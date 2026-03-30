package com.zyj.hiddendanger.model.service.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoVO {
    private String id;

    private String account;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    private String departmentName;


    private String roleId;

    private String roleName;
}
