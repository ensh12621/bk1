package com.kkh.backend.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kkh.backend.dto.JoinDTO;
import com.kkh.backend.entity.MemberEntity;
import com.kkh.backend.repository.MemberRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class AuthServiceImpl implements AuthService{

	private MemberRepository memberRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public AuthServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	

	@Transactional
	@Override
	public boolean addMember(@Valid JoinDTO dto) {
		UUID uuid = UUID.randomUUID();
		
		MemberEntity member = MemberEntity
								.builder()
									.userPk(uuid.toString())
									.userId(dto.getUserId())
									.userPwd(passwordEncoder.encode(dto.getUserPwd()))
									.build();
		
		return memberRepository.save(member) != null;
	}
	
	
	
}
