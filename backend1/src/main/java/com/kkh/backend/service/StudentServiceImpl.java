package com.kkh.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kkh.backend.dto.StudentDTO;
import com.kkh.backend.entity.StudentEntity;
import com.kkh.backend.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@Override
	public List<StudentDTO> getAllStudents() {
		List<StudentEntity> list = studentRepository.findAll();
		List<StudentDTO> resultList = new ArrayList<StudentDTO>();
		setDTOListWithEntityList(list, resultList);
		return resultList;
		
	}

	@Override
	public List<StudentDTO> searchStudents(String keyword) {
		List<StudentEntity> list = studentRepository.findByNameContaining(keyword);
		List<StudentDTO> resultList = new ArrayList<StudentDTO>();
		setDTOListWithEntityList(list, resultList);
		return resultList;
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
}
