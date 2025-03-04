package com.smr.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smr.entity.Country;

@Repository
public class CountryDao {

	@Autowired
	SessionFactory sf;

	public String save(Country c) {

		Session ss = null;
		Transaction tx = null;
		String msg = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			ss.persist(c);

			tx.commit();

			msg = "data insert successfully";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}

			e.printStackTrace();

		} finally {

			if (ss != null) {
				ss.close();
			}
		}

		return msg;
	}

	// simple
	public String update(Integer id, Country c) {

		Session ss = null;
		Transaction tx = null;
		String msg = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			Country country = ss.get(Country.class, id);

			if (c.getCname() != null) {
				country.setCname(c.getCname());
			}

			ss.merge(country);

			tx.commit();

			msg = "Country update successfully";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}

		return msg;

	}

	// sumit Sir
	public String updatee(Country c) {

		Session ss = null;
		Transaction tx = null;
		String msg = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			if (c.getCid() != 0) {
				Country country = ss.get(Country.class, c.getCid());

				if (c.getCname() != null) {
					country.setCname(c.getCname());
				}

				ss.merge(country);
			}

			tx.commit();

			msg = "Country update successfully";

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}

		return msg;

	}

	public String deleteById(Integer id) {

		Session ss = null;
		Transaction tx = null;
		String msg = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			if(id!=null) {
			Country country = ss.get(Country.class, id);
			
			ss.remove(country);
			}

			tx.commit();

			msg = "Country delete successfully";
		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {

			if (ss != null) {
				ss.close();
			}
		}

		return msg;

	}

	public Country findById(Integer id) {
		Session ss = null;
		Transaction tx = null;
		Country country = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			if(id!=null) {
			country = ss.get(Country.class, id);
			}
			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
		return country;

	}

	public List<Country> findAll() {

		Session ss = null;
		Transaction tx = null;

		List<Country> clist = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			String hql = "from Country";
			Query<Country> query = ss.createQuery(hql, Country.class);
			clist = query.list();
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (ss != null) {	
				ss.close();
			}
		}

		return clist;

	}

}
