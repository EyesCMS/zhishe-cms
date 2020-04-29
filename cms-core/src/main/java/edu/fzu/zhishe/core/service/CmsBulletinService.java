package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import java.util.List;

/**
 * @author zou
 * 公告模块服务层
 */
public interface CmsBulletinService {

    CmsBulletin getBulletin(Integer clubId, int bulletinId);

    CmsBulletin getBulletinById(int id);

    int creatBulletin(Integer clubId, CmsBulletinParam cmsBulletinParam);

    int updateBulletin(Integer bulletinId, CmsBulletinParam cmsBulletinParam);

    int deleteBulletin(int id);

    List<CmsBulletin> listClubBulletin(int clubId, int page, int limit);
}
