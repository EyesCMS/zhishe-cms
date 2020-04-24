package edu.fzu.zhishe.core.dto;
/**
 *社团创建审核申请表单数据
 *
 * @author yang
 */
public class CmsClubsAuditParam {
    /**
     * id : 1
     * state : 1
     */

    private int id;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
