package com.djy.limiter.core.base;

/**
 * 业务枚举 所有的枚举继承该枚举
 */
public interface BaseEnum {



    /** 获取code */
    String code();

    /** 获取消息 */
    String message();

    /** 获取枚举值 */
    String value();

    /** 根据字符串code码获取message */
    String getMsgByCode(String code);
}
