package com.zyj.hiddendanger.cache.config;

import com.alicp.jetcache.anno.support.EncoderParser;
import com.zyj.hiddendanger.cache.serial.JsonEncoderParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JetCacheConfig {
    @Bean
    public EncoderParser encoderParser(){
        return new JsonEncoderParser();	// 支持fastjson2序列化
    }
}
