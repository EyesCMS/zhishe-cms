package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsActivityMapper;
import edu.fzu.zhishe.cms.mapper.CmsBulletinMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.cms.model.CmsBulletinExample;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;

import edu.fzu.zhishe.core.service.CmsClubService;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zou
 * 公告模块服务层
 */
@Service
public class CmsBulletinServiceImpl implements CmsBulletinService {

    @Autowired
    private CmsBulletinMapper bulletinMapper;

    @Autowired
    private CmsClubService clubService;

    @Autowired
    CmsClubMapper clubMapper;

    @Override
    public CmsBulletin getBulletin(Integer clubId, Integer bulletinId) {
        if (!clubService.isClubMember(clubId)) {
            Asserts.fail(" 您没有访问的权限！ ");
        }
        return bulletinMapper.selectByPrimaryKey(bulletinId);
    }

    @Override
    public List<CmsBulletin> listClubBulletin(int clubId, int page, int limit) {
        CmsBulletinExample bulletinExample = new CmsBulletinExample();
        bulletinExample.createCriteria().andClubIdEqualTo(clubId);

        PageHelper.startPage(page, limit);
        return bulletinMapper.selectByExample(bulletinExample);
    }

    @Override
    public CmsBulletin getBulletinById(Integer id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    @Override
    public int creatBulletin(Integer clubId, CmsBulletinParam cmsBulletinParam) {
        CmsBulletin bulletin = new CmsBulletin(){{
            setClubId(clubId);//获取当前club_id 未实现，目前直接通过前端传递
            setTitle(cmsBulletinParam.getTitle());
            setBody(cmsBulletinParam.getContent());
            setCreateAt(new Date());
            setUpdateAt(new Date());
        }
        };
        return bulletinMapper.insert(bulletin);
    }

    @Override
    public int updateBulletin(Integer bulletinId, CmsBulletinParam cmsBulletinParam) {

        CmsBulletin bulletin = bulletinMapper.selectByPrimaryKey(bulletinId);
        if (bulletin == null) {
            Asserts.fail( "找不到 id 为 " + bulletinId + " 的公告 " );
        }

        Integer clubId = bulletin.getClubId();
        //CmsClub club = clubService.getClubById(clubId);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club.getDeleteStatus() == 1) {
            Asserts.fail(" 找不到该社团 ");
        }

        if (clubService.getClubStatue(clubId) != ClubStatueEnum.CHIEF) {
            Asserts.fail( " 权限不足 " );
        }

        BeanUtils.copyProperties(cmsBulletinParam, bulletin);
        bulletin.setBody(cmsBulletinParam.getContent());
        bulletin.setUpdateAt(new Date());
       return bulletinMapper.updateByPrimaryKey(bulletin);
    }

    @Override
    public int deleteBulletin(Integer id) {
        return bulletinMapper.deleteByPrimaryKey(id);
    }
}
