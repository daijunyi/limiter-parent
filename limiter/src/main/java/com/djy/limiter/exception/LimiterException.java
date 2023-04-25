package com.djy.limiter.exception;


import com.djy.limiter.core.base.BaseEnum;
import com.djy.limiter.core.exception.BaseException;

/**
 * des: 限流异常
 *
 * @author jun.yi.dai
 * @version : LimiterException, v 0.1 2023/3/31 16:32 jun.yi.dai Exp $
 */
public class LimiterException extends BaseException {

    public LimiterException(String code, String message) {
        super(code, message);
    }

    public LimiterException(BaseEnum baseEnum) {
        super(baseEnum);
    }

    public LimiterException(BaseEnum baseEnum, String[] args) {
        super(baseEnum, args);
    }

    public LimiterException(BaseEnum baseEnum, String message) {
        super(baseEnum, message);
    }

    public LimiterException(BaseEnum baseEnum,Throwable e) {
        super(baseEnum);
        addSuppressed(e);
    }

    public LimiterException(String message) {
        super(message);
    }


}
