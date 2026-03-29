package com.zyj.hiddendanger.model.service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserRegisterDTO {
    @NotBlank(message = "账号不能为空")
    private String account;

    private String password;

    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;

    @NotBlank(message = "部门id不能为空")
    private String departmentId;

    @NotBlank(message = "角色id不能为空")
    private String roleId;

    private static String DEFAULT_PASSWORD = "123456";

    public String getPassword() {
        return password == null || password.isEmpty() ? DEFAULT_PASSWORD : password;
    }
}
