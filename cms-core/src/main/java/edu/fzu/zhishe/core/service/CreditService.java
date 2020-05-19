package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.core.param.CreditForCheckinParam;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.List;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
public interface CreditService {
    /**
     * 累加积分
     */
    void creditAdd(CmsUserClubRel cmsUserClubRel,int credit);

    /**
     * 获取签到积分
     */
    void checkin(Integer clubId,Date date);

    /**
     * 是否已经签到
     */
    int isCheckin(Integer clubId,Date date,List<CmsUserClubRel> userClubRelList);

    /**
     * 获取评论积分
     */
    void comment(Integer postId);


}
