package com.smr.controller;

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

import com.smr.entity.Country;
import com.smr.service.CountryService;

@RestController
@RequestMapping("/api/country")
@CrossOrigin
public class CountryController {

	@Autowired
	CountryService service;
	

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Country c) {
		String msg = service.save(c);
		
		return ResponseEntity.ok(msg);

	}

	// simple
	@PutMapping("/updatee/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Country c) {
		String msg = service.update(id, c);
		return ResponseEntity.ok(msg);
	}

	// sumit sir
	@PutMapping("/update")
	public ResponseEntity<String> updatee(@RequestBody Country c) {
		String msg = service.updatee(c);
		return ResponseEntity.ok(msg);
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id) {
		String msg = service.deleteById(id);
		return ResponseEntity.ok(msg);

	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id) {
		
		 Country country = service.findById(id);
		 
		 if(country!=null) {
			 return ResponseEntity.ok(country);
		 }else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND) 
                     .body("Country not found with ID: " + id);
		 }
		 
	}

	@GetMapping("/findall")
	public ResponseEntity<Object> findAll() {
		
	       List<Country> clist = service.findAll();
	       
	       if(clist!=null) {
	    	   return ResponseEntity.ok(clist);
	       }else {
	    	   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record Not Found");
	       }
	}

	
	
}
