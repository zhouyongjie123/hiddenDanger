package com.zyj.hiddendanger.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyj.hiddendanger.model.domain.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentAddDTO;
import com.zyj.hiddendanger.model.service.auth.dto.DepartmentPageQueryDTO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentInfoVO;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import com.zyj.hiddendanger.model.service.auth.vo.UserSelectionVO;

import java.util.List;

public interface DepartmentService extends IService<Department> {
    List<DepartmentSelectionVO> getSelectionVo();

    Page<DepartmentInfoVO> page(DepartmentPageQueryDTO dto);

    List<UserSelectionVO> getLeaderSelectionVO();

    DepartmentInfoVO addDepartment(DepartmentAddDTO dto);
}
