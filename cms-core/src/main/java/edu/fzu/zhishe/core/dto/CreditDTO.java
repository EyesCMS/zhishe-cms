package edu.fzu.zhishe.core.dto;

import edu.fzu.zhishe.core.constant.CheckinStateEnum;

public class CreditDTO {
    private int state;
    private String message;

    public CreditDTO(int state){
        this.setState(state);
        if(state == CheckinStateEnum.GRANTED.getValue()){
            this.setMessage("可以签到");
        }
        if(state == CheckinStateEnum.DONE.getValue()){
            this.setMessage("今天您已签到过，同一天不可重复签到");
        }
        if(state == CheckinStateEnum.DENIED.getValue()){
            this.setMessage("该社团已不存在或您已退出（还未加入）该社团");
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
