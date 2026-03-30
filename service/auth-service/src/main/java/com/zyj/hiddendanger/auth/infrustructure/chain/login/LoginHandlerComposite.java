package com.zyj.hiddendanger.auth.infrustructure.chain.login;

import com.zyj.hiddendanger.core.chain.AbstractPredicatableHandlerComposite;
import com.zyj.hiddendanger.core.chain.PredicatableHandler;
import com.zyj.hiddendanger.model.service.auth.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;

public class LoginHandlerComposite extends AbstractPredicatableHandlerComposite<UserInfoDTO, LoginRequestDTO> {

    @SafeVarargs
    public LoginHandlerComposite(PredicatableHandler<UserInfoDTO, LoginRequestDTO>... predicatableHandlers) {
        super(predicatableHandlers);
    }
}
