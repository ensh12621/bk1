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

@Table(name="professor")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorEntity {

	@Id
	private Long profno;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String id;
	
	
	@Column(nullable = false)
	private String position;
	
	@Column(nullable = false)
	private Long pay;
	
	@Column(nullable = true)
	private Date hiredate;
	
	@Column(nullable = true)
	private Long bonus;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="deptno")
	private DepartmentEntity department;
	
	@Column(nullable = true)
	private String email;
	
	@Column(nullable = true)
	private String hpage;
	
}
