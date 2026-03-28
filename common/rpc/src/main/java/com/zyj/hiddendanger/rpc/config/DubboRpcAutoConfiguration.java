package com.zyj.hiddendanger.rpc.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import({DubboRpcConfig.class})
@EnableConfigurationProperties(RpcProperties.class)
public class DubboRpcAutoConfiguration {
}
