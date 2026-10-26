package com.kkh.backend.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kkh.backend.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	List<StudentDTO> searchStudentList(@Param("keyword") String keyword, @Param("deptNo") Long deptNo, @Param("orderBy") String orderBy);
}
