package com.zyj.hiddendanger.auth.infrustructure.chain;

import com.zyj.hiddendanger.core.chain.AbstractPredicatableHandlerComposite;
import com.zyj.hiddendanger.core.chain.PredicatableHandler;
import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;

public class LoginHandlerComposite extends AbstractPredicatableHandlerComposite<UserLoginVO, LoginRequestDTO> {

    @SafeVarargs
    public LoginHandlerComposite(PredicatableHandler<UserLoginVO, LoginRequestDTO>... predicatableHandlers) {
        super(predicatableHandlers);
    }
}
