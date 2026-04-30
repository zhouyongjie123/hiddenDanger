package com.zyj.hiddendanger.rpc.api.auth.service;

import java.util.List;
import java.util.Map;

public interface UserFacadeService {
    String getRealNameById(String userId);

    Map<String, String> getRealNameByIds(List<String> userIds);
}
