package com.zyj.hiddendanger.model.service.auth.dto;

import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.vo.UserInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoDTO {
    private String id;

    private String account;

    private String password;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    private String departmentName;

    private String roleId;

    private String roleName;

    private String avatarUrl;

    private User.UserStatus status;

    public UserInfoVO toUserInfoVO() {
        return new UserInfoVO().setId(this.id)
                               .setAccount(this.account)
                               .setRealName(this.realName)
                               .setPhoneNumber(this.phoneNumber)
                               .setDepartmentId(this.departmentId)
                               .setDepartmentName(this.departmentName)
                               .setAvatarUrl(this.avatarUrl)
                               .setRoleId(this.roleId)
                               .setRoleName(this.roleName);
    }
}
