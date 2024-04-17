package com.itwill.transaction.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public void registerEmployee(Employee person) {
		employeeDao.insert(person);
	}

	public void deleteEmployee(int idx) {
		employeeDao.delete(idx);
	}

	public void udpateEmployee(Employee emp) {
		employeeDao.update(emp);
	}

	public List<Employee> getEmps() {
		return employeeDao.list();
	}
	
	public void increaseSalaryForAllUnCheckedException() {
		List<Employee> emps = getEmps();
		int random_index = (int)(Math.random()*10);
		for (int i=0;i<emps.size();i++) {
			Employee emp=emps.get(i);
			employeeDao.increaseSalary(emp.getId(), 50);
			if (i == random_index) {
				System.out.println((i+1) +" 번째실행후  throw new RuntimeException()");
				throw new RuntimeException("throw unchecked exception");
			}				
		}
		
	}
	
	public void increaseSalaryForAllCheckedException()throws Exception{
		
		List<Employee> emps = getEmps();
		int random_index = (int)(Math.random()*10)+1;
		for (int i=0;i<emps.size();i++) {
			Employee emp=emps.get(i);
			employeeDao.increaseSalary(						
				emp.getId(), 50);
			if (i == random_index) {
				System.out.println((i+1)+" 번째실행후  throw new Exception()");
				throw new Exception("throw checked exception");
			}				
		}
		
	}

	

	
}
