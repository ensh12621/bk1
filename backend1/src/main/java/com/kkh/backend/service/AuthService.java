package com.kkh.backend.service;

import com.kkh.backend.dto.JoinDTO;

import jakarta.validation.Valid;

public interface AuthService {

	boolean addMember(@Valid JoinDTO dto);

}
	