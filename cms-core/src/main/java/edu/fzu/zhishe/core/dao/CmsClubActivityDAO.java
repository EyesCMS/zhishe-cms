package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsActivityApplyDTO;
import edu.fzu.zhishe.core.dto.CmsActivityApplyListDTO;
import edu.fzu.zhishe.core.param.CmsActivityQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public interface CmsClubActivityDAO {
    List<CmsActivityApplyDTO> listActivityApplyForChief(@Param("clubId") Integer clubId,
                                                        CmsActivityQuery queryParam);

    List<CmsActivityApplyListDTO> listActivityApplyForAdmin(Integer state, String clubName);
}
