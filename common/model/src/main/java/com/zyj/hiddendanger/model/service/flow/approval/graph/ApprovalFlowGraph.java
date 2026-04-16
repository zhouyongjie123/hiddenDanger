package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.UnImplementationExceptionCode;
import com.zyj.hiddendanger.model.domain.FlowProcess;
import com.zyj.hiddendanger.model.service.flow.approval.event.AbstractApprovalFlowEdgeEvent;

import java.util.Arrays;

public class ApprovalFlowGraph extends AbstractApprovalFlowGraph {
    public ApprovalFlowGraph(Integer[][] originalGraph, String processName, String businessId) {
        super(originalGraph, processName, businessId);
    }

    @Override
    public Boolean isLegal() {
        // 1.检查V0节点是否只有出度 && Vn节点是否只有入度
        for (int i = 0, j = 0; i < getDimension(); i++, j++) {
            if (this.originalGraph[i][0] != 0 || this.originalGraph[getDimension() - 1][j] != 0) {
                // 检查第0列是否有值,有值就说明存在入度,则不合法
                // 检查最后一列是否有值,有值就说明存在出度,则不合法
                return Boolean.FALSE;
            }
        }

        // 3.检查是否有自回路,有则不合法
        for (int i = 0, j = 0; i < getDimension(); i++, j++) {
            if (this.originalGraph[i][j] != 0) {
                return Boolean.FALSE;
            }
        }
        // 4.检查图中是否只有V0和Vn两个节点只有出度或者只有入度,有则不合法,即该节点的行列都必须有值
        // 起始节点和结束节点不需要判断
        for (int i = 1, j = 1; i < getDimension() - 1; i++, j++) {
            // 取出行
            Integer[] row = this.originalGraph[i];
            // 取出列
            Integer[] column = new Integer[getDimension()];
            for (int k = 0; k < getDimension(); k++) {
                column[k] = this.originalGraph[k][j];
            }
            // 计算行和 列和 判断是否其中有一个为0
            if (Arrays.stream(row).mapToLong(e -> (long) e).sum() == 0L || Arrays
                    .stream(column)
                    .mapToLong(e -> (long) e)
                    .sum() == 0L) {
                return Boolean.FALSE;
            }
        }

        // 5.检查从V0->Vn是否有路径,没有则不合法,同时要考虑存在环的情况(REVOKE),避免出现死循环
        if (!hasPathBFS()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public FlowProcess<AbstractApprovalFlowEdgeEvent> convertToFlowProcess() {
        throw new SystemException(UnImplementationExceptionCode.METHOD_UNIMPLEMENT);
    }
}
