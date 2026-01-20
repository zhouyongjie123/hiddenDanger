package com.zyj.hiddendanger.rpc.config;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

@DubboComponentScan(basePackages = "com.zyj.hiddendanger")
@EnableDubbo
public class DubboRpcConfig {
}
