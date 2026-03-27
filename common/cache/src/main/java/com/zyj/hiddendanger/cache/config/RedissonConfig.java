package com.zyj.hiddendanger.cache.config;

import jakarta.annotation.Resource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Resource
    private RedisProperties redisProperties;

    @Bean
    public RedissonClient redissonClient() {
        // 配置
        Config config = new Config();
        config.useSingleServer()
              .setDatabase(0)
              .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
              .setUsername(redisProperties.getUsername())
              .setPassword(redisProperties.getPassword());
        config.setCodec(new JsonJacksonCodec());
        // 创建RedissonClient对象
        return Redisson.create(config);
    }
}
