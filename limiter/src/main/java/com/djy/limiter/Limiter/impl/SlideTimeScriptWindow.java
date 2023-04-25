package com.djy.limiter.Limiter.impl;


import com.djy.limiter.Limiter.RedisCurrentLimiter;
import com.djy.limiter.enums.LimiterEnum;
import com.djy.limiter.exception.LimiterException;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * des: 滑动时间窗口算法
 *
 * @author jun.yi.dai
 * @version : Time, v 0.1 2023/3/29 19:39 jun.yi.dai Exp $
 */
@Slf4j
@Data
@Accessors(chain = true)
public class SlideTimeScriptWindow extends RedisCurrentLimiter {


    private RedisScript<Long> redisScript;

    public SlideTimeScriptWindow() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        ResourceScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("script/rate_limiter.lua"));
        try {
            redisScript.setScriptText(scriptSource.getScriptAsString());
            redisScript.setResultType(Long.class);
            this.redisScript = redisScript;
        } catch (IOException e) {
            throw new LimiterException(LimiterEnum.LIMITER_LOAD_REDIS_SCRIPT_ERROR,e);
        }
    }

    @Override
    public boolean limit(){
        String key = this.key;
        List<String> keys = Arrays.asList(key);
        Long currentTime = System.currentTimeMillis();
        String uuid = java.util.UUID.randomUUID().toString();
        try {
            Long result = stringRedisTemplate.execute(redisScript, keys, maxInInterval.toString(), uuid, currentTime.toString(),timeUnit.toMillis(rangeTime)+"");
            return result == 0;
        }catch (RedisConnectionFailureException e){
            throw e;
        }
    }

}
