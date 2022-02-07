package com.devsuperior.bds01.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll() {

		List<Department> listEntity = departmentRepository.findAll(Sort.by("name"));

		List<DepartmentDTO> departmentsDTO = new ArrayList<>();

		listEntity.forEach(department -> departmentsDTO.add(new DepartmentDTO(department)));

		return departmentsDTO;
	}

}
