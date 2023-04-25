package com.djy.limiter.core.enums;


import com.djy.limiter.core.base.BaseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常枚举  A 是客户端导致的错误，B 是服务端导致的错误 C 第三方导致的错误
 */
public enum BaseExceptionEnum implements BaseEnum {

    SUCCESS("00000", "SUCCESS", "操作成功"),

    NULL_ARGUMENT("A10001", "NULL_ARGUMENT", "参数为空"),

    ILLEGAL_ARGUMENT("A0002", "ILLEGAL_ARGUMENT", "参数错误"),

    NO_OPERATE_PERMISSION("A0003", "NO_OPERATE_PERMISSION", "无操作权限"),

    REPEAT_REQUEST("A0004", "REPEAT_REQUEST", "请不要重复请求"),


    SYSTEM_ERROR("B0001", "SYSTEM_ERROR", "系统错误"),

    CONNECT_TIME_OUT("B0002", "CONNECT_TIME_OUT", "t系统超时，请重试"),

    LOGIC_ERROR("B0003", "LOGIC_ERROR", "逻辑错误"),

    BIZ_FAIL("B0004", "BIZ_FAIL", "业务处理失败"),

    BIZ_UNKNOWN("B0005", "BIZ_UNKNOWN", "业务处理中，请稍候"),

    DATA_ERROR("B0006", "DATA_ERROR", "数据异常"),

    ;

    /**
     * 枚举编号
     */
    @Getter
    @Setter
    private String code;

    /**
     * 枚举编号
     */
    @Getter
    @Setter
    private String value;

    /**
     * 枚举详情
     */
    @Getter
    @Setter
    private String message;

    /**
     * 构造方法
     *
     * @param code 枚举编号
     * @param value 枚举值
     * @param message 枚举详情
     */
    private BaseExceptionEnum(String code, String value, String message) {
        this.code = code;
        this.value = value;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String getMsgByCode(String code) {
        return message;
    }

    public static BaseExceptionEnum getByCode(String code) {
        BaseExceptionEnum businessExceptionEnum = null;
        if (code == null) {
            return businessExceptionEnum;
        }
        for (BaseExceptionEnum enums : BaseExceptionEnum.values()) {
            if (enums.code.equals(code)) {
                businessExceptionEnum = enums;
                break;
            }
        }
        return businessExceptionEnum;
    }

}
