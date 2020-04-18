package edu.fzu.zhishe.demo.service;

import edu.fzu.zhishe.cms.model.CmsClubDO;
import edu.fzu.zhishe.demo.dto.CmsClubDTO;
import java.util.List;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
public interface DemoService {

    int insertClub(CmsClubDTO cmsClubDTO);

    int updateClub(Integer id, CmsClubDTO cmsClubDTO);

    int deleteClub(Integer id);

    List<CmsClubDO> listClubs(int pageNum, int pageSize);

    CmsClubDO getClub(Integer id);
}
