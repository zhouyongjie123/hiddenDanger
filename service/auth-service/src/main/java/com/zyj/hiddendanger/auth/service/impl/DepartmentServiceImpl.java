package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.database.util.PageUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentPageQueryDTO;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;
import com.zyj.hiddendanger.rpc.annotation.RpcReference;
import com.zyj.hiddendanger.rpc.api.risk.response.HiddenRiskDepartmentStatisticResponse;
import com.zyj.hiddendanger.rpc.api.risk.service.HiddenRiskStatisticFacadeService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    private final UserMapper userMapper;

    @RpcReference
    private HiddenRiskStatisticFacadeService hiddenRiskStatisticFacadeService;

    @Resource
    private Cache<String, String> userNameCache;

    @Override
    public List<DepartmentSelectionVO> getSelectionVo() {
        return departmentMapper.getSelectionVO();
    }

    @Override
    public Page<DepartmentInfoVO> page(DepartmentPageQueryDTO dto) {
        String name = dto.getName();
        String leaderId = dto.getLeaderId();
        String status = dto.getStatus();
        Long current = dto.getCurrent();
        Long pageSize = dto.getPageSize();
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<Department>()
                .like(StringUtils.hasText(name), Department::getDepartmentName, name)
                .eq(StringUtils.hasText(leaderId), Department::getLeaderId, leaderId)
                .eq(StringUtils.hasText(status), Department::getStatus, status);
        Page<Department> pageResult = departmentMapper.selectPage(Page.of(current, pageSize), queryWrapper);

        List<DepartmentInfoVO> result = getDepartmentInfoVO(pageResult.getRecords());

        return PageUtil.pageConvert(pageResult, result);
    }

    private List<DepartmentInfoVO> getDepartmentInfoVO(List<Department> departments) {
        Map<String, HiddenRiskDepartmentStatisticResponse> hiddenRiskDepartmentStatistic = hiddenRiskStatisticFacadeService.getHiddenRiskDepartmentStatistic(
                departments.stream().map(Department::getId).toList());

        return departments.stream().map(department -> {
            HiddenRiskDepartmentStatisticResponse response = hiddenRiskDepartmentStatistic.get(
                    department.getId());
            String leaderPhoneNumber = "";
            Long userCount = 0L;
            Long totalHiddenRiskCount = response.getTotalHiddenRiskCount();
            Long closedHiddenRiskCount = response.getClosedHiddenRiskCount();
            Long waitRectifyHiddenRiskCount = response.getWaitRectifyHiddenRiskCount();
            String leaderName = "";
            return department.toDepartmentInfoVO(
                    leaderName, leaderPhoneNumber, userCount, totalHiddenRiskCount, closedHiddenRiskCount,
                    waitRectifyHiddenRiskCount);
        }).toList();
    }

    @Override
    public List<UserSelectionVO> getLeaderSelectionVO() {
        List<String> allLeaderId = departmentMapper.getAllLeaderId();
        return userMapper
                .selectBatchIds(allLeaderId)
                .stream()
                .map(user -> new UserSelectionVO(user.getId(), user.getRealName()))
                .toList();
    }
}




