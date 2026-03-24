package com.zyj.hiddendanger.auth.infrustructure.chain;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.zyj.hiddendanger.core.chain.PredicatableHandler;
import com.zyj.hiddendanger.model.dto.LoginRequestDTO;
import com.zyj.hiddendanger.model.vo.UserLoginVO;

import java.util.stream.Stream;

public class AccountAndPasswordLoginHandler implements PredicatableHandler<UserLoginVO, LoginRequestDTO> {
    @Override
    public UserLoginVO handle(LoginRequestDTO value) {

        UserLoginVO userLoginVO = new UserLoginVO()
                .setAccount("oawianwf")
                .setRealName("aoeina3r")
                .setPhoneNumber("mock")
                .setDepartmentName("mock")
                .setRoleName("123");

        return userLoginVO;
    }

    @Override
    public Boolean isSupported(LoginRequestDTO value) {
        return Stream.of(value.getAccount(), value.getPassword()).allMatch(StringUtils::isNotBlank);
    }
}
