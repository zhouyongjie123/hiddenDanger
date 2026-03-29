package com.zyj.hiddendanger.model.service.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserLoginVO {
    private String id;

    private String account;

    private String realName;

    private String phoneNumber;

    private String departmentName;

    private String roleName;

    private String tokenName;

    private String tokenValue;
}
