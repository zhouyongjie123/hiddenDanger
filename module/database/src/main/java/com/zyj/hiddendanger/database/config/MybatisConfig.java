package com.zyj.hiddendanger.database.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.core.id.SnowflakeIdGenerator;
import com.zyj.hiddendanger.database.BasePojoInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Bean
    public MetaObjectHandler metaObjectHandler(IdGenerator<String> idGenerator) {
        return new BasePojoInjector(idGenerator);
    }

    @Bean
    public IdGenerator<String> snowflakeIdGenerator() {
        return new SnowflakeIdGenerator(0);
    }

    //配置分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
