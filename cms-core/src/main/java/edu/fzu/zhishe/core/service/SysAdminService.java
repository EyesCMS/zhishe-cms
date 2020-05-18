package edu.fzu.zhishe.core.service;

import edu.fzu.zhishe.core.dto.SysAdminAuditedDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminNewUsersDTO;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface SysAdminService {

    SysAdminAuditedDTO unaudited();

    SysAdminNewUsersDTO newUsers();

    SysAdminClubSpeciesDTO clubSpecies();
}
