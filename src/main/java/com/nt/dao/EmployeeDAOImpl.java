package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.model.Employee;
@Repository("empDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {
	private static final String GET_EMPS_BY_DESGS_="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN(?,?,?)";
	@Autowired
	private DataSource ds;

	@Override
	public List<Employee> getEmpByDesgs(String desg1, String desg2, String desg3) throws Exception {
		List<Employee> list=null;
		try(//get Pooled connection
			Connection con=ds.getConnection();
				//CREATE PREPARED STATEMENT OBJ HAVING THE PRE-COMPILED SQL QUERY
			PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESGS_);
				){
			    
			     // set values to query params
			     ps.setString(1, desg1); ps.setString(2, desg2); ps.setString(3, desg3);
			     
			     
			     try(//execute the Query
			    		 ResultSet rs=ps.executeQuery();){
			    	 
			    	 //process the result object
			    	 list=new ArrayList();
			    	 while(rs.next()) {
			    		 //copy each record into java bean class object
			    		 Employee emp=new Employee();
			    		 emp.setEmpno(rs.getInt(1));
			    		 emp.setEname(rs.getString(2));
			    		 emp.setJob(rs.getString(3));
			    		 emp.setSalary(rs.getDouble(4));
			    		 emp.setDeptno(rs.getInt(5));
			    		 //add each java bean class object list collection
			    		 list.add(emp);
			    	 }//while
			     
			     }//fry2
			     
			    		 
		}//try1
		catch(SQLException se) {
			System.out.println("some DB Problems,wait for sumTime");
			throw se;
		}
		catch(Exception e) {
			System.out.println("some DB Problems,wait for sumTime");
			throw e;
		}
		return list;
	}

}
