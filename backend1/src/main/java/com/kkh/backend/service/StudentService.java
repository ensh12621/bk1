package com.kkh.backend.service;

import java.util.List;

import com.kkh.backend.dto.DepartmentDTO;
import com.kkh.backend.dto.StudentDTO;

public interface StudentService {

	List<StudentDTO> getAllStudents();

	List<StudentDTO> searchStudents(String keyword, Long deptNo, String orderBy, Long offset, Long pageSize);

	List<DepartmentDTO> getAllDepartments();

	Long searchStudentCount(String keyword, Long deptNo);

}
