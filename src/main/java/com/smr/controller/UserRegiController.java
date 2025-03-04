package com.smr.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smr.entity.Registration;
import com.smr.service.UserRegiService;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin
public class UserRegiController {

	@Autowired
	UserRegiService service;
	
	@PostMapping("save")
	public ResponseEntity<String> save(@RequestBody Registration regi) {
		String msg= service.save(regi);
		return ResponseEntity.ok(msg);
	}
	
	@PostMapping("login")
	public ResponseEntity<HashMap<String, Object>> login(@RequestBody Registration regi){
		HashMap<String, Object> user = service.loginApi(regi);
		
		return ResponseEntity.ok(user);
		
	}
}
