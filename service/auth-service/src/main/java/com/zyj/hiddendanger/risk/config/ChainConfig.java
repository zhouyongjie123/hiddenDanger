package com.zyj.hiddendanger.risk.config;

import com.zyj.hiddendanger.risk.infrustructure.chain.AccountAndPasswordLoginHandler;
import com.zyj.hiddendanger.risk.infrustructure.chain.LoginHandlerComposite;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChainConfig {
    @Bean
    public LoginHandlerComposite loginHandlerComposite(AccountAndPasswordLoginHandler accountAndPasswordLoginHandler) {
        return new LoginHandlerComposite(accountAndPasswordLoginHandler);
    }
}
