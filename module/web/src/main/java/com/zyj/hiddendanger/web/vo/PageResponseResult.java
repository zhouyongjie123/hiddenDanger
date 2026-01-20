package com.zyj.hiddendanger.web.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PageResponseResult<T> extends ResponseResult<List<T>> {
    /**
     * 总记录数
     */
    private long total;

    /**
     * 当前页码
     */
    private int page;

    /**
     * 每页记录数
     */
    private int size;

    public static <T> PageResponseResult<T> of(List<T> data, long total, int page, int size) {
        PageResponseResult<T> result = new PageResponseResult<>();
        result
                .setTotal(total)
                .setPage(page)
                .setSize(size)
                .setTotal(total)
                .setCode("200")
                .setData(data);
        return result;
    }
}
