package com.kkh.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.backend.dto.DepartmentDTO;
import com.kkh.backend.dto.StudentDTO;
import com.kkh.backend.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> studentList(){
		Map<String, Object> map = new HashMap<>();
		List<StudentDTO> list = studentService.getAllStudents();
		
		map.put("list", list);
		map.put("result", "success");
		
		logger.info("리스트 요소 갯수: " + list.size());
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, Object>> searchStudent(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name="deptNo", required = false) String deptNo, @RequestParam(name="orderBy", required = false) String orderBy){
		
		logger.info("/search endpoint 도달..keyword:[{}] deptno:[{}]", keyword, deptNo);
		Map<String, Object> map = new HashMap<>();
		
		Long deptNoParam = !deptNo.isEmpty() ? Long.valueOf(deptNo) : null;
		String keywordParam = !keyword.isEmpty() ? keyword : null;
		if(orderBy == null) {
			orderBy = "dept";
		}
		
		
		List<StudentDTO> list = studentService.searchStudents(keywordParam, deptNoParam, orderBy);
		logger.info("검색어: [{}]", keyword);
		map.put("list", list);
		map.put("result", "success");
		
		logger.info("리스트 요소 갯수: " + list.size());
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/department-list")
	public ResponseEntity<Map<String, Object>> departmentList(){
		logger.info("department-list endpoint 도달");
		Map<String, Object> map = new HashMap<>();
		List<DepartmentDTO> list = studentService.getAllDepartments();
		if(list.size() > 0) {
			logger.info("데이터 조회 데이터 존재..");
			
			map.put("result", "success");
			map.put("list", list);
		}else {
			logger.info("조회된 데이터 0개.");
			map.put("result", "failed");
		}
		
		return ResponseEntity.ok(map);
	}
	
}
