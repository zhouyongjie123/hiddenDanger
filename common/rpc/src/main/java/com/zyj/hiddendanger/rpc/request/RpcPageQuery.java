package com.zyj.hiddendanger.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RpcPageQuery implements Serializable {

    private Long current = 1L;

    private Long pageSize = 10L;

    @Serial
    private static final long serialVersionUID = 1L;
}
