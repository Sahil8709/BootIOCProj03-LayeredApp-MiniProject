//Model class
package com.nt.model;

import lombok.Data;

@Data
public class Employee {
	private Integer empno;
	private String ename;
	private String job;
	private Double salary;
	private Integer deptno;
	private Double grossSalery;
	private Double netSalary;

}
