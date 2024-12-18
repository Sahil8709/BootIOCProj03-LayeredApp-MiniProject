package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.IEmployeeDAO;
import com.nt.model.Employee;
@Service("empService")
public class EmployeeServiceImpl implements IEmployeeMgmtService {
  @Autowired
  private IEmployeeDAO empDAO;

  
	@Override
	public List<Employee> fetchEmployeeByDesgs(String desg1, String desg2, String desg3) throws Exception {
		// convert the reieved signature to UPPERCASE content
		desg1=desg1.toUpperCase();
		desg2=desg2.toUpperCase();
		desg3=desg3.toUpperCase();
		
		//use DAO
		List<Employee>list=empDAO.getEmpByDesgs(desg1,desg2,desg3);
		//sort the object in list collection
		list.sort((t1,t2)->t1.getDeptno().compareTo(t2.getEmpno()));
		//calculate gross salary and netsalary
		list.forEach(emp->{
			emp.setGrossSalery(emp.getSalary()+(emp.getSalary()*0.5));
			emp.setNetSalary(emp.getGrossSalery()-(emp.getGrossSalery()*0.2));
		});
		return list;
	}

}
