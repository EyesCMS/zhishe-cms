package edu.fzu.zhishe.core.dto;

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
