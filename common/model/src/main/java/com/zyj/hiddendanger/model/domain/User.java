package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.database.handler.PasswordEncryptTypeHandler;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user", autoResultMap = true)
public class User extends Entity {
    private String account;

    @TableField(typeHandler = PasswordEncryptTypeHandler.class)
    private String password;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    @EnumValue
    private UserStatus status;

    private String roleId;

    private String avatarUrl;

    public enum UserStatus {
        NORMAL, DISABLED;
    }

    public UserSelectionVO toUserSelectionVO() {
        return new UserSelectionVO().setId(this.id)
                                    .setRealName(this.realName);
    }


    public UserInfoVO toUserInfoVO(String departmentName, String roleName) {
        return new UserInfoVO().setId(this.id)
                               .setAccount(this.account)
                               .setRealName(this.realName)
                               .setPhoneNumber(this.phoneNumber)
                               .setDepartmentId(this.departmentId)
                               .setDepartmentName(departmentName)
                               .setRoleId(this.roleId)
                               .setAvatarUrl(this.avatarUrl)
                               .setRoleName(roleName);
    }
}
