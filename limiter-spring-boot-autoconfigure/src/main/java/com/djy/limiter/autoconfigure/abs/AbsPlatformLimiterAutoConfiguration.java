package com.djy.limiter.autoconfigure.abs;

import com.djy.limiter.Limiter.AbsCurrentLimiter;
import com.djy.limiter.LimiterTemplate;
import com.djy.limiter.autoconfigure.PlatformLimiterProperties;
import com.djy.limiter.core.exception.BaseException;
import com.djy.limiter.enums.LimiterEnum;
import com.djy.limiter.impl.AbsLimiterTemplate;
import com.djy.limiter.impl.BaseRedisLimiterTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * des:
 *
 * @author jun.yi.dai
 * @version : Abs, v 0.1 2023/4/1 10:41 jun.yi.dai Exp $
 */
public abstract class AbsPlatformLimiterAutoConfiguration {

    /**
     * 设置限流器缓存
     * @param redisTemplate
     * @param limiterTemplate
     */
    protected static void setLimiterCacheDbTemplate(StringRedisTemplate redisTemplate, LimiterTemplate limiterTemplate) {
        if (limiterTemplate instanceof BaseRedisLimiterTemplate){
            ((BaseRedisLimiterTemplate) limiterTemplate).setStringRedisTemplate(redisTemplate);
        }
    }

    /**
     * 设置限流器
     * @param properties
     * @param limiterTemplate
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected void setLimiterCurrentLimiter(PlatformLimiterProperties properties, LimiterTemplate limiterTemplate) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (limiterTemplate instanceof AbsLimiterTemplate){
            ((AbsLimiterTemplate) limiterTemplate).setAbsCurrentLimiter(getAbsCurrentLimiter(properties));
        }else{
            throw new BaseException(LimiterEnum.LIMITER_IMPL_ERROR,"LimiterTemplate实现类得继承自com.xddigital.platform.limiter.template.impl.AbsLimiterTemplate");
        }
    }

    /**
     * 获取限流实现
     * @param properties
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected AbsCurrentLimiter getAbsCurrentLimiter(PlatformLimiterProperties properties) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> limitCheck = getClass().getClassLoader().loadClass(properties.getCurrentLimiter());
        Object o = limitCheck.newInstance();
        if (!(o instanceof AbsCurrentLimiter)){
            throw new BaseException(LimiterEnum.LIMITER_IMPL_ERROR,"限流器实现类得继承自com.xddigital.platform.limiter.template.Limiter.AbsCurrentLimiter");
        }
        AbsCurrentLimiter currentLimiter = (AbsCurrentLimiter) o;
        return currentLimiter;
    }

    protected LimiterTemplate getLimiterTemplate(String limiterTemplateImpl) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> limiterTemplateClass = getClass().getClassLoader().loadClass(limiterTemplateImpl);
        Object limiterTemplateClassObj = limiterTemplateClass.newInstance();
        if (!(limiterTemplateClassObj instanceof BaseRedisLimiterTemplate)){
            throw new BaseException(LimiterEnum.LIMITER_IMPL_ERROR,"LimiterTemplate实现类得继承自com.xddigital.platform.limiter.template.impl.BaseRedisLimiterTemplate");
        }
        LimiterTemplate limiterTemplate = (LimiterTemplate) limiterTemplateClassObj ;
        return limiterTemplate;
    }


}
