package com.zyj.hiddendanger.model.service.auth.dto;

import com.zyj.hiddendanger.database.BasePageQueryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Accessors(chain = true)
public class DepartmentPageQueryDTO extends BasePageQueryDTO {
}
