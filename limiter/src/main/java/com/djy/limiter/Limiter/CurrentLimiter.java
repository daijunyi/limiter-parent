package com.djy.limiter.Limiter;

/**
 * des:限流器
 *
 * @author jun.yi.dai
 * @version : LimitCheck, v 0.1 2023/3/29 19:19 jun.yi.dai Exp $
 */
@FunctionalInterface
public interface CurrentLimiter {

    /**
     * 检查是否限流
     * @return true 限流 false 不限流
     */
    boolean limit() throws Throwable;

}
