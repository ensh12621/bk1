package com.kkh.backend.service;

import java.util.List;

import com.kkh.backend.dto.StudentDTO;

public interface StudentService {

	List<StudentDTO> getAllStudents();

	List<StudentDTO> searchStudents(String keyword);

}
