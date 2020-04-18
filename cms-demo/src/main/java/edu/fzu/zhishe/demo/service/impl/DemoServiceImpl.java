package edu.fzu.zhishe.demo.service.impl;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageHelper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsClubDO;
import edu.fzu.zhishe.demo.dto.CmsClubDTO;
import edu.fzu.zhishe.demo.service.DemoService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private CmsClubMapper clubMapper;

    @Autowired
    private Mapper dozerMapper;


    @Override
    public int insertClub(CmsClubDTO cmsClubDTO) {
//        CmsClub cmsClub = new CmsClub();
//        BeanUtils.copyProperties(cmsClubDTO, cmsClub);
//        cmsClub.setName(cmsClubDTO.getClubName());
//        cmsClub.setCreateAt(new Date());
        CmsClubDO cmsClubDO = dozerMapper.map(cmsClubDTO, CmsClubDO.class);
        cmsClubDO.setCreateAt(new Date());
        return clubMapper.insertSelective(cmsClubDO);
    }

    @Override
    public int updateClub(Integer id, CmsClubDTO cmsClubDTO) {
        CmsClubDO cmsClubDO = dozerMapper.map(cmsClubDTO, CmsClubDO.class);
        cmsClubDO.setCreateAt(new Date());
        return clubMapper.updateByPrimaryKeySelective(cmsClubDO);
    }

    @Override
    public int deleteClub(Integer id) {
        return clubMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CmsClubDO> listClubs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return clubMapper.selectByExample(null);
    }

    @Override
    public CmsClubDO getClub(Integer id) {
        return clubMapper.selectByPrimaryKey(id);
    }
}
