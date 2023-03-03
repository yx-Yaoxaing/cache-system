package org.cache.redis.system.auto;


import org.cache.redis.system.CacheServiceClient;
import org.cache.redis.system.util.SpringRedisBeanObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisStartInitAutoConfiguration {

    @Bean
    public SpringRedisBeanObjectUtils springRedisBeanObjectUtils() {
        return new SpringRedisBeanObjectUtils();
    }

    @Bean
    public CacheServiceClient cacheServiceClient() {
        return new CacheServiceClient();
    }

}
