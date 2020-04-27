package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsBulletin;
import edu.fzu.zhishe.core.dto.CmsBulletinParam;
import java.util.List;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public interface CmsBulletinService {

    List<CmsBulletin> listBulletin(int page, int limit);

    CmsBulletin getBulletinById(int id);

    void saveBulletin(CmsBulletinParam cmsBulletinParam);

    void updateBulletin(CmsBulletinParam cmsBulletinParam);

    void deleteBulletin(int id);

    List<CmsBulletin> listClubBulletin(int clubId, int page, int limit);
}
