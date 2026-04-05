package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.database.util.PageUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentPageQueryDTO;
import com.zyj.hiddendanger.model.service.auth.dto.UserInfoDTO;
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

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    private final UserMapper userMapper;

    @RpcReference
    private HiddenRiskStatisticFacadeService hiddenRiskStatisticFacadeService;

    @Resource
    private Cache<String, String> userNameCache;

    @Resource
    private Cache<String, UserInfoDTO> userInfoDtoCache;

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
        Map<String, Long> userCountByBatchDepartmentId = getDepartmentUserCountByBatchDepartmentId(
                departments.stream().map(Department::getId).toList());

        return departments.stream().map(department -> {
            HiddenRiskDepartmentStatisticResponse response = hiddenRiskDepartmentStatistic.get(department.getId());
            UserInfoDTO userInfoDTO = userInfoDtoCache.get(department.getLeaderId());
            if (userInfoDTO == null) {
                userInfoDTO = userMapper.getUserInfoById(department.getLeaderId());
                ThrowUtil.throwIfNull(userInfoDTO, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
            }
            return department.toDepartmentInfoVO(
                    userInfoDTO.getRealName(), userInfoDTO.getPhoneNumber(),
                    userCountByBatchDepartmentId.get(department.getId()),
                    response.getTotalHiddenRiskCount(),
                    response.getClosedHiddenRiskCount(),
                    response.getWaitRectifyHiddenRiskCount());
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

    private Map<String, Long> getDepartmentUserCountByBatchDepartmentId(List<String> departmentIds) {
        List<Map<String, Object>> maps = userMapper.selectMaps(Wrappers
                                                                       .query(User.class)
                                                                       .in("department_id", departmentIds)
                                                                       .groupBy("department_id")
                                                                       .select(
                                                                               "department_id",
                                                                               "count(1) as user_count"));
        return maps.stream().collect(Collectors.toMap(
                map -> map.get("department_id").toString(),
                map -> Long.parseLong(map.get("user_count").toString())
        ));
    }
}




