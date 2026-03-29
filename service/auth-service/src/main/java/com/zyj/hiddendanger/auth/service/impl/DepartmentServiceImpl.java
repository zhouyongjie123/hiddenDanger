package com.zyj.hiddendanger.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyj.hiddendanger.auth.mapper.UserMapper;
import com.zyj.hiddendanger.core.util.ThrowUtil;
import com.zyj.hiddendanger.model.domain.Department;
import com.zyj.hiddendanger.auth.service.DepartmentService;
import com.zyj.hiddendanger.auth.mapper.DepartmentMapper;
import com.zyj.hiddendanger.model.domain.User;
import com.zyj.hiddendanger.model.service.auth.exception.AuthException;
import com.zyj.hiddendanger.model.service.auth.exception.AuthExceptionCode;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Page<DepartmentVO> page(Long current, Long pageSize) {
        Page<Department> page = this.page(Page.of(current, pageSize));
        List<DepartmentVO> results = page.getRecords().stream().map(record -> {
            String leaderName = userNameCache.get(record.getLeaderId());
            if (leaderName == null){
                // 去查id对应的名字
                User user = userMapper.selectById(record.getLeaderId());
                ThrowUtil.throwIfNull(user, () -> new AuthException(AuthExceptionCode.ID_NOT_EXIST));
                leaderName = user.getRealName();
                userNameCache.put(record.getLeaderId(), leaderName);
            }
            return record.toDepartmentVO(leaderName);
        }).toList();
        Page<DepartmentVO> resultPage = new Page<>();
        BeanUtil.copyProperties(page, resultPage);
        resultPage.setRecords(results);
        return resultPage;
    }
}




