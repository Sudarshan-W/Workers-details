package com.smr.service;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smr.dao.UserRegiDao;
import com.smr.entity.Registration;

@Service
public class UserRegiService {
	
	@Autowired
	UserRegiDao dao;
	
	public String save(Registration regi) {
		
		String msg=dao.save(regi);
		if(Objects.isNull(msg)) {
			msg="registration not successful";
		}
		return msg;
	}

	public HashMap<String, Object> loginApi(Registration regi) {
		Registration user = dao.loginApi(regi);
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		if(user!=null) {
			map.put("msg", "valid user");
			map.put("user", user);
		}else {
			map.put("msg", "Invalid user");
			map.put("user", user);
		}
		
		return map;
	}
	
}
