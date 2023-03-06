package org.cache.redis.system;


import org.springframework.data.redis.core.RedisTemplate;

public abstract class AbstractRedisCommon {

    private static RedisTemplate redisTemplate;

    public static void setRedisTemplate(RedisTemplate redisTemplate){
        AbstractRedisCommon.redisTemplate = redisTemplate;
    }

    public static boolean hashKey(String key){
       return redisTemplate.hasKey(key);
    }

}
