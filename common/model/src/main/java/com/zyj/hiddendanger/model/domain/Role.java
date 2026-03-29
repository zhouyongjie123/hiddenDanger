package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("role")
public class Role extends Entity {
    @EnumValue
    private RoleEnum roleName;

    public enum RoleEnum {
        ADMIN,
        USER
    }
}
