package com.zyj.hiddendanger.risk.service.impl;

import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.core.id.IdGenerator;
import com.zyj.hiddendanger.model.service.flow.approval.event.AcceptApprovalEvent;
import com.zyj.hiddendanger.model.service.flow.approval.event.RejectApprovalEvent;
import com.zyj.hiddendanger.model.service.risk.dto.HiddenRiskApprovalDTO;
import com.zyj.hiddendanger.mq.MessageHeaderConstant;
import com.zyj.hiddendanger.risk.service.HiddenRiskApprovalService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiddenRiskApprovalServiceImpl implements HiddenRiskApprovalService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private IdGenerator<String> idGenerator;

    @Override
    public void approvalAccept(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
        Message<AcceptApprovalEvent> message = MessageBuilder.withPayload(
                                                                     new AcceptApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage))
                                                             .setHeader(
                                                                     MessageHeaderConstant.USER_ID,
                                                                     UserIdContextHolder.get())
                                                             .build();
        rocketMQTemplate.send(message);
    }

    @Override
    public void approvalReject(HiddenRiskApprovalDTO dto) {
        String hiddenRiskId = dto.getHiddenRiskId();
        String approvalMessage = dto.getApprovalMessage();
        Message<RejectApprovalEvent> message = MessageBuilder.withPayload(
                                                                     new RejectApprovalEvent(hiddenRiskId, idGenerator.generate(), approvalMessage))
                                                             .setHeader(
                                                                     MessageHeaderConstant.USER_ID,
                                                                     UserIdContextHolder.get())
                                                             .build();
        rocketMQTemplate.send(message);
    }
}
