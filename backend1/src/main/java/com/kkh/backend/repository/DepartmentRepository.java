package com.kkh.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkh.backend.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{
	

}
