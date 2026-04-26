package com.zyj.hiddendanger.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RpcPageResult<T> implements Serializable {
    /**
     * 当前页数据
     */
    private List<T> records;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 每页条数
     */
    private Long pageSize;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 总页数
     */
    private Long pages;

    @Serial
    private static final long serialVersionUID = 1L;
}
