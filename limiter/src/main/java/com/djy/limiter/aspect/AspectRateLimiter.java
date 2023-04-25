package com.djy.limiter.aspect;


import com.djy.limiter.Limiter.AbsCurrentLimiter;
import com.djy.limiter.LimiterTemplate;
import com.djy.limiter.aspect.base.BaseAspect;
import com.djy.limiter.core.annotation.RateLimiter;
import com.djy.limiter.exception.LimiterException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : AspectLimiter, v 0.1 2023/3/30 18:10 jun.yi.dai Exp $
 */
@Aspect
@Slf4j
public class AspectRateLimiter extends BaseAspect {

    @Getter
    @Setter
    private LimiterTemplate limiterTemplate;

    /**
     * 拦截： api/integration
     */
    @Pointcut("@annotation(com.djy.limiter.core.annotation.RateLimiter)")
    public void annotationCut() {}

    @Around("annotationCut()")
    public Object annotationCut(ProceedingJoinPoint pjp) throws Throwable {
        return around(pjp);
    }

    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        limiter(joinPoint);
        Object invokeResult = null;
        try {
            // 2.服务调用
            invokeResult = joinPoint.proceed();
            return invokeResult;
        } catch (Exception e) {
            // 异常情况处理
            throw e;
        }
    }

    /**
     * 限流
     * @param joinPoint
     */
    private void limiter(ProceedingJoinPoint joinPoint) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            RateLimiter rateLimiter = getTargetMethodAnnotation(joinPoint, RateLimiter.class);
            //1.2.4 获取自定义注解中operation属性的值
            String key =rateLimiter.key();
            int maxInInterval = rateLimiter.maxInInterval();
            long rangeTime = rateLimiter.rangeTime();
            TimeUnit timeUnit = rateLimiter.timeUnit();
            Class<? extends AbsCurrentLimiter> aClass = rateLimiter.currentLimiter();
            if (aClass == AbsCurrentLimiter.class) {
                limiterTemplate.limit(key, maxInInterval, rangeTime, timeUnit);
            }else{
                AbsCurrentLimiter absCurrentLimiter = aClass.newInstance();
                absCurrentLimiter.setKey(key);
                absCurrentLimiter.setMaxInInterval(maxInInterval);
                absCurrentLimiter.setRangeTime(rangeTime);
                absCurrentLimiter.setTimeUnit(timeUnit);
                limiterTemplate.limit(absCurrentLimiter);
            }
        } catch (LimiterException e) {
            throw e;
        } catch (Exception e){
            throw e;
        }
    }

}
