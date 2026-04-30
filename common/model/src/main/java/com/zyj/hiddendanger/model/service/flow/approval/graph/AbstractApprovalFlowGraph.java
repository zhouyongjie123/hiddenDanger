package com.zyj.hiddendanger.model.service.flow.approval.graph;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.exception.FlowException;
import com.zyj.hiddendanger.model.service.flow.exception.FlowExceptionCode;
import com.zyj.hiddendanger.model.service.flow.infrustructure.FlowGraph;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public abstract class AbstractApprovalFlowGraph implements FlowGraph<AbstractApprovalFlowEdgeEvent> {
    /**
     * 审批通过 - 出边值
     */
    public static final int APPROVE = 0b01;

    /**
     * 审批拒绝 - 出边值
     */
    public static final int REJECT = 0b10;

    /**
     * 中间节点必须的出度和：通过+拒绝
     */
    public static final int MIDDLE_NODE_OUT_SUM = APPROVE + REJECT;

    /**
     * 图
     */
    @NotNull(message = "图不能为空")
    protected Integer[][] originalGraph;

    /**
     * 维度
     */
    @JsonIgnore
    protected Integer dimension;

    public AbstractApprovalFlowGraph(Integer[][] originalGraph) {
        ThrowUtil.throwIfFalse(isLegal(originalGraph), () -> new FlowException(FlowExceptionCode.ILLEGAL_GRAPH));
        this.originalGraph = originalGraph;
        this.dimension = originalGraph.length;
    }

    protected abstract Boolean isLegal(Integer[][] originalGraph);

    @Serial
    private static final long serialVersionUID = 1L;
}
