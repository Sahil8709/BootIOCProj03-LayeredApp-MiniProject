package com.nt;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.controller.EmployeeOperationController;
import com.nt.model.Employee;

@SpringBootApplication
public class BootIocProj03LayeredAppMiniProjectApplication {

	public static void main(String[] args) {
		//get IOC container
		ApplicationContext ctx = SpringApplication.run(BootIocProj03LayeredAppMiniProjectApplication.class, args);
		//get controller class obj ref
		//EmployeeOperationsController controller=ctx.getBean("empController",EmployeeOperationsController.class);
		EmployeeOperationController controller=ctx.getBean("empController",EmployeeOperationController.class);
		//READ inputs from enduser
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter desg 1");
		String desg1=sc.next();
		System.out.println("Enter desg 2");
		String desg2=sc.next();
		System.out.println("Enter desg 3");
		String desg3=sc.next();
		
		//invoke the b.method
		try {
			List<Employee>list=controller.processEmployeesDesgs(desg1,desg2,desg3);
			list.forEach(emp->{
				System.out.println(emp);
			});
		}//try
		catch(Exception e ) {
			e.printStackTrace();
			System.out.println("Internal problem... try again");
			}
		//close the container
		((ConfigurableApplicationContext) ctx).close();
		}
	}


