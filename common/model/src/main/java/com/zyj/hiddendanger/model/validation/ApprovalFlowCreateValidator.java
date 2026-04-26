package com.zyj.hiddendanger.model.validation;

import com.zyj.hiddendanger.model.service.flow.approval.dto.ApprovalFlowCreateDTO;
import com.zyj.hiddendanger.model.validation.annotation.ApprovalFlowCreate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ApprovalFlowCreateValidator implements ConstraintValidator<ApprovalFlowCreate, ApprovalFlowCreateDTO> {
    @Override
    public boolean isValid(
            ApprovalFlowCreateDTO approvalFlowCreateDTO,
            ConstraintValidatorContext context) {
        // 禁用默认消息，自定义错误信息
        context.disableDefaultConstraintViolation();
        Integer dimension = approvalFlowCreateDTO.getGraph().getDimension();
        int length = approvalFlowCreateDTO.getApproverIds().length;
        // 检查审批节点的审批人id是否是图维度-2(减去开始和结束节点)
        return dimension - 2 == length;
    }
}
