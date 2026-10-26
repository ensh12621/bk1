package com.kkh.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkh.backend.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

	List<StudentEntity> findByNameContaining(String keyword);
	

}
