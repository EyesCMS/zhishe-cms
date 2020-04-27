package edu.fzu.zhishe.core.service.impl;

import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsBulletinMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.cms.model.CmsBulletinExample;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import edu.fzu.zhishe.core.service.CmsBulletinService;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
@Service
public class CmsBulletinServiceImpl implements CmsBulletinService {

    @Autowired
    private CmsBulletinMapper bulletinMapper;

    @Autowired
    private CmsClubMapper clubMapper;

    @Override
    public List<CmsBulletin> listBulletin(int page, int limit) {
        PageHelper.startPage(page, limit);
        return bulletinMapper.selectByExample(null);
    }

    @Override
    public CmsBulletin getBulletinById(int id) {
        return bulletinMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveBulletin(CmsBulletinParam cmsBulletinParam) {
        CmsBulletin bulletin = new CmsBulletin();
        BeanUtils.copyProperties(cmsBulletinParam, bulletin);
        bulletinMapper.insertSelective(bulletin);
    }

    @Override
    public void updateBulletin(CmsBulletinParam cmsBulletinParam) {
        CmsBulletin bulletin = new CmsBulletin();
        BeanUtils.copyProperties(cmsBulletinParam, bulletin);
        bulletinMapper.updateByPrimaryKey(bulletin);
    }

    @Override
    public void deleteBulletin(int id) {
        bulletinMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CmsBulletin> listClubBulletin(int clubId, int page, int limit) {
        CmsBulletinExample bulletinExample = new CmsBulletinExample();
        bulletinExample.createCriteria().andClubIdEqualTo(clubId);

        PageHelper.startPage(page, limit);
        return bulletinMapper.selectByExample(bulletinExample);
    }
}
