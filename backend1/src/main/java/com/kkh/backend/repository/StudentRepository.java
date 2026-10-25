package com.kkh.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkh.backend.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
	

}
