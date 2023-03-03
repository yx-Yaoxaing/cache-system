package org.cache.redis.system;


import cn.hutool.json.JSONUtil;
import org.cache.redis.system.util.SpringRedisBeanObjectUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author uYxUuu
 * 缓存客户端相关业务
 */
public class CacheServiceClient extends AbstractRedisCommon implements InitializingBean {

    private static RedisTemplate redisTemplate;


    /**
     *
     * @param keyPrefix 缓存key的前缀
     * @param id 业务id
     * @param time 时间
     * @param timeUnit 时间单位
     * @param type 类
     * @param function 数据库查询的函数
     * @param <R> 返回类型
     * @param <ID> id
     * @return 缓存结果
     */
    public static <R,ID> R doQueryCacheAndDB(String keyPrefix, ID id, long time, TimeUnit timeUnit, Class<R> type, Function<ID,R> function){
        String redisCacheKey = keyPrefix + id;
        // 查询redis
        Object cacheData = redisTemplate.opsForValue().get(redisCacheKey);
        if (cacheData != null){
            R r = JSONUtil.toBean(JSONUtil.toJsonStr(cacheData), type);
            return r;
        }
        // redis中不存在 就查询数据库，这里查询数据库的操作交给调用方
        R db = function.apply(id);
        redisTemplate.opsForValue().set(redisCacheKey,JSONUtil.toJsonStr(db),time,timeUnit);
        return db;
    }

    /**
     * 业务落地-购物车类似结构可以
     * 查询hash缓存在处理业务操作
     */
    public static void queryHashCacheAndService(String key,String filed,Class<?> type){
        // redisTemplate.opsForHash().put();
    }


    /**
     * 业务落地-排名、排行榜等操作
     * key member score
     * 当key和member不存在的时候 就是新增
     * 当key和member存在的时候 就是修改{@code type} 当type等于1的时候就是直接修改 当type等于2的时候新值大于旧值才会进行修改
     * {@see 进行使用的时候 {@code type}的正确性 相对于来说很重要 这取决于业务是怎么执行的}
     */
    public static <R,N> void doRank(String key,R member,N score,int type){
        Double dataScore = Double.valueOf((String) score);
        if (type == 2 && hashKey(key)) {
            // 取旧值 比较大小 修改值
            Double redicScore = redisTemplate.opsForZSet().score(key, member);
            if (dataScore > redicScore) {
                redisTemplate.opsForZSet().add(key,member,dataScore);
            }
        } else {
            redisTemplate.opsForZSet().add(key, member, dataScore);
        }
    }




    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate = (RedisTemplate) SpringRedisBeanObjectUtils.getBean("redisTemplate");
        setRedisTemplate(redisTemplate);
    }
}
