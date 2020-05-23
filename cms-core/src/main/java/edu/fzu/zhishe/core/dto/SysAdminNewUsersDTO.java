package edu.fzu.zhishe.core.dto;

import cn.hutool.core.date.DateUtil;
import edu.fzu.zhishe.core.param.SysAdminNewUsersQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class SysAdminNewUsersDTO {

    private List<String> date;
    private List<Integer> newUsers;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Integer> getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(List<Integer> newUsers) {
        this.newUsers = newUsers;
    }
}
