package com.zyj.hiddendanger.rpc.api.flow.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApprovalResponse {
    /**
     * 是否结束
     */
    private boolean isEnd;
}
