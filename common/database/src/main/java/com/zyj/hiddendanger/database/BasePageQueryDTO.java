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
    private Long current;

    private Long pageSize;

    public void setCurrent(Long current) {
        if (current == null || current < 1) {
            this.current = 1L;
        } else {
            this.current = current;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize < 1) {
            this.pageSize = 10L;
        } else {
            this.pageSize = pageSize;
        }
    }
}
