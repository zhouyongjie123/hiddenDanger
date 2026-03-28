package com.zyj.hiddendanger.rpc.api.auth.service.mock;

import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;

@RpcMockService
public class UserFacadeServiceMock implements UserFacadeService {
    @Override
    public String getRealNameById(String userId) {
        return "";
    }
}
