package com.kkh.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity {

	@Id
	@Column(name="DEPTNO")
	private Long deptNo;
	
	private String dname;
	
	private String build;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "part")
	private DepartmentEntity part;
	
	@OneToMany(mappedBy = "part", cascade = CascadeType.ALL)
	private List<DepartmentEntity> children = new ArrayList<>();
	
	
}
