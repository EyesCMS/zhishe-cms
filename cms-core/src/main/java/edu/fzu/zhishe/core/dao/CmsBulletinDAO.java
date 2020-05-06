package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsBulletinsDTO;

import java.util.List;

import edu.fzu.zhishe.core.param.CmsBulletinQuery;
import org.apache.ibatis.annotations.Param;

public interface CmsBulletinDAO {

    List<CmsBulletinsDTO> listBulletin(@Param("clubId") Integer clubId, @Param("bulletinQuery")CmsBulletinQuery bulletinQuery);
}
