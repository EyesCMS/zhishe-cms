package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

public enum ApplyAuditErrorEnum  implements BaseErrorEnum {

    // 400
    MAPPER_OPERATION_FAILED("mapper operation failed"),
    NOT_APPLY("不是申请"),
    ALREADY_AUDIT(" 该申请已经审核完毕 "),
    CLUB_ALREADY_EXIST(" 该社团已经存在 "),
    ALREADY_APPLY_CREATE(" 该社团已经申请创建，请等待审核 "),
    ALREADY_APPLY_DISBAND(" 该社团已经申请解散，请等待审核 "),
    ALREADY_JOINED(" 您已加入该社团 "),
    ALREADY_APPLY_JOIN(" 您已申请加入该社团，请等待审核 "),
    NOT_MEMBER(" 该新社长不是该社团成员 "),
    ALREADY_APPLY_CHIEF_CHANG(" 该社团已经申请换届，请等待审核 "),
    ALREADY_CERTIFICATED(" 该社团已经是认证社团 "),
    ALREADY_APPLY_OFFICIAL_CHANGE(" 该社团已经申请认证，请等待审核 "),


    // 403
    CAN_NOT_DISBAND(" 您不是社长无权解散 "),
    CAN_NOT_AUDIT_JOIN_APPLY(" 您不是该社社长无权进行加入申请审核 "),
    CAN_NOT_CHIEF_CHANGE(" 你不是该社团社长无权换届 "),
    CAN_NOT_OFFICIAL_CHANGE(" 你不是该社团社长无权认证 "),
    ALREADY_CHECKIN("今天您已签到过，同一天不可重复签到"),

    // 404
    APPLY_NOT_EXIST("该申请不存在"),
    CLUB_NOT_EXIST(" 该社团不存在 "),
    CLUB_ALREADY_DISBAND(" 该社团已解散 "),
    USER_NOT_EXIST("该新社长用户不存在"),
    CLUB_TYPE_NOT_EXIST("该社团类型不存在"),
    ;


    private ApplyAuditErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
