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

import io.micrometer.common.util.StringUtils;

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
	public ResponseEntity<Map<String, Object>> searchStudent(
			@RequestParam(name = "keyword", required = false) String keyword, 
			@RequestParam(name="deptNo", required = false) String deptNo, 
			@RequestParam(name="orderBy", required = false) String orderBy,
			@RequestParam(name="currentPage", defaultValue = "1") String currentPage,
			@RequestParam(name="pageSize", defaultValue = "5") String pageSize){
		
		
		logger.info("/search endpoint 도달..keyword:[{}] deptno:[{}]", keyword, deptNo);
		Map<String, Object> map = new HashMap<>();
		
		Long deptNoParam = !deptNo.isEmpty() ? Long.valueOf(deptNo) : null;
		String keywordParam = !keyword.isEmpty() ? keyword : null;
		if(orderBy == null) {
			orderBy = "dept";
		}
		
		logger.info("paging - currentPage:[{}], pageSize:[{}]", currentPage, pageSize);
		
		
		List<StudentDTO> list = studentService.searchStudents(keywordParam, deptNoParam, orderBy, Long.valueOf(currentPage), Long.valueOf(pageSize));
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
	
	// search-count?keyword=${keyword}&deptNo=${deptNo
	
	@GetMapping("/search-count")
	public ResponseEntity<Map<String, Object>> searchCount(@RequestParam("keyword") String keyword, @RequestParam("deptNo") String deptNo){
		
		Map<String, Object> map = new HashMap<>();
		Long deptNoLongVal = StringUtils.isBlank(deptNo) ? null : Long.valueOf(deptNo);
		logger.info("search-count endpoint 도달 - keyword[{}] deptNo[{}]", keyword, deptNoLongVal);
		
		Long count = studentService.searchStudentCount(keyword, deptNoLongVal);
		logger.info("갯수만 추출 - {}", count);
		map.put("count", count);
		
		return ResponseEntity.ok(map);
	};
}
