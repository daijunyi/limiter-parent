package com.djy.limiter.callback;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : FailCallBack, v 0.1 2023/4/3 09:08 jun.yi.dai Exp $
 */
@FunctionalInterface
public interface FailCallBack {

    void onFail(Throwable e);

}
