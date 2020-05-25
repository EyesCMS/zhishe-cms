package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubGrade;
import edu.fzu.zhishe.cms.model.CmsMemberHonor;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.core.dto.UserHonorDTO;

import java.util.Date;
import java.util.List;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
public interface CreditService {
    /**
     * 个人累加积分
     */
    void creditAdd(CmsUserClubRel cmsUserClubRel,int credit);
    /**
     * 计算活跃度
     */
    CmsUserClubRel honorCalculate(CmsUserClubRel cmsUserClubRel);

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
    void getCreditByComment(Integer postId);
    /**
     * 获取当前用户活跃度
     */
    UserHonorDTO getUserHonor(Integer clubId);
    /**
     * 获取用户活跃度规则信息
     */
    List<CmsMemberHonor> listMemberHonor();
    /**
     * 获取社团等级规则信息
     */
    List<CmsClubGrade> listClubGrade();


    /**
     *社团活动获取积分
     */
    CmsClub getCreditByActivity(CmsClub cmsClub);
    /**
     *社团积分累加
     */
    void clubCreditAdd(CmsClub cmsClub,int credit);
    /**
     *社团等级计算
     */
    CmsClub clubCreditCalculate(CmsClub cmsClub);

}
