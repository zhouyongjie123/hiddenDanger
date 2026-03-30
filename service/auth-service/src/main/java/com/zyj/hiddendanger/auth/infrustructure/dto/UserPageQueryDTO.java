package com.zyj.hiddendanger.auth.infrustructure.dto;

import com.zyj.hiddendanger.database.BasePageQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserPageQueryDTO extends BasePageQueryDTO {
    private String departmentId;

    private String roleId;
}
