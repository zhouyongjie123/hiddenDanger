package com.zyj.hiddendanger.model.service.auth.dto;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zyj.hiddendanger.database.handler.PasswordEncryptTypeHandler;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserUpdateDTO {
    @NotBlank
    private String id;

    private String account;

    private String password;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    private String roleId;

    private String avatarUrl;
}
