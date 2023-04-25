package com.djy.limiter.core.exception;


import com.djy.limiter.core.base.BaseEnum;
import com.djy.limiter.core.enums.BaseExceptionEnum;

/**
 * 异常类 所有异常类需要继承该类

 */
public class BaseException extends RuntimeException {

    protected String code;

    protected String message;

    private String[] args;

    protected BaseEnum errorEnum;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(BaseEnum baseEnum) {
        super(baseEnum.message());
        this.code = baseEnum.code();
        this.message = baseEnum.message();
        this.errorEnum = baseEnum;
    }

    public BaseException(BaseEnum baseEnum, String[] args) {
        super(baseEnum.message());
        this.code = baseEnum.code();
        this.message = baseEnum.message();
        this.errorEnum = baseEnum;
        this.args = args;
    }

    public BaseException(BaseEnum baseEnum, String message) {
        super(message);
        this.code = baseEnum.code();
        this.errorEnum = baseEnum;
        this.message = message;
    }

    public BaseException(String message) {
        super(message);
        this.errorEnum = BaseExceptionEnum.BIZ_FAIL;
        this.code = BaseExceptionEnum.BIZ_FAIL.getCode();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BaseEnum getErrorEnum() {
        return errorEnum;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
