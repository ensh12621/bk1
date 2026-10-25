package com.kkh.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkh.backend.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, String>{

	MemberEntity findByUserId(String userId);
	

}
