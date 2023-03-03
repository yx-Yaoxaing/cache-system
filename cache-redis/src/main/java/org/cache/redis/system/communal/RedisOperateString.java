package org.cache.redis.system.communal;


import java.util.concurrent.TimeUnit;

/**
 * String数据类型的基本操作
 * @param <T> key
 * @param <R> value
 */
public interface RedisOperateString<T,R> {

    void set(T key,R value);


    void set(T key, R value, long time, TimeUnit unit);


    R get(T key,Class<? extends R> type);

    R get(T key);

}
