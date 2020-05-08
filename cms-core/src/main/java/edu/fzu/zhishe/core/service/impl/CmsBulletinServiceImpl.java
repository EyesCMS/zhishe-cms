package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsBulletinMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.cms.model.CmsBulletinExample;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.common.exception.Asserts;
import edu.fzu.zhishe.core.constant.ClubStatueEnum;
import edu.fzu.zhishe.core.dao.CmsBulletinDAO;
import edu.fzu.zhishe.core.dto.CmsBulletinDTO;
import edu.fzu.zhishe.core.dto.CmsBulletinsDTO;
import edu.fzu.zhishe.core.param.CmsBulletinParam;
import edu.fzu.zhishe.core.param.CmsBulletinQuery;
import edu.fzu.zhishe.core.param.PaginationParam;
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

    @Autowired
    private CmsBulletinDAO bulletinDAO;

    @Override
    public CmsBulletin getBulletin(Integer clubId, Integer bulletinId) {
//        if (!clubService.isClubMember(clubId)) {
//            Asserts.fail(" 您没有访问的权限！ ");
//        }
        return bulletinMapper.selectByPrimaryKey(bulletinId);
    }

    @Override
    public List<CmsBulletinsDTO> listClubBulletin(int clubId, PaginationParam paginationParam, CmsBulletinQuery bulletinQuery) {
        //CmsBulletinExample bulletinExample = new CmsBulletinExample();
        //bulletinExample.createCriteria().andClubIdEqualTo(clubId);


        PageHelper.startPage(paginationParam.getPage(), paginationParam.getLimit());
        return bulletinDAO.listBulletin(clubId,bulletinQuery);
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
            setBody(cmsBulletinParam.getBody());
            setCreateAt(new Date());
            setUpdateAt(new Date());
        }
        };
        return bulletinMapper.insert(bulletin);
    }

    @Override
    public int updateBulletin(Integer bulletinId, CmsBulletinParam cmsBulletinParam) {

        CmsBulletin bulletin = bulletinMapper.selectByPrimaryKey(bulletinId);
        Asserts.notNull(bulletin);

        Integer clubId = bulletin.getClubId();
        //CmsClub club = clubService.getClubById(clubId);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club.getDeleteStatus() == 1) {
            Asserts.notFound(" 找不到该社团 ");
        }

        if (clubService.getClubStatue(clubId) != ClubStatueEnum.CHIEF) {
            Asserts.forbidden( " 权限不足 " );
        }

        BeanUtils.copyProperties(cmsBulletinParam, bulletin);
        bulletin.setBody(cmsBulletinParam.getBody());
        bulletin.setUpdateAt(new Date());
       return bulletinMapper.updateByPrimaryKey(bulletin);
    }

    @Override
    public int deleteBulletin(Integer bulletinId) {

        CmsBulletin bulletin = bulletinMapper.selectByPrimaryKey(bulletinId);
        if (bulletin == null) {
            Asserts.notFound( "找不到 id 为 " + bulletinId + " 的公告 " );
        }

        Integer clubId = bulletin.getClubId();
        //CmsClub club = clubService.getClubById(clubId);
        CmsClub club = clubMapper.selectByPrimaryKey(clubId);
        if (club.getDeleteStatus() == 1) {
            Asserts.notFound(" 找不到该社团 ");
        }

        if (clubService.getClubStatue(clubId) != ClubStatueEnum.CHIEF) {
            Asserts.forbidden( " 权限不足 " );
        }
        return bulletinMapper.deleteByPrimaryKey(bulletinId);
    }
}
