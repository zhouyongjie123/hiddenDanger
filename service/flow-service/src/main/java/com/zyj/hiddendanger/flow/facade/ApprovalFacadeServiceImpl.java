package com.zyj.hiddendanger.flow.facade;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowNodeMapper;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.flow.service.FlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.domain.node.ApprovalFlowNode;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Facade
@Service
@RequiredArgsConstructor
@DubboService
public class ApprovalFacadeServiceImpl implements ApprovalFacadeService {
    private final ApprovalFlowProcessService approvalFlowProcessService;

    private final FlowProcessService flowProcessService;

    private final ApprovalFlowNodeMapper approvalFlowNodeMapper;

    @Override
    @Idempotent(idempotentKey = "#event.getEventId")
    public ApprovalResponse approve(AbstractApprovalFlowEdgeEvent event) {
        String userId = RpcContext.getServerAttachment().getAttachment("userId");
        UserIdContextHolder.set(userId);
        return new ApprovalResponse(approvalFlowProcessService.handleEvent(event));
    }

    @Override
    public void createApprovalProcess(ApprovalFlowCreateDTO dto) {
        flowProcessService.createApprovalProcess(dto);
    }

    @Override
    public List<String> getMyApprovalProcess(String approverId) {
        return approvalFlowNodeMapper.selectList(Wrappers.lambdaQuery(ApprovalFlowNode.class).eq(
                ApprovalFlowNode::getApproverId, approverId
        )).stream().map(ApprovalFlowNode::getApproverId).toList();
    }
}
