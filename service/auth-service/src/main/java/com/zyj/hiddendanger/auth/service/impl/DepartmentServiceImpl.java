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
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
        implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    private final UserMapper userMapper;

    @Resource
    private Cache<String, String> userNameCache;

    @Override
    public Page<DepartmentInfoVO> page(Long current, Long pageSize) {
        Page<Department> page = this.page(Page.of(current, pageSize));
        List<DepartmentInfoVO> results = page.getRecords().stream().map(record -> {
            String leaderName = userNameCache.get(record.getLeaderId());
            if (leaderName == null) {
                // 去查id对应的名字
                User user = userMapper.selectById(record.getLeaderId());
                ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
                leaderName = user.getRealName();
                userNameCache.put(record.getLeaderId(), leaderName);
            }
            return record.toDepartmentInfoVO(leaderName);
        }).toList();
        return PageUtil.pageConvert(page, results);
    }

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
        List<DepartmentInfoVO> result = pageResult.getRecords().stream().map(record -> {
            String s = userNameCache.get(record.getLeaderId());
            if (!StringUtils.hasText(s)) {
                User user = userMapper.selectById(record.getLeaderId());
                ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
                userNameCache.put(record.getLeaderId(), user.getRealName());
                s = user.getRealName();
            }
            return record.toDepartmentInfoVO(s);
        }).toList();
        return PageUtil.pageConvert(pageResult, result);
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




