package com.zyj.hiddendanger.web.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.template.QuickConfig;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static com.zyj.hiddendanger.cache.Separator.DEFAULT_SEPARATOR;

@Configuration
public class AuthServiceCacheConfig {
    @Resource
    private CacheManager cacheManager;

    @Bean
    public Cache<String, String> departmentNameCache() {
        QuickConfig qc = QuickConfig.newBuilder("dept" + DEFAULT_SEPARATOR + "name" + DEFAULT_SEPARATOR)
                                    .expire(Duration.ofSeconds(100))
                                    .cacheType(CacheType.BOTH)
                                    .syncLocal(true)
                                    .build();
        return cacheManager.getOrCreateCache(qc);
    }

    @Bean
    public Cache<String,String> userNameCache(){
        QuickConfig qc = QuickConfig.newBuilder("user" + DEFAULT_SEPARATOR + "name" + DEFAULT_SEPARATOR)
                                    .expire(Duration.ofSeconds(100))
                                    .cacheType(CacheType.BOTH)
                                    .syncLocal(true)
                                    .build();
        return cacheManager.getOrCreateCache(qc);
    }

    @Bean
    public Cache<String,String> roleNameCache(){
        QuickConfig qc = QuickConfig.newBuilder("role" + DEFAULT_SEPARATOR + "name" + DEFAULT_SEPARATOR)
                                    .expire(Duration.ofSeconds(100))
                                    .cacheType(CacheType.BOTH)
                                    .syncLocal(true)
                                    .build();
        return cacheManager.getOrCreateCache(qc);
    }
}
