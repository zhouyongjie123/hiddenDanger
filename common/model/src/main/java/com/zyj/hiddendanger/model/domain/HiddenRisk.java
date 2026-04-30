package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.risk.status.HiddenRiskStatusMachine;
import com.zyj.hiddendanger.model.service.risk.vo.HiddenRiskVO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("hidden_risk")
public class HiddenRisk extends Entity {
    private String name;

    private String description;

    private String location;

    @EnumValue
    private RiskLevel riskLevel;

    @EnumValue
    private RiskType riskType;

    private String responsibleDepartmentId;

    private String responsiblePersonId;

    private Date discoveryTime;

    private Date rectificationDeadline;

    @EnumValue
    private RiskStatus status;

    @EnumValue
    private RiskSource source;

    public enum RiskLevel {
        LOW,// 低
        MIDDLE,// 中
        HIGH,// 高
        DANGER;// 危险
    }


    public enum RiskStatus {
        WAIT_RECTIFY,// 待整改
        RECTIFYING,// 整改中
        RECTIFY_REPORT_SUBMITTED,// 整改报告已提交
        CLOSED,// 已闭环
        ;
    }

    public enum RiskEvent {
        RECTIFY,// 整改
        SUBMIT_RECTIFY_REPORT,// 提交整改报告
        ACCEPT,// 整改通过
        REJECT,// 整改未通过
        ;
    }

    public enum RiskType {
        // 隐患类型
        PEOPLE_UNSAFE_BEHAVIOR,// 人的不安全行为
        MATERIAL_UNSAFE_STATUS,// 物的不安全状态
        MANAGEMENT_DEFECT,// 管理缺陷
        OTHER,// 其他
        ;
    }

    public enum RiskSource {
        // 来源
        DAILY_CHECK,// 日常检查
        EMPLOYEE_REPORT,// 员工上报
        SUPERVISOR_SUPERVISE,// 上级督办
        OTHER,// 其他
        ;
    }


    public HiddenRiskVO toHiddenRiskVO(
            String responsibleDepartmentName, String responsiblePersonName, Boolean isRectify) {
        return new HiddenRiskVO().setName(this.getName())
                                 .setDescription(this.getDescription())
                                 .setLocation(this.getLocation())
                                 .setRiskLevel(this.getRiskLevel())
                                 .setRiskType(this.getRiskType())
                                 .setResponsibleDepartmentName(responsibleDepartmentName)
                                 .setResponsiblePersonName(responsiblePersonName)
                                 .setDiscoveryTime(this.getDiscoveryTime())
                                 .setRectificationDeadline(this.getRectificationDeadline())
                                 .setStatus(this.getStatus())
                                 .setSource(this.getSource())
                                 .setIsRectify(isRectify);
    }

    public void transition(RiskEvent event) {
        this.status = HiddenRiskStatusMachine.getInstance().transition(this.status, event);
    }
}