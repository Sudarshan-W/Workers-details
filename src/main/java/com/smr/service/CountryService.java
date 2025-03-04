package com.smr.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smr.dao.CountryDao;
import com.smr.entity.Country;

@Service
public class CountryService {

	@Autowired
	CountryDao dao;
	
	public String save(Country c) {
		
		String msg= dao.save(c);
		if(Objects.isNull(msg)) {
			msg="Country Not Added successfully...!";
		}
		
		return msg;
	}
	
	//simple
	public String update(Integer id,Country c) {
		String msg = dao.update(id, c);
		if(Objects.isNull(msg)) {
			msg="Update Not Country successfully";
		}
		return msg;
	}
	
	//sumit sir
	public String updatee(Country c) {
		
		String msg=dao.updatee(c);
		if(Objects.isNull(msg)) {
			msg="Update Not Country successfully";
		}
		return msg;
	}
	
	public String deleteById(Integer id) {
		String msg = dao.deleteById(id);
		if(Objects.isNull(msg)) {
			msg="Country Not Deleted successfully";
		}
		return msg;
	}
	
	public Country findById(Integer id) {
		
		return dao.findById(id);
		
	}
	
	
	public List<Country> findAll(){
		return dao.findAll();
	}
	
	
}
