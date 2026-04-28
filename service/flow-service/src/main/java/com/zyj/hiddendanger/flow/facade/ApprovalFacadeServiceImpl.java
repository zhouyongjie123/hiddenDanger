package com.zyj.hiddendanger.flow.facade;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.flow.mapper.ApprovalFlowNodeMapper;
import com.zyj.hiddendanger.flow.service.ApprovalFlowProcessService;
import com.zyj.hiddendanger.flow.service.FlowProcessService;
import com.zyj.hiddendanger.model.service.flow.approval.domain.edge.event.AbstractApprovalFlowEdgeEvent;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.rpc.api.flow.request.MyApprovalNodeRequest;
import com.zyj.hiddendanger.rpc.api.flow.response.ApprovalResponse;
import com.zyj.hiddendanger.rpc.api.flow.service.ApprovalFacadeService;
import com.zyj.hiddendanger.rpc.facade.Facade;
import com.zyj.hiddendanger.rpc.response.RpcPageResult;
import com.zyj.hiddendanger.web.infrustructure.idempotent.Idempotent;
import com.zyj.hiddendanger.web.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

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
        UserIdContextHolder.set(RpcContext.getServerAttachment().getAttachment("userId"));
        return new ApprovalResponse(approvalFlowProcessService.handleEvent(event));
    }

    @Override
    public void createApprovalProcess(ApprovalFlowCreateDTO dto) {
        UserIdContextHolder.set(RpcContext.getServerAttachment().getAttachment("userId"));
        flowProcessService.createApprovalProcess(dto);
    }

    @Override
    public RpcPageResult<String> getMyApprovalNode(MyApprovalNodeRequest request) {
        Page<String> page = approvalFlowNodeMapper.getBusinessIdByApproverId(
                new Page<>(request.getCurrent(), request.getPageSize()), request.getApproverId());
        return PageUtil.convert2RpcPageResult(page);
    }
}
