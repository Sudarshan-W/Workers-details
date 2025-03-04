package com.smr.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smr.entity.Registration;

@Repository
public class UserRegiDao {

	@Autowired
	SessionFactory sf;
	
	public String save(Registration regi) {
		
		Session ss = null;
		Transaction tx =null;
		String msg=null;
		
		try {
		ss=sf.openSession();
		tx= ss.beginTransaction();
		
		ss.persist(regi);
		tx.commit();
		msg="registration successful";
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(ss!=null) {
				ss.close();
			}
		}
		
		return msg;
	}
	
	public Registration loginApi(Registration regi) {
		
		Session ss=null;
		Transaction tx =null;
		Registration user =null;
		
		try {
		 ss = sf.openSession();
		 tx = ss.beginTransaction();
		 String hql="from Registration where emailid=:email and password=:pass";
		 
		 Query<Registration> query = ss.createQuery(hql,Registration.class);
		 query.setParameter("email", regi.getEmailid());
		 query.setParameter("pass", regi.getPassword());
		  user = query.uniqueResult();
		 
		 tx.commit();
		}catch (Exception e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if(ss!=null) {
				ss.close();
			}
		}
		
		 
		return user;
	}
}
