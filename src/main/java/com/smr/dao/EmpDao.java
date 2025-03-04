package com.smr.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smr.entity.Employee;

@Repository
public class EmpDao {

	@Autowired
	SessionFactory sf;

	public String save(Employee emp) {

		Session ss = null;
		Transaction tx = null;
		String msg = null;
		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			ss.persist(emp);

			tx.commit();
			msg = "employee added successfully";
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

	public String update(Employee emp) {

		Session ss = null;
		Transaction tx = null;
		Employee employee = null;
		String msg = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			employee = ss.get(Employee.class, emp.getId());

			if (emp.getName() != null) {
				employee.setName(emp.getName());
			}
			if (emp.getStatus() != null) {
				employee.setStatus(emp.getStatus());
			}
			if (emp.getDepartment() != null) {
				employee.setDepartment(emp.getDepartment());
			}
			if (emp.getMobileno() != null) {
				employee.setMobileno(emp.getMobileno());
			}
			if (emp.getEmailid() != null) {
				employee.setEmailid(emp.getEmailid());
			}
			if (emp.getCreatedBy() != null) {
				employee.setCreatedBy(emp.getCreatedBy());
			}
			if (emp.getCreatedDate() != null) {
				employee.setCreatedDate(emp.getCreatedDate());
			}
			if (emp.getUpdatedby() != null) {
				employee.setUpdatedby(emp.getUpdatedby());
			}
			if (emp.getUpdatedDate() != null) {
				employee.setUpdatedDate(emp.getUpdatedDate());
			}
			if (emp.getSalary() != 0) {
				employee.setSalary(emp.getSalary());
			}
			if (emp.getCountry() != null) {
				employee.setCountry(emp.getCountry());
			}

			ss.merge(employee);

			tx.commit();
			msg = "update employee successfully";
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

			Employee employee = ss.get(Employee.class, id);

			ss.remove(employee);

			tx.commit();

			msg = "employee is deleted succesfully";

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

	public Employee findById(Integer id) {

		Session ss = null;
		Transaction tx = null;
		Employee employee = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			employee = ss.get(Employee.class, id);

			tx.commit();
		} catch (Exception e) {

			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			ss.close();
		}

		return employee;
	}

	public List<Employee> findAll() {

		Session ss = null;
		Transaction tx = null;
		List<Employee> list = null;
		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			String hql = "from Employee";
			Query<Employee> emplist = ss.createQuery(hql, Employee.class);
			list = emplist.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			ss.close();
		}

		return list;
	}

	public Employee login(Employee emp) {

		Session ss = null;
		Transaction tx = null;
		Employee empl = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			String hql = "from Employee where mobileno=:mob and emailid=:email";

			Query<Employee> employee = ss.createQuery(hql, Employee.class);
			employee.setParameter("mob", emp.getMobileno());
			employee.setParameter("email", emp.getEmailid());
			empl = employee.uniqueResult();

			tx.commit();

		} catch (Exception e) {

			if (tx != null) {
				tx.commit();
			}
			e.printStackTrace();
		} finally {
			if (ss != null) {
				ss.close();
			}
		}

		return empl;

	}

	public List<Employee> serachSalary(double minSal, double maxSal) {

		Session ss = null;
		Transaction tx = null;
		List<Employee> list = null;

		try {
			ss = sf.openSession();
			tx = ss.beginTransaction();

			String hql = "FROM Employee WHERE salary BETWEEN :minSalary AND :maxSalary";

			Query<Employee> query = ss.createQuery(hql, Employee.class);
			query.setParameter("minSalary", minSal);
			query.setParameter("maxSalary", maxSal);
			list = query.list();
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

		return list;
	}

	public List<Employee> findByCountry(String country) {
        Session ss = null;
        Transaction tx = null;
        List<Employee> emplist = null;

        try {
            ss = sf.openSession();
            tx = ss.beginTransaction();

            String hql="from Employee";
            Query<Employee> query = ss.createQuery(hql,Employee.class);
            List<Employee> list = query.list();
            
            emplist=new ArrayList<Employee>();
            for(Employee elist:list) {
            	if(elist.getCountry().getCname().equals(country)) {
            		emplist.add(elist);
            	}
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
        return emplist;
    }
	
//	public List<Employee> findByCountry(Employee emp) {
//        Session ss = null;
//        Transaction tx = null;
//        List<Employee> emplist = null;
//
//        try {
//            ss = sf.openSession();
//            tx = ss.beginTransaction();
//
//            String hql="from Employee where country=:cnt";
//            Query<Employee> query = ss.createQuery(hql,Employee.class);
//             query.setParameter("cnt", emp.getCountry().getCname());
//              emplist = query.list();
//            
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            if (ss != null) {
//                ss.close();
//            }
//        }
//        return emplist;
//    }
	
	public List<Employee> listbystatus(String status){
		
		Session ss =null;
		Transaction tx = null;
		List<Employee> listbystatus =null;
		
		try {
		ss=sf.openSession();
		tx=ss.beginTransaction();
		
		String hql="from Employee where status=:st";
		
		Query<Employee> query = ss.createQuery(hql,Employee.class);
		query.setParameter("st", status);
		 listbystatus = query.list();
		
		tx.commit();
		}catch (Exception e) {
          if(tx!=null) {
        	  tx.rollback();
          }
          e.printStackTrace();
		}
		finally {
			if(ss!=null) {
				ss.close();
			}
		}
		
		return listbystatus;
		
	}
}
