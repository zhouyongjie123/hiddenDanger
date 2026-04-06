package com.zyj.hiddendanger.auth.service.impl;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.core.exception.sys.SystemException;
import com.zyj.hiddendanger.core.exception.sys.code.DatabaseExceptionCode;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.database.util.PageUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentAddDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
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

    @Override
    public List<DepartmentInfoVO> getDepartmentInfoVO(List<Department> departments) {
        Map<String, HiddenRiskDepartmentStatisticResponse> hiddenRiskDepartmentStatistic = hiddenRiskStatisticFacadeService.getHiddenRiskDepartmentStatistic(
                departments.stream().map(Department::getId).toList());
        Map<String, Long> userCountByBatchDepartmentId = getDepartmentUserCountByBatchDepartmentId(
                departments.stream().map(Department::getId).toList());

        return departments.stream().map(department -> {
            HiddenRiskDepartmentStatisticResponse response = hiddenRiskDepartmentStatistic.get(department.getId());
            UserInfoDTO userInfoDTO = userInfoDtoCache.get(department.getLeaderId());
            if (userInfoDTO == null) {
                userInfoDTO = userMapper.getUserInfoDTOById(department.getLeaderId());
                ThrowUtil.throwIfNull(userInfoDTO, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
            }
            return department.toDepartmentInfoVO(
                    userInfoDTO.getRealName(), userInfoDTO.getPhoneNumber(),
                    Optional.ofNullable(userCountByBatchDepartmentId.get(department.getId())).orElse(0L),
                    response.getTotalHiddenRiskCount(), response.getClosedHiddenRiskCount(),
                    response.getWaitRectifyHiddenRiskCount());
        }).toList();
    }

    @Override
    public DepartmentInfoVO getDepartmentInfoVO(Department department) {
        return getDepartmentInfoVO(List.of(department)).get(0);
    }

    @Override
    @Transactional(rollbackFor = SystemException.class)
    public void deleteDepartmentAndChild(String departmentId) {
        ThrowUtil.throwIfTrue(!isExist(departmentId), () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
        // 删除该部门及其子部门
        // 先查找该部门下的所有子部门
        List<String> departmentIds = getChildDepartmentIds(departmentId);
        departmentIds.add(departmentId);
        // 删除该部门及其子部门
        ThrowUtil.throwIfTrue(
                departmentMapper.deleteBatchIds(departmentIds) != departmentIds.size(),
                () -> new SystemException(DatabaseExceptionCode.DELETE_ERROR));
    }

    private boolean isExist(String departmentId) {
        return departmentMapper.exists(new LambdaQueryWrapper<Department>().eq(Department::getId, departmentId));
    }

    // 优化点:不要在循环中进行数据库查询,
    private List<String> getChildDepartmentIds(String parentDepartmentId) {
        List<String> result = new ArrayList<>();
        List<Department> departments = departmentMapper.selectList(
                new LambdaQueryWrapper<Department>().eq(Department::getParentDepartmentId, parentDepartmentId));

        // 递归获取子部门
        for (Department department : departments) {
            result.add(department.getId());
            result.addAll(getChildDepartmentIds(department.getId()));
        }
        return result;
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

    @Override
    public DepartmentInfoVO addDepartment(DepartmentAddDTO dto) {
        String parentDepartmentId = dto.getParentDepartmentId();
        String departmentName = dto.getDepartmentName();
        String departmentPath;
        String leaderId = dto.getLeaderId();
        if (!StringUtils.hasText(parentDepartmentId)) {
            parentDepartmentId = null;
        }
        if (!StringUtils.hasText(parentDepartmentId)) {
            departmentPath = "/1";
        } else {
            Department parentDepartment = departmentMapper.selectById(parentDepartmentId);
            ThrowUtil.throwIfNull(parentDepartment, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
            departmentPath = getNextDepartmentPath(parentDepartment.getDepartmentPath());
        }
        Department department = new Department()
                .setParentDepartmentId(parentDepartmentId)
                .setDepartmentName(departmentName)
                .setDepartmentPath(departmentPath)
                .setLeaderId(leaderId)
                .setStatus(Department.Status.ENABLED);
        ThrowUtil.throwIf(
                departmentMapper.insert(department) != 1,
                () -> new SystemException(DatabaseExceptionCode.INSERT_ERROR));
        return getDepartmentInfoVO(department);
    }


    private Map<String, Long> getDepartmentUserCountByBatchDepartmentId(List<String> departmentIds) {
        List<Map<String, Object>> maps = userMapper.selectMaps(Wrappers
                                                                       .query(User.class)
                                                                       .in("department_id", departmentIds)
                                                                       .groupBy("department_id")
                                                                       .select(
                                                                               "department_id",
                                                                               "count(1) as user_count"));
        return maps
                .stream()
                .collect(Collectors.toMap(
                        map -> map.get("department_id").toString(),
                        map -> Long.parseLong(map.get("user_count").toString())));
    }

    public static String getNextDepartmentPath(String path) {
        if (path == null || path.isBlank()) {
            return "/1";
        }

        // 按 / 分割
        List<String> parts = Arrays.stream(path.split("/")).filter(s -> !s.isBlank()).toList();

        if (parts.isEmpty()) {
            return "/1";
        }

        // 取最后一段转数字 +1
        int last = Integer.parseInt(parts.get(parts.size() - 1));
        int next = last + 1;

        // 拼接回去
        return path + "/" + next;
    }
}




