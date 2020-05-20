package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.SysAdminClubSpeciesDTO;
import edu.fzu.zhishe.core.dto.SysAdminClubTypeAmount;

import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface SysAdminDAO {
    List<String> listClubTypes();

    List<SysAdminClubTypeAmount> getClubTypes();
}
