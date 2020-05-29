package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsActivity;
import edu.fzu.zhishe.core.constant.UserRoleEnum;
import edu.fzu.zhishe.core.dto.CmsActivityApplyDTO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyListDTO;
import edu.fzu.zhishe.core.dto.CmsActivityRecommendDTO;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.param.CmsActivityQuery;
import edu.fzu.zhishe.core.param.CmsActivityUpdateParam;
import edu.fzu.zhishe.core.param.CmsClubActivityParam;
import edu.fzu.zhishe.core.param.OrderByParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author PSF 2020/04/27
 */
public interface CmsActivityService {

    void activityApply(CmsClubActivityParam param, MultipartFile imgUrl);

    void activityStateChange(Integer applyId, Integer stateId, UserRoleEnum role);

    void delActivity(Integer id);

    List<CmsActivityApplyDTO> listActivitiesApply(Integer clubId, CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam);

    CmsActivity getActivityApplyItem(Integer id);

    void updateActivity(Integer id, CmsActivityUpdateParam param);

    List<CmsActivityApplyListDTO> listActivitiesApply(CmsActivityQuery param,
        PaginationParam paginationParam, OrderByParam orderByParam);

    List<CmsActivityRecommendDTO> listRecomend(Integer number);

    int finishExpiredActivity();
}
