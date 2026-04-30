package com.zyj.hiddendanger.model.service.flow.approval.domain.node;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.model.domain.ApprovalRecord;
import com.zyj.hiddendanger.model.domain.FlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.event.ApprovalFlowNodeStatusEventEnum;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.status.ApprovalFlowNodeStatusMachine;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.status.ApprovalStatusEnum;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalFlowNodeVO;
import com.zyj.hiddendanger.model.service.flow.approval.vo.ApprovalRecordVO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 审批节点
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("approval_flow_node")
public class ApprovalFlowNode extends FlowNode {
    /**
     * 特殊的开始节点
     */
    public final static ApprovalFlowNode START = new ApprovalFlowNode();

    /**
     * 特殊的结束节点
     */
    public final static ApprovalFlowNode END = new ApprovalFlowNode();

    static {
        START.setId("-1");
        END.setId("-2");
    }

    /**
     * 审批状态
     */
    private ApprovalStatusEnum status;

    /**
     * 审批人id
     */
    private String approverId;

    @TableField(exist = false)
    private String processId;

    /**
     * 审批记录
     */
    @TableField(exist = false)
    private List<ApprovalRecord> approvalRecords;

    public void transition(ApprovalFlowNodeStatusEventEnum event) {
        this.status = ApprovalFlowNodeStatusMachine.getInstance().transition(this.status, event);
    }

    public ApprovalFlowNodeVO toVO(String approverName, List<ApprovalRecordVO> approvalRecordVOs) {
        return new ApprovalFlowNodeVO()
                .setApprovalFlowNodeId(this.getId())
                .setApproverId(this.approverId)
                .setProcessId(this.processId)
                .setStatus(this.status)
                .setApproverName(approverName)
                .setApprovalRecords(approvalRecordVOs);
    }
}
