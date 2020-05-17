package edu.fzu.zhishe.core.dto;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class SysAdminAuditedDTO {


    /**
     * createNumber : 1
     * dismissNumber : 2
     * activityNumber : 3
     * changeNumber : 4
     * identifyNumber : 5
     */

    private int createNumber;
    private int dismissNumber;
    private int activityNumber;
    private int changeNumber;
    private int identifyNumber;

    public int getCreateNumber() {
        return createNumber;
    }

    public void setCreateNumber(int createNumber) {
        this.createNumber = createNumber;
    }

    public int getDismissNumber() {
        return dismissNumber;
    }

    public void setDismissNumber(int dismissNumber) {
        this.dismissNumber = dismissNumber;
    }

    public int getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(int activityNumber) {
        this.activityNumber = activityNumber;
    }

    public int getChangeNumber() {
        return changeNumber;
    }

    public void setChangeNumber(int changeNumber) {
        this.changeNumber = changeNumber;
    }

    public int getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(int identifyNumber) {
        this.identifyNumber = identifyNumber;
    }
}
