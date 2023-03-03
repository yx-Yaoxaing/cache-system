package org.cache.redis.system.communal;

import java.util.concurrent.TimeUnit;

/**
 * hash操作
 */
public interface RedisOperateHash<K,F,V> {

    void hset(K key,F field,V value);

    V hget(K key,F field);

    void hset(K key, F field, V value, long time, TimeUnit unit);

}
