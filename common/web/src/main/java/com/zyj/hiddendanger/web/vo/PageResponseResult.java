package com.zyj.hiddendanger.web.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    private Long total;

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 每页记录数
     */
    private Long size;


    public static <T> PageResponseResult<T> ok(Page<T> page) {
        PageResponseResult<T> result = new PageResponseResult<T>().setTotal(page.getTotal())
                                                                  .setCurrent(page.getCurrent())
                                                                  .setSize(page.getSize());
        result.setCode(SUCCESSFUL_CODE)
              .setMessage("查询成功")
              .setData(page.getRecords());
        return result;
    }
}
