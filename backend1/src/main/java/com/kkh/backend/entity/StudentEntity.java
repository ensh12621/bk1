package com.kkh.backend.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="stu")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

	@Id
	@Column
	private Long stuno;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String id;
	
	@Column(nullable = true)
	private Long grade;
	
	@Column(nullable = true)
	private String jumin;
	
	@Column(name="birthday", nullable = true)
	private Date birthday;
	
	@Column(nullable = true)
	private String tel;
	
	@Column(nullable = true)
	private Long height;
	
	@Column(nullable = true)
	private Long weight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptno1")
	private DepartmentEntity department;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptno2")
	private DepartmentEntity department2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="profno")
	private ProfessorEntity professor;
	
	
}