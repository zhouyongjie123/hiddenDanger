package com.zyj.hiddendanger.model.po;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
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
@TableName("user")
public class User extends Entity {
    private String account;

    private String password;

    private String realName;

    private String phoneNumber;

    private String departmentId;

    @EnumValue
    private UserStatus status;

    private String roleId;

    public enum UserStatus {
        NORMAL, DISABLED;
    }
}
