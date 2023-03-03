package org.cache.redis.system;


import org.cache.redis.system.util.SpringRedisBeanObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;

public class CacheClient<T,R> extends AbstractRedisCacheHandle<T,R>{


    public CacheClient() {
        super();
    }

    @Override
    RedisTemplate getRedisTemplate() {
        return (RedisTemplate) SpringRedisBeanObjectUtils.getBean("redisTemplate");
    }


}
