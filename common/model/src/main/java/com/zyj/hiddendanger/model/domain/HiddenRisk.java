package com.zyj.hiddendanger.model.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyj.hiddendanger.database.Entity;
import com.zyj.hiddendanger.model.service.auth.vo.HiddenRiskVO;
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

    @Getter
    @AllArgsConstructor
    public enum RiskLevel {
        LOW("1", "低"),
        MIDDLE("2", "中"),
        HIGH("3", "高"),
        DANGER("4", "危险");

        @EnumValue
        private final String code;

        private final String name;

        public static RiskLevel getByCode(String code) {
            for (RiskLevel value : values()) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            throw new RuntimeException("没有对应的枚举值");
        }
    }


    @Getter
    @AllArgsConstructor
    public enum RiskStatus {
        // 1-待整改，2-整改中，3-待验收，4-已闭环，5-已撤销
        WAIT_RECTIFY("1", "待整改"),
        RECTIFYING("2", "整改中"),
        WAIT_ACCEPTANCE("3", "待验收"),
        CLOSED("4", "已闭环"),
        CANCELED("5", "已撤销"),
        ;

        @EnumValue
        private final String code;

        private final String name;

        public static RiskStatus getByCode(String code) {
            for (RiskStatus value : values()) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            throw new RuntimeException("没有对应的枚举值");
        }
    }

    public enum RiskEvent {
        RECTIFY,// 整改
        RECTIFY_COMPLETE,// 整改完成
        REJECT,// 拒绝整改完成
        ACCEPT,// 同意完成整改
        REVOKE,// 撤销该隐患
        ;
    }

    @Getter
    @AllArgsConstructor
    public enum RiskType {
        // 隐患类型：1-人的不安全行为，2-物的不安全状态，3-管理缺陷
        PEOPLE_UNSAFE_BEHAVIOR("1", "人的不安全行为"),
        MATERIAL_UNSAFE_STATUS("2", "物的不安全行为"),
        MANAGEMENT_DEFECT("3", "管理缺陷"),
        OTHER("4", "其他"),
        ;

        @EnumValue
        private final String code;

        private final String name;

        public static RiskType getByCode(String code) {
            for (RiskType value : values()) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            throw new RuntimeException("没有对应的枚举值");
        }
    }

    @Getter
    @AllArgsConstructor
    public enum RiskSource {
        // 来源：1-日常检查，2-员工上报，3-上级督办，4-其他
        DAILY_CHECK("1", "日常检查"),
        EMPLOYEE_REPORT("2", "员工上报"),
        SUPERVISOR_SUPERVISE("3", "上级督办"),
        OTHER("4", "其他"),
        ;

        @EnumValue
        private final String code;

        private final String name;

        public static RiskSource getByCode(String code) {
            for (RiskSource value : values()) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            throw new RuntimeException("没有对应的枚举值");
        }
    }


    public HiddenRiskVO toHiddenRiskVO(String responsibleDepartmentName, String responsiblePersonName) {
        return new HiddenRiskVO().setName(this.getName())
                                 .setDescription(this.getDescription())
                                 .setLocation(this.getLocation())
                                 .setRiskLevel(this.getRiskLevel().getName())
                                 .setRiskType(this.getRiskType().getName())
                                 .setResponsibleDepartmentName(responsibleDepartmentName)
                                 .setResponsiblePersonName(responsiblePersonName)
                                 .setDiscoveryTime(this.getDiscoveryTime())
                                 .setRectificationDeadline(this.getRectificationDeadline())
                                 .setStatus(this.getStatus().getName())
                                 .setSource(this.getSource().getName());
    }
}