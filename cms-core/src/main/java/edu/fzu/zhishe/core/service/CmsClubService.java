package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.cms.model.CmsClubCreateApply;
import edu.fzu.zhishe.core.dto.CmsClubsCreationsParam;

import java.util.List;

public interface CmsClubService {
    CmsClubCreateApply clubCreate(CmsClubsCreationsParam clubsCreationsParam);

    List<CmsClubCreateApply> getClubCreateList();

}
