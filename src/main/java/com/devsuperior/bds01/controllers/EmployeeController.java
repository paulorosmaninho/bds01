package com.devsuperior.bds01.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) {

		//Define padrão de size e sort da página
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
		
		Page<EmployeeDTO> pageDTO = employeeService.findAll(pageRequest);

		return ResponseEntity.ok().body(pageDTO);

	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO employeeDTO) {

		employeeDTO = employeeService.insert(employeeDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeeDTO.getId())
				.toUri();

		return ResponseEntity.created(uri).body(employeeDTO);

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
		
		employeeDTO = employeeService.update(id, employeeDTO);
		
		return ResponseEntity.ok().body(employeeDTO);
		
	}
	
}
