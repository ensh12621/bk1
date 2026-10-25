package com.kkh.backend.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.dto.LoginDTO;

import jakarta.validation.Valid;

public interface AuthService {

	boolean addMember(JoinDTO dto);
	Map<String, String> login(@Valid LoginDTO dto);
	boolean validateToken(String token);
	String getUserIdFromToken(String token);
	UserDetails loadUserDetails(String userId);
	String generateToken(String userId, int expirationMilliSecond);
}
	