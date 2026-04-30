package com.zyj.hiddendanger.rpc.api.auth.service.mock;

import com.zyj.hiddendanger.rpc.annotation.RpcMockService;
import com.zyj.hiddendanger.rpc.api.auth.service.UserFacadeService;

import java.util.List;
import java.util.Map;

@RpcMockService
public class UserFacadeServiceMock implements UserFacadeService {
    @Override
    public String getRealNameById(String userId) {
        return "mockUser_" + userId;
    }

    @Override
    public Map<String, String> getRealNameByIds(List<String> userIds) {
        return Map.of();
    }
}
