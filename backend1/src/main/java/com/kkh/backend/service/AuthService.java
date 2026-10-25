package com.kkh.backend.service;

import java.util.Map;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.dto.LoginDTO;

import jakarta.validation.Valid;

public interface AuthService {

	boolean addMember(JoinDTO dto);
	Map<String, String> login(@Valid LoginDTO dto);
}
	