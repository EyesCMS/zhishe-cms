package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.FmsPostMapper;
import edu.fzu.zhishe.cms.model.*;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.annotation.IsAdmin;
import edu.fzu.zhishe.core.annotation.IsLogin;
import edu.fzu.zhishe.core.constant.ActivityStateEnum;
import edu.fzu.zhishe.core.constant.DeleteStateEnum;
import edu.fzu.zhishe.core.constant.PostTypeEnum;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dao.CmsClubActivityDAO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyDTO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyListDTO;
import edu.fzu.zhishe.core.error.ClubErrorEnum;
import edu.fzu.zhishe.core.param.CmsActivityQuery;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.StorageService;
import edu.fzu.zhishe.core.service.SysUserService;

import edu.fzu.zhishe.core.util.NotExistUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
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

    @Autowired
    private CreditService creditService;

    @Autowired
    private StorageService storageService;

    @IsLogin
    @Override
    public void activityApply(CmsClubActivityParam param, MultipartFile imgUrl) {

        if (param.getStartDate().after(param.getEndDate())) {
            Asserts.forbidden("结束日期不可以在开始日期之前");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(param.getClubId());
        if (NotExistUtil.check(club)) {
            Asserts.notFound("clubId 错误，找不到社团");
        }

        if (!club.getChiefId().equals(userService.getCurrentUser().getId())) {
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

        //设置上传活动图片
        if (imgUrl != null) {
            String url = storageService.storeImage(imgUrl);
            if (!StrUtil.isEmpty(url)) {
                activity.setImgUrl(url);
            }
        }

        if (activityMapper.insert(activity) == 0) {
            Asserts.fail("数据库插入失败创建申请活动失败");
        }
    }

    @IsLogin
    @Override
    public void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(applyId);
        SysUser user = userService.getCurrentUser();
        if (NotExistUtil.check(activity)) {
            Asserts.notFound("找不到活动");
        }

        if (role.equals(UserRoleEnum.ADMIN) && activity.getState().equals(ActivityStateEnum.PENDING.getValue())) {
            if (user.getIsAdmin() == 0) {
                Asserts.forbidden("非管理员，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.ACTIVE.getValue())
                || stateId.equals(ActivityStateEnum.REJECTED.getValue())) {

                activity.setState(stateId);
                activity.setHandleAt(new Date());
                activityMapper.updateByPrimaryKey(activity);
                return;
            }
        }
        else if (role.equals(UserRoleEnum.CHIEF) && activity.getState().equals(ActivityStateEnum.ACTIVE.getValue())) {
            CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
            if (NotExistUtil.check(club)) {
                Asserts.notFound(ClubErrorEnum.CLUB_NOT_EXIST);
            }
            if (!club.getChiefId().equals(user.getId())) {
                Asserts.forbidden("非社长，权限不足");
            }
            if (stateId.equals(ActivityStateEnum.PUBLISHED.getValue())
                || stateId.equals(ActivityStateEnum.FINISHED.getValue())) {

                activity.setState(stateId);
                activity.setHandleAt(new Date());
                activityMapper.updateByPrimaryKey(activity);
                //创建一个帖子
                FmsPost post = new FmsPost();
                post.setPosterId(club.getId());
                post.setType(PostTypeEnum.ACTIVITY.getValue());
                post.setTitle(activity.getTitle());
                post.setContent(activity.getBody());
                post.setImgUrl(activity.getImgUrl());
                post.setCreateAt(new Date());
                post.setDeleteState(DeleteStateEnum.Existence.getValue());
                if (postMapper.insertSelective(post) == 0) {
                    Asserts.fail("发布活动时创建帖子失败");
                }
                creditService.getCreditByActivity(club.getId());
                return;
            }
        }
        Asserts.forbidden("状态未改变或权限不足");
    }

    @Override
    public void delActivity(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (NotExistUtil.check(activity)) {
            Asserts.notFound("活动ID不存在");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (NotExistUtil.check(club)) {
            Asserts.notFound("活动对应社团不存在");
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

    @IsLogin
    @Override
    public List<CmsActivityApplyDTO> listActivitiesApply(
        Integer clubId,
        CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam) {

        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (NotExistUtil.check(club)) {
            Asserts.notFound("社团ID错误，无法获取社团信息");
        }

        SysUser user = userService.getCurrentUser();
        if (!user.getId().equals(club.getChiefId())) {
            Asserts.forbidden("非社长无法查看申请活动");
        }

        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return activityDAO.listActivityApplyForChief(clubId, param);
    }

    @IsLogin
    @Override
    public CmsActivity getActivityApplyItem(Integer id) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (NotExistUtil.check(activity)) {
            Asserts.notFound("申请ID错误，获取申请活动失败");
        }

        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (NotExistUtil.check(club)) {
            Asserts.notFound("获取活动的社团失败");
        }
        if (!userService.getCurrentUser().getId().equals(club.getChiefId())) {
            Asserts.forbidden("非社长无法查看申请活动");
        }

        return activity;
    }

    @IsLogin
    @Override
    public void updateActivity(Integer id, CmsActivityUpdateParam param) {
        CmsActivity activity = activityMapper.selectByPrimaryKey(id);
        if (NotExistUtil.check(activity)) {
            Asserts.notFound("获取活动失败");
        }
        CmsClub club = clubMapper.selectByPrimaryKey(activity.getClubId());
        if (!club.getChiefId().equals(userService.getCurrentUser().getId())) {
            Asserts.forbidden("非社长不能修改活动");
        }
        if (param.getStartDate().after(param.getEndDate())) {
            Asserts.forbidden("结束日期不可以在开始日期之前");
        }
        activity.setBody(param.getContent());
        BeanUtils.copyProperties(param, activity);
        if (activityMapper.updateByPrimaryKeySelective(activity) == 0) {
            Asserts.fail("更新失败");
        }
    }

    @IsAdmin
    @Override
    public List<CmsActivityApplyListDTO> listActivitiesApply(
        CmsActivityQuery param,
        PaginationParam paginationParam,
        OrderByParam orderByParam) {

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
