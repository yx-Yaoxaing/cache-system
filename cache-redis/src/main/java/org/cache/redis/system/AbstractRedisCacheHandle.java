package org.cache.redis.system;


import cn.hutool.json.JSONUtil;
import org.cache.redis.system.common.Validate;
import org.cache.redis.system.communal.RedisOperateString;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;

public abstract class AbstractRedisCacheHandle<T,R> implements RedisOperateString<T,R> {


    abstract RedisTemplate<T,R> getRedisTemplate();

    private final RedisTemplate<T,R> redisTemplate;

    public AbstractRedisCacheHandle() {
         redisTemplate = getRedisTemplate();
    }

    @Override
    public void set(T key, R value) {
        Validate.parmValidate(key,value);
        redisTemplate.opsForValue().set(key, (R) JSONUtil.toJsonStr(value));
    }

    @Override
    public void set(T key, R value, long time, TimeUnit unit) {
        Validate.parmValidate(key,value);
        redisTemplate.opsForValue().set(key, (R)JSONUtil.toJsonStr(value), time, unit);
    }

    @Override
    public R get(T key,Class<? extends R> type) {
        Validate.parmValidate(key);
        if (type.isAssignableFrom(String.class)) {
            return redisTemplate.opsForValue().get(key);
        }
        R t = redisTemplate.opsForValue().get(key);
        R bean = (R) JSONUtil.toBean((String) t, type);
        return bean;
    }

    @Override
    public R get(T key) {
        Validate.parmValidate(key);
        return redisTemplate.opsForValue().get(key);
    }
}
