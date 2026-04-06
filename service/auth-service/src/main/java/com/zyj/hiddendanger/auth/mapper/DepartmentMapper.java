package com.zyj.hiddendanger.auth.mapper;

import com.zyj.hiddendanger.model.domain.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyj.hiddendanger.model.service.auth.vo.DepartmentSelectionVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    List<DepartmentSelectionVO> getSelectionVO();

    List<String> getAllLeaderId();

    Long getUserCountByDepartmentId(String departmentId);

//    @Select("SELECT get_all_chile_dept_ids(#{id})")
//    List<String> getChildDepartmentIds(@Param("id") String departmentId);
}
