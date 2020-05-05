package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.cms.model.CmsActivityExample;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubExample;
import edu.fzu.zhishe.cms.model.SysUser;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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

    @Override
    public void activityApply(CmsClubActivityParam param) {
        CmsActivity activity = new CmsActivity();
        BeanUtils.copyProperties(param, activity);
        activity.setStarDate(param.getStartDate());
        activity.setEndData(param.getEndDate());
        activity.setCreateAt(new Date());

        activity.setState(0);
        if (activityMapper.insert(activity) == 0) {
            Asserts.fail("创建申请活动失败");
        }
    }

    @Override
    public void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(applyId);
        SysUser user = userService.getCurrentUser();
        if (activity == null || user == null) {
            Asserts.notFound("找不到活动");
        }
        if (user == null) {
            Asserts.forbidden("权限不足");
        }

        if (role.equals(UserRoleEnum.ADMIN) && activity.getState().equals(ActivityStateEnum.PENDING)) {
            if (user.getIsAdmin() == 0) {
                Asserts.forbidden("非管理员，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.ACTIVE) ||
                    stateId.equals(ActivityStateEnum.REJECTED)) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        else if (role.equals(UserRoleEnum.CHIEF) && activity.getState().equals(ActivityStateEnum.ACTIVE)) {
            CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
            if (club == null || !club.getChiefId().equals(user.getId())) {
                Asserts.forbidden("非社长，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.PUBLISHED) || stateId.equals(ActivityStateEnum.FINISHED)) {
                activity.setState(stateId);
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        Asserts.fail("状态未改变或权限不足");
    }

    @Override
    public void delActivity(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (activity == null || club == null) {
            Asserts.fail("活动ID不存在");
        }
        SysUser user = userService.getCurrentUser();
        if (user.getIsAdmin() == 1 || user.getId().equals(club.getChiefId())) {
            activity.setState(ActivityStateEnum.DELETED.getValue());
            if (activityMapper.updateByPrimaryKey(activity) == 0) {
                Asserts.fail("删除失败，删除0行");
            }
        } else {
            Asserts.fail("非社长或管理员，权限不足");
        }
    }

    @Override
    public List<CmsActivityApplyDTO> listActivitiesApply(Integer clubId,
        CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam) {
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        SysUser user = userService.getCurrentUser();

        if (club == null) {
            Asserts.fail("社团ID错误，无法获取社团信息");
        }

        if (user == null || !user.getId().equals(club.getChiefId())) {
            Asserts.fail("非社长无法查看申请活动");
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return activityDAO.listActivityApplyForChief(clubId, param);
    }

    @Override
    public CmsActivity getActivityApplyItem(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (activity == null || activity.getState().equals(ActivityStateEnum.DELETED)) {
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
            Asserts.fail("请登录");
        }
        if (activity == null || activity.getState().equals(ActivityStateEnum.DELETED)) {
            Asserts.fail("获取活动失败");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (!club.getChiefId().equals(user.getId())) {
            Asserts.fail("非社长不能修改活动");
        }
        activity.setName(param.getName());
        activity.setTitle(param.getTitle());
        activity.setBody(param.getContent());
        activity.setStarDate(param.getStarDate());
        activity.setEndData(param.getEndDate());
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
}
