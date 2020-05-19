package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dao.CmsClubActivityDAO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyDTO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyListDTO;
import edu.fzu.zhishe.core.param.CmsActivityQuery;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.SysUserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @author PSF 2020/04/27
 */
@Service
public class CmsActivityServiceImpl implements CmsActivityService {

    @Autowired
    private CmsActivityMapper activityMapper;

    @Autowired
    private CmsClubMapper clubMapper;

    @Autowired
    private CmsClubActivityDAO activityDAO;

    @Autowired
    private SysUserService userService;

    @Autowired
    private FmsPostMapper postMapper;

    @Override
    public void activityApply(CmsClubActivityParam param) {
        SysUser user = userService.getCurrentUser();

        if (param.getStartDate().after(param.getEndDate())) {
            Asserts.forbidden("结束日期不可以在开始日期之前");
        }

        if (user == null) {
            Asserts.forbidden("请登录");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(param.getClubId());
        if (club== null) {
            Asserts.notFound("clubId错误，找不到社团");
        }

        if (!club.getChiefId().equals(user.getId())) {
            Asserts.forbidden("非社长无法申请社团活动");
        }

        CmsActivityExample example = new CmsActivityExample();
        example.createCriteria().andStateEqualTo(ActivityStateEnum.PENDING.getValue())
                .andClubIdEqualTo(param.getClubId()).andNameEqualTo(param.getName());

        if (!activityMapper.selectByExample(example).isEmpty()) {
            Asserts.forbidden("已申请活动，多次申请失败");
        }

        CmsActivity activity = new CmsActivity();
        BeanUtils.copyProperties(param, activity);
        activity.setBody(param.getContent());
        activity.setCreateAt(new Date());

        activity.setState(ActivityStateEnum.PENDING.getValue());
        if (activityMapper.insert(activity) == 0) {
            Asserts.fail("数据库插入失败创建申请活动失败");
        }
    }

    @Override
    public void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(applyId);
        SysUser user = userService.getCurrentUser();
        if (activity == null) {
            Asserts.notFound("找不到活动");
        }
        if (user == null) {
            Asserts.forbidden("权限不足");
        }

        if (role.equals(UserRoleEnum.ADMIN) && activity.getState().equals(ActivityStateEnum.PENDING.getValue())) {
            if (user.getIsAdmin() == 0) {
                Asserts.forbidden("非管理员，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.ACTIVE.getValue()) ||
                    stateId.equals(ActivityStateEnum.REJECTED.getValue())) {
                activity.setState(stateId);
                activity.setHandleAt(new Date());
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        else if (role.equals(UserRoleEnum.CHIEF) && activity.getState().equals(ActivityStateEnum.ACTIVE.getValue())) {
            CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
            if (club == null || !club.getChiefId().equals(user.getId())) {
                Asserts.forbidden("非社长，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.PUBLISHED.getValue()) || stateId.equals(ActivityStateEnum.FINISHED.getValue())) {
                activity.setState(stateId);
                activity.setHandleAt(new Date());
                activityMapper.updateByPrimaryKey(activity);
                //创建一个帖子
                FmsPost post = new FmsPost() {{
                    setPosterId(club.getId());
                    setType(PostTypeEnum.ACTIVITY.getValue());
                    setTitle(activity.getTitle());
                    setContent(activity.getBody());
                    setImgUrl(activity.getImgUrl());
                    setCreateAt(new Date());
                    setDeleteState(DeleteStateEnum.Existence.getValue());
                }};
                if (postMapper.insertSelective(post) == 0) {
                    Asserts.fail("发布活动时创建帖子失败");
                }

                return;
            }
        }
        Asserts.forbidden("状态未改变或权限不足");
    }

    @Override
    public void delActivity(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (activity == null || club == null
                || activity.getState().equals(ActivityStateEnum.DELETED.getValue())) {
            Asserts.notFound("活动ID不存在");
        }
        SysUser user = userService.getCurrentUser();
        if (user.getIsAdmin() == 1 || user.getId().equals(club.getChiefId())) {
            activity.setState(ActivityStateEnum.DELETED.getValue());
            activity.setHandleAt(new Date());
            if (activityMapper.updateByPrimaryKey(activity) == 0) {
                Asserts.fail("删除失败，删除0行");
            }
        } else {
            Asserts.forbidden("非社长或管理员，权限不足");
        }
    }

    @Override
    public List<CmsActivityApplyDTO> listActivitiesApply(Integer clubId,
        CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam) {
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        SysUser user = userService.getCurrentUser();

        if (club == null) {
            Asserts.notFound("社团ID错误，无法获取社团信息");
        }

        if (user == null || !user.getId().equals(club.getChiefId())) {
            Asserts.forbidden("非社长无法查看申请活动");
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return activityDAO.listActivityApplyForChief(clubId, param);
    }

    @Override
    public CmsActivity getActivityApplyItem(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (activity == null || activity.getState().equals(ActivityStateEnum.DELETED.getValue())) {
            Asserts.fail("申请ID错误，获取申请活动失败");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        SysUser user = userService.getCurrentUser();
        if (user == null || !user.getId().equals(club.getChiefId())) {
            Asserts.fail("非社长无法查看申请活动");
        }

        return activity;
    }

    @Override
    public void updateActivity(Integer id, CmsActivityUpdateParam param) {
        SysUser user = userService.getCurrentUser();
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (user == null) {
            Asserts.forbidden("请登录");
        }
        if (activity == null || activity.getState().equals(ActivityStateEnum.DELETED.getValue())) {
            Asserts.notFound("获取活动失败");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (!club.getChiefId().equals(user.getId())) {
            Asserts.forbidden("非社长不能修改活动");
        }
        if (param.getStartDate().after(param.getEndDate())) {
            Asserts.forbidden("结束日期不可以在开始日期之前");
        }
        activity.setName(param.getName());
        activity.setTitle(param.getTitle());
        activity.setBody(param.getContent());
        activity.setStartDate(param.getStartDate());
        activity.setEndDate(param.getEndDate());
        if (param.getLocation() != null) {
            activity.setLocation(param.getLocation());
        }
        if (activityMapper.updateByPrimaryKey(activity) == 0) {
            Asserts.fail("更新失败");
        }
    }

    @Override
    public List<CmsActivityApplyListDTO> listActivitiesApply(CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam) {
        SysUser user = userService.getCurrentUser();
        if (user == null || user.getIsAdmin() == 0) {
            Asserts.fail("非管理员无法查询");
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());

        return activityDAO.listActivityApplyForAdmin(param.getState(), param.getClubName());
    }

    /**
     * update activity state: published -> finished
     */
    @Override
    public int finishExpiredActivity() {

        return activityDAO.finishActivityBatch(new Date());
    }
}
