package com.djy.limiter.callback;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : Runnable, v 0.1 2023/4/1 11:16 jun.yi.dai Exp $
 */
@FunctionalInterface
public interface RunCallback<T> {

    /**
     * 执行业务
     * @return
     * @throws Exception
     */
    <T> T run() throws Throwable;

}
