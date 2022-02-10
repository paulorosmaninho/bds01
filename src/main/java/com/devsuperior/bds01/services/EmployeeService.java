package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {

		Page<Employee> pageEntity = employeeRepository.findAll(pageable);

		return pageEntity.map(element -> new EmployeeDTO(element));
	}

	@Transactional
	public EmployeeDTO insert(EmployeeDTO employeeDTO) {

		Employee entity = new Employee();

		entity.setName(employeeDTO.getName());
		entity.setEmail(employeeDTO.getEmail());
		entity.setDepartment(new Department(employeeDTO.getDepartmentId(), null));

		employeeRepository.save(entity);

		return new EmployeeDTO(entity);

	}

	// @Transactional
	public EmployeeDTO update(Long id, EmployeeDTO employeeDTO) {

		Employee employee = employeeRepository.getOne(id);

		Department department = departmentRepository.getOne(employeeDTO.getDepartmentId());

		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setDepartment(department);

		employee = employeeRepository.save(employee);

		return new EmployeeDTO(employee);

	}

}
