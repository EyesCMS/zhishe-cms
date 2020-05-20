package edu.fzu.zhishe.core.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
}
