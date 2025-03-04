package com.smr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smr.dao.EmpDao;
import com.smr.entity.Employee;

@Service
public class EmpService {

	@Autowired
	EmpDao dao;
	
	public String save(Employee emp) {
		String msg = dao.save(emp);
	  if(Objects.isNull(msg)) {
		  msg="employee not Added";
	  }
	  return msg;
	}
	
	public String update(Employee emp) {
		String msg = dao.update(emp);
		if(Objects.isNull(msg)) {
			msg="employee not updated";
			
		}
		return msg;
	}
	
	public String deleteById(Integer id) {
		String msg = dao.deleteById(id);
		if(Objects.isNull(msg)) {
			msg="employee are not deleted";
		}
		return msg;
	}
	
	public Employee findById(Integer id) {
		Employee employee = dao.findById(id);
		return employee;
	}
	
	public List<Employee> findAll(){
		List<Employee> list = dao.findAll();
		return list;
	}
	
	
	public HashMap<String, Object> login(Employee emp) {
		
		Employee employee = dao.login(emp);
		
		HashMap<String,Object> map=new HashMap<>();
		
		if(employee!=null) {
			map.put("msg", "Valid User");
			map.put("user", employee);
		}else {
			map.put("msg", "Invalid User");
			map.put("user", employee);
		}
		return map;
	}
	
	public List<Employee> serachSalary(double minSal, double maxSal){
		List<Employee> listsalary = dao.serachSalary(minSal, maxSal);
	
		return listsalary;
	
	}
	
	 public List<Employee> findByCountry(String country) {
		List<Employee> listbyCountry = dao.findByCountry(country);
		
		return listbyCountry;
		
	}
	
//	public List<Employee> findByCountry(Employee emp) {
//                List<Employee> listbycountry = dao.findByCountry(emp);		
//		return listbycountry;
//		
//	}
	 
	 public List<Employee> listbystatus(String status){
		 List<Employee> listbystatus = dao.listbystatus(status);
		 
		 return listbystatus;
	 }
}
