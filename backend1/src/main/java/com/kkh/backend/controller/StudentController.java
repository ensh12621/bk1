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
	public ResponseEntity<Map<String, Object>> searchStudent(@RequestParam("keyword") String keyword){
		Map<String, Object> map = new HashMap<>();
		List<StudentDTO> list = studentService.searchStudents(keyword);
		logger.info("검색어: [{}]", keyword);
		map.put("list", list);
		map.put("result", "success");
		
		logger.info("리스트 요소 갯수: " + list.size());
		
		return ResponseEntity.ok(map);
	}
}
