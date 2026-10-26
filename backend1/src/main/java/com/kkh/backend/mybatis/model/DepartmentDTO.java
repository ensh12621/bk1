package com.kkh.backend.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

	private Long deptno;
	private String build;
	private String dname;
}
