package com.zyj.hiddendanger.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePageQueryDTO {
    private Long current = 1L;

    private Long pageSize = 10L;
}
