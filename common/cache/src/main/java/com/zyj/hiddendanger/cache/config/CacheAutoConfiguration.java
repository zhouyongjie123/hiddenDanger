package com.zyj.hiddendanger.cache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Import;

@Import({RedisConfig.class, RedissonConfig.class})
@EnableMethodCache(basePackages = "com.zyj.hiddendanger")
public class CacheAutoConfiguration {
}
