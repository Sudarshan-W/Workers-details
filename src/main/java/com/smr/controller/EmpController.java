package com.smr.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smr.entity.Employee;
import com.smr.service.EmpService;

@RestController
@RequestMapping("/api/emp")
@CrossOrigin
public class EmpController {

	@Autowired
	EmpService service;
	

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Employee emp) {
		String msg = service.save(emp);
		return ResponseEntity.ok(msg);
	}

	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Employee emp) {

		String msg = service.update(emp);
		return ResponseEntity.ok(msg);
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {

		String msg = service.deleteById(id);
		return ResponseEntity.ok(msg);
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		Employee employee = service.findById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("employee is not found" + id);
		}
	}
	
	@GetMapping("/findall")
	public ResponseEntity<Object> findAll(){
		
		List<Employee> list = service.findAll();
		if(list!=null) {
			return ResponseEntity.ok(list);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee record not found");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<HashMap<String, Object>> login(@RequestBody Employee emp){
		HashMap<String, Object> employee = service.login(emp);
		
		return ResponseEntity.ok(employee);
		
	}

	@GetMapping("/searchsalary/{minSal}/{maxSal}")
	public ResponseEntity<Object> serachSalary(@PathVariable double minSal,@PathVariable double maxSal){
		
		List<Employee> listsalary = service.serachSalary(minSal, maxSal);
		
			return ResponseEntity.ok(listsalary);
	}
	               
	@GetMapping("/findempbycountry/{country}")
	public ResponseEntity<List<Employee>> findByCountry(@PathVariable String country){
		List<Employee> listbycountry = service.findByCountry(country);
		
			return ResponseEntity.ok(listbycountry);
		
	}
	
//	@GetMapping("/findempbycountry")
//	public ResponseEntity<List<Employee>> findByCountry(@RequestBody Employee emp){
//		List<Employee> listbycountry = service.findByCountry(emp);
//		
//			return ResponseEntity.ok(listbycountry);
//		
//	}
	
	@GetMapping("/findbystatus/{status}")
	public ResponseEntity<List<Employee>> listbystatus(@PathVariable String status){
		 List<Employee> listbystatus = service.listbystatus(status);
		 
		 return ResponseEntity.ok(listbystatus);
		 
	 }
	
}
