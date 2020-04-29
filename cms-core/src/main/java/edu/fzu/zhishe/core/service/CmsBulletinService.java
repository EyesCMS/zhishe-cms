package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import java.util.List;

/**
 * @author zou
 * 公告模块服务层
 */
public interface CmsBulletinService {

    List<CmsBulletin> listBulletin(int bulletinId,int page, int limit);

    CmsBulletin getBulletinById(int id);

    int creatBulletin(CmsBulletinParam cmsBulletinParam);

    int updateBulletin(CmsBulletinParam cmsBulletinParam);

    int deleteBulletin(int id);

    List<CmsBulletin> listClubBulletin(int clubId, int page, int limit);
}
