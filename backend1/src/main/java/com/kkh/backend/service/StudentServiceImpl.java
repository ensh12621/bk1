package com.kkh.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kkh.backend.dto.DepartmentDTO;
import com.kkh.backend.dto.StudentDTO;
import com.kkh.backend.entity.StudentEntity;
import com.kkh.backend.mybatis.mapper.DepartmentMapper;
import com.kkh.backend.mybatis.mapper.StudentMapper;
import com.kkh.backend.repository.DepartmentRepository;
import com.kkh.backend.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private DepartmentMapper departmentMapper;
	private StudentMapper studentMapper;
	private StudentRepository studentRepository;
	private DepartmentRepository departmentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository, DepartmentRepository departmentRepository, DepartmentMapper departmentMapper, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.departmentRepository = departmentRepository;
		this.departmentMapper = departmentMapper;
		this.studentMapper = studentMapper;
	}
	
	@Override
	public List<StudentDTO> getAllStudents() {
		List<StudentEntity> list = studentRepository.findAll();
		List<StudentDTO> resultList = new ArrayList<StudentDTO>();
		setDTOListWithEntityList(list, resultList);
		return resultList;
		
	}

	@Override
	public List<StudentDTO> searchStudents(String keyword, Long deptNo, String orderBy) {
		
		List <StudentDTO> list = studentMapper.searchStudentList(keyword, deptNo, orderBy);
		logger.info("학생 리스트 추출중.. {}개", list.size());
		return list;
//		List<StudentEntity> list = studentRepository.findByNameContaining(keyword);
//		List<StudentDTO> resultList = new ArrayList<StudentDTO>();
//		setDTOListWithEntityList(list, resultList);
	}

	private void setDTOListWithEntityList(List<StudentEntity> list, List<StudentDTO> resultList) {

		list.forEach(item -> {
			StudentDTO dto = StudentDTO
								.builder()
								.stuno(item.getStuno())
								.name(item.getName())
								.id(item.getId())
								.grade(item.getGrade())
								.jumin(item.getJumin())
								.birthday(item.getBirthday())
								.tel(item.getTel())
								.height(item.getHeight())
								.weight(item.getWeight())
								.deptName(item.getDepartment().getDname())
								.build();

			
			if(item.getProfessor() != null) {
				dto.setProfessorName(item.getProfessor().getName());
			}
			
			if(item.getDepartment2() != null) {
				dto.setDeptName2(item.getDepartment2().getDname());
			}
			
			resultList.add(dto);
		});
	}

	@Override
	public List<DepartmentDTO> getAllDepartments() {
		return departmentMapper.selectDepartmentList();
	}
}
