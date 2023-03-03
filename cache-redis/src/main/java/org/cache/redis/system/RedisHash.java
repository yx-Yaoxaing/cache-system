package org.cache.redis.system;


import org.cache.redis.system.communal.RedisOperateHash;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisHash<K,F,V> implements RedisOperateHash<K,F,V> {

    private RedisTemplate redisTemplate;

    public RedisHash(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void hset(K key, F field, V value) {
        redisTemplate.opsForHash().put(key,field,value);
    }

    @Override
    public V hget(K key, F field) {
        return (V) redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public void hset(K key, F field, V value, long time, TimeUnit unit) {
        this.hset(key,field,value);
        redisTemplate.expire(key, time, unit);
    }
}
