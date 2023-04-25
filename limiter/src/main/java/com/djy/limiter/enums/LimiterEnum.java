package com.djy.limiter.enums;


import com.djy.limiter.core.base.BaseEnum;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : LimiterEnum, v 0.1 2023/3/31 16:42 jun.yi.dai Exp $
 */
public enum LimiterEnum implements BaseEnum {
    LIMITER("B11001","LIMITER", "被限流了"),

    LIMITER_ERROR("B11002","LIMITER_ERROR", "限流器发生了异常错误"),

    LIMITER_LOAD_REDIS_SCRIPT_ERROR("B11003","LIMITER_LOAD_REDIS_SCRIPT_ERROR", "加载限流脚本发生错误"),

    LIMITER_IMPL_ERROR("B11004","LIMITER_IMPL_ERROR", "LimiterTemplate实现类得继承自com.xddigital.platform.limiter.template.impl.AbsLimiterTemplate"),

    LIMITER_IMPL_NEW_ERROR("B11005","LIMITER_IMPL_NEW_ERROR", "限流器创建失败"),
    ;

    private String code;

    private String value;

    private String message;

    LimiterEnum(String code, String value, String message) {
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
        for (LimiterEnum e : LimiterEnum.values()) {
            if (e.code().equals(code)) {
                return e.message();
            }
        }
        return null;
    }
}
