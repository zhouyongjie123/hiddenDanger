package com.zyj.hiddendanger.gateway;

import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.zyj.hiddendanger")
public class GatewayApplication {
    @Resource
    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String applicationName = Optional
                .ofNullable(applicationContext.getEnvironment()
                                              .getProperty("spring.application.name"))
                .orElse("");
        System.out.println(applicationName + " started ✧⁺⸜(๑˙▾˙๑)⸝⁺✧");
    }
}
