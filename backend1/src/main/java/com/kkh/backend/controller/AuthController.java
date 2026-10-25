package com.kkh.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.dto.LoginDTO;
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
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDTO dto){
		logger.info("login id:{} / password: {}", dto.getUserId(), dto.getUserPwd());
		
		Map<String, String> map = new HashMap<>();
		
		Map<String, String> keys = authService.login(dto);
		
		if(keys != null) {
			map.put("result", "success");
			map.put("accessToken", keys.get("accessToken"));
			map.put("refreshToken", keys.get("refreshToken"));
			
		}else {
			map.put("result", "failed");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
		}
		
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<Map<String, String>> refreshToken(@RequestParam("refreshToken") String refreshToken){
		Map<String, String> map = new HashMap<>();

		if(authService.validateToken(refreshToken)) {
			logger.info("refresh 유효. jwt 발급신청 시작");
			
			String userId = authService.getUserIdFromToken(refreshToken);
			String token = authService.generateToken(userId, 1000 * 30);
			
			map.put("accessToken", token);
			
		}else {
			logger.info("refresh 유효성 오류");
			map.put("result", "failed");
		}
		
		
		return ResponseEntity.ok(map);
	}
}
