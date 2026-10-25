package com.kkh.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/student")
@RestController
public class StudentController {

	
	@GetMapping("/list")
	public ResponseEntity<Map<String, String>> studentList(){
		Map<String, String> map = new HashMap<>();
		
		map.put("result", "success");
		return ResponseEntity.ok(map);
	}
}
