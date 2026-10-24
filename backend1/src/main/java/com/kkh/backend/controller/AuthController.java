package com.kkh.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.service.AuthService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	private AuthService authService;
	
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/join")
	public ResponseEntity<Map<String, String>> join(@Valid @RequestBody JoinDTO dto){
		
		logger.info("controller - dto : {}", dto.toString());
		Map<String, String> map = new HashMap<String, String>();
		
		if(authService.addMember(dto)) {
		
			map.put("result", "success");
		}else {
			map.put("result", "failed");
		}
		
		return ResponseEntity.ok(map);
	}
}
