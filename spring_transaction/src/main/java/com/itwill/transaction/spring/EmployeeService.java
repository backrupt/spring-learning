package com.itwill.transaction.spring;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public interface EmployeeService {
	public abstract void registerEmployee(Employee emp);
	public abstract void deleteEmployee(int id);
	public abstract void udpateEmployee(Employee emp);
	public abstract List<Employee> getEmps();
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
	public abstract void increaseSalaryForAllUnCheckedException();	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
	public abstract void increaseSalaryForAllCheckedException() throws Exception;
}