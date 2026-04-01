package com.zyj.hiddendanger.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageResult<T> {
    // 当前页数据
    private List<T> records;

    // 每页条数
    private Long pageSize;

    // 当前页码
    private Long current;

    // 总页数
    private Long total;
}
