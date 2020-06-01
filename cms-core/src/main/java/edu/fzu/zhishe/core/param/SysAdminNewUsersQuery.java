package edu.fzu.zhishe.core.param;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
@Data
public class SysAdminNewUsersQuery {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    public Date endDate;

    public SysAdminNewUsersQuery() {
        startDate = DateUtil.lastWeek();
        endDate = DateUtil.parse(DateUtil.today());
    }
}
