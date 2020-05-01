package edu.fzu.zhishe.core.dao;

import edu.fzu.zhishe.core.dto.CmsActivityApplyDTO;
import edu.fzu.zhishe.core.dto.FmsPostDTO;
import edu.fzu.zhishe.core.dto.QueryParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author liang on 4/26/2020.
 * @version 1.0
 */
public interface CmsActivityDAO {

    /**
     * 查看活动申请列表
     * @author PSF
     */
    List<CmsActivityApplyDTO> selectActivitiesApply(Integer clubId);
}
