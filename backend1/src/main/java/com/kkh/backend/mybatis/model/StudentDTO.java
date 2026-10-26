package com.kkh.backend.mybatis.model;

import java.util.Date;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

	private Long stuno;
	private String name;
	private String id;
	private Long grade;
	private String jumin;
	private Date birthday;
	private String tel;
	
	private Long height;
	private Long weight;
	
	@JoinColumn(name="deptno1")
	private String deptName;
	
	private String deptName2;
	
	private String professorName;
}
