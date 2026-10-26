package com.kkh.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO {


	private Long deptno;
	
	private String build;
	private String dname;
	
	private String partDname;
}
