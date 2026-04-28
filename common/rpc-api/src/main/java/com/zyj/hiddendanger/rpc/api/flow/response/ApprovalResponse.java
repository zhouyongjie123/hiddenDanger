package com.zyj.hiddendanger.rpc.api.flow.response;

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
public class ApprovalResponse implements Serializable {
    /**
     * 是否结束
     */
    private boolean isEnd;

    @Serial
    private static final long serialVersionUID = 1L;
}
