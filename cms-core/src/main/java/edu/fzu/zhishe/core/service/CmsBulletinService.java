package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.core.param.CmsBulletinParam;
import edu.fzu.zhishe.core.param.PaginationParam;
import java.util.List;

/**
 * @author zou
 * 公告模块服务层
 */
public interface CmsBulletinService {

    CmsBulletin getBulletin(Integer clubId, Integer bulletinId);

    CmsBulletin getBulletinById(Integer id);

    int creatBulletin(Integer clubId, CmsBulletinParam cmsBulletinParam);

    int updateBulletin(Integer bulletinId, CmsBulletinParam cmsBulletinParam);

    int deleteBulletin(Integer id);

    List<CmsBulletin> listClubBulletin(int clubId, PaginationParam paginationParam);
}
