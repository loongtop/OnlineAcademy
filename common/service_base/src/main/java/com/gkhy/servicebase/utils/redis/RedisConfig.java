package com.gkhy.servicebase.utils.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;

/**
 * There are two ways to use redis operations in Springboot: lettuce and jedis, both of which require serializers to achieve serialization (jackson2JsonRedisSerializer is recommended)
 * , shorter than the serializer and String serializer provided by JDK)
 * The bottom layer of Springboot 1.x uses jedis, and Springboot 2.x uses lettuce. Lettuce and Jedis are positioned as Redis clients, so of course they can directly connect to the redis server.
 * Jedis is a directly connected redis server in implementation. If it is not thread-safe in a multi-threaded environment, only the connection pool is used at this time to add physical connections to each Jedis instance
 * Lettuce's connection is based on Netty, and the connection instance (StatefulRedisConnection) can be accessed concurrently between multiple threads. It should be thread-safe for StatefulRedisConnection.
 * Therefore, one connection instance (StatefulRedisConnection) can satisfy concurrent access in a multi-threaded environment, and if one connection instance is not enough, you can also add connection instances as needed.
 **/

@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    // key??????????????????
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * json?????????
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> jackson2JsonRedisSerializer() {
        //??????Jackson2JsonRedisSerializer???????????????????????????redis???value???
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        // ?????????????????????????????????config??????????????????????????????????????????
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // ????????????????????????????????????????????????Duration??????
        config = config.entryTtl(Duration.ofMinutes(60 * 12))
                // ?????? key???string?????????
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // ??????value???json?????????
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()))
                // ???????????????
                .disableCachingNullValues();
        // ?????????????????????????????????????????????cacheManager
        return RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(config).transactionAware().build();
    }

    /**
     * RedisTemplate??????
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //todo ?????????????????????lettuceConnectionFactory???springboot2.0???????????????????????????
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //todo ??????Jackson2JsonRedisSerializer???????????????????????????redis???value??????????????????JDK?????????????????????
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //todo ???????????????????????????field,get???set,????????????????????????ANY???????????????private???public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //todo ????????????????????????????????????????????????final????????????final?????????????????????String,Integer??????????????????
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //??????StringRedisSerializer???????????????????????????redis???key???
        RedisSerializer redisSerializer = new StringRedisSerializer();
        //key
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //value ?????????json?????????
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

