package com.zyj.hiddendanger.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BasePageQueryDTO {
    private Long current = 1L;

    private Long pageSize = 10L;
}
