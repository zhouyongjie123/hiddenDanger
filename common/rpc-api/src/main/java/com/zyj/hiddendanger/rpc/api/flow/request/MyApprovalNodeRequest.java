package com.zyj.hiddendanger.rpc.api.flow.request;

import com.zyj.hiddendanger.rpc.request.RpcPageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyApprovalNodeRequest extends RpcPageQuery {
    private String approverId;
}
