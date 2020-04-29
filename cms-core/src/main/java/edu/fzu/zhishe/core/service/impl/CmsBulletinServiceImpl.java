package edu.fzu.zhishe.core.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsBulletinMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.cms.model.CmsBulletinExample;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;

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
    private CmsClubMapper clubMapper;

    @Override
    public List<CmsBulletin> listBulletin(int bulletinId,int page, int limit) {
        CmsBulletinExample bulletinExample = new CmsBulletinExample();
        bulletinExample.createCriteria().andIdEqualTo(bulletinId);
        PageHelper.startPage(page, limit);
        return bulletinMapper.selectByExample(bulletinExample);
    }

    @Override
    public List<CmsBulletin> listClubBulletin(int clubId, int page, int limit) {
        CmsBulletinExample bulletinExample = new CmsBulletinExample();
        bulletinExample.createCriteria().andClubIdEqualTo(clubId);

        PageHelper.startPage(page, limit);
        return bulletinMapper.selectByExample(bulletinExample);
    }

    @Override
    public CmsBulletin getBulletinById(int id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    @Override
    public int creatBulletin(CmsBulletinParam cmsBulletinParam) {
        CmsBulletin bulletin = new CmsBulletin(){{
            //TODO:id自增  setId();
            setClubId(getClubId());//获取当前club_id 未实现
            setTitle(cmsBulletinParam.getTitle());
            setBody(cmsBulletinParam.getContent());
            setCreateAt(new Date());
            setUpdateAt(new Date());
        }
        };
        return bulletinMapper.insert(bulletin);
    }

    @Override
    public int updateBulletin(CmsBulletinParam cmsBulletinParam) {
        CmsBulletin bulletin = new CmsBulletin(){{
            //存在问题
            //TODO update bulletin
            setTitle(cmsBulletinParam.getTitle());
            setBody(cmsBulletinParam.getContent());
            setUpdateAt(new Date());
        }};
       return bulletinMapper.updateByPrimaryKey(bulletin);
    }

    @Override
    public int deleteBulletin(int id) {
        return bulletinMapper.deleteByPrimaryKey(id);
    }



}
