package com.zyj.hiddendanger.auth.mapper;

import com.zyj.hiddendanger.model.domain.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    List<DepartmentSelectionVO> getSelectionVO();

}




