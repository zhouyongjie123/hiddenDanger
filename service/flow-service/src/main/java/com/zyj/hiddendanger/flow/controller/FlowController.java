package com.zyj.hiddendanger.flow.controller;

import com.zyj.hiddendanger.flow.service.FlowService;
import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.web.vo.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseResult<?> createApprovalProcessByGraph(@RequestBody ApprovalFlowCreateDTO approvalFlowCreateDTO) {
        flowService.createApprovalProcess(approvalFlowCreateDTO);
        return ResponseResult.ok("创建审批流程成功");
    }
}
