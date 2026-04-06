package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.auth.vo.RoleSelectionVO;
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
@TableName("role")
public class Role extends Entity {
    private String roleName;

    private String roleCode;

    public RoleSelectionVO toRoleSelectionVO() {
        return new RoleSelectionVO().setId(this.getId())
                                    .setRoleName(this.getRoleName());
    }
}
