package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.date.DateUtil;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.CreditEnum;
import edu.fzu.zhishe.core.param.CreditForCheckinParam;
import edu.fzu.zhishe.core.service.CreditService;
import edu.fzu.zhishe.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @author yang on 5/18/2020.
 * @version 1.0
 */
@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CmsUserClubRelMapper cmsUserClubRelMapper;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void creditAdd(CmsUserClubRel cmsUserClubRel, int credit) {
        int oldCredit = cmsUserClubRel.getCredit();
        oldCredit += credit;
        cmsUserClubRel.setCredit(oldCredit);
        cmsUserClubRelMapper.updateByPrimaryKeySelective(cmsUserClubRel);
    }

    @Override
    public void checkin(Integer clubId) {
        CmsUserClubRelExample example = new CmsUserClubRelExample();
        example.createCriteria().andUserIdEqualTo(sysUserService.getCurrentUser().getId())
                .andClubIdEqualTo(clubId);
        List<CmsUserClubRel> userClubRelList = cmsUserClubRelMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userClubRelList)) {
            Asserts.fail(" 该社团已不存在或您已退出（还未加入）该社团 ");
        }
        CmsUserClubRel userClubRel = userClubRelList.get(0);

        Date date = new Date();
        Date lastCheckinDate = userClubRel.getCheckInDate();

        if (lastCheckinDate == null) {
            userClubRel.setCheckInDate(date);
            creditAdd(userClubRel, CreditEnum.CHECKIN.getValue());
            return;
        }

        if (DateUtil.isSameDay(date, lastCheckinDate)) {
            Asserts.forbidden("今天您已签到过，同一天不可重复签到");
        } else {
            userClubRel.setCheckInDate(date);
            creditAdd(userClubRel, CreditEnum.CHECKIN.getValue());
        }
    }
}
