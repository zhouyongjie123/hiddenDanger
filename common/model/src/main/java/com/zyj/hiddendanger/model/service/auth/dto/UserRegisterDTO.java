package com.zyj.hiddendanger.model.service.auth.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRegisterDTO {
    private String account;

    @Nullable
    private String password;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    private String roleId;

    private static String DEFAULT_PASSWORD = "123456";

    public String getPassword() {
        return password == null ? DEFAULT_PASSWORD : password;
    }
}
