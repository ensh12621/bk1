package com.kkh.backend.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kkh.backend.dto.DepartmentDTO;

@Mapper
public interface DepartmentMapper {
	List<DepartmentDTO> selectDepartmentList();
}
