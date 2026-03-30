package com.zyj.hiddendanger.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BasePageQueryDTO {
    private Long current = 1L;

    private Long pageSize = 10L;
}
