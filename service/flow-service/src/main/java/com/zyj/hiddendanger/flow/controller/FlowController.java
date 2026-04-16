package com.zyj.hiddendanger.flow.controller;

import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.UnImplementationExceptionCode;
import com.zyj.hiddendanger.flow.service.FlowService;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/process")
public class FlowController {
    private final FlowService flowService;

    /**
     * 创建审批流程
     */
    @PostMapping("/create/approval")
    public ResponseResult<?> createApprovalProcessByGraph(ApprovalFlowCreateDTO approvalFlowCreateDTO) {
        flowService.createApprovalProcess(approvalFlowCreateDTO);
        throw new SystemException(UnImplementationExceptionCode.METHOD_UNIMPLEMENT);
    }
}
