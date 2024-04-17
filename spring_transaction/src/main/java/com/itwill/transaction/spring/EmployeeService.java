package com.itwill.transaction.spring;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface EmployeeService {
	public abstract void registerEmployee(Employee emp);
	public abstract void deleteEmployee(int id);
	public abstract void udpateEmployee(Employee emp);
	public abstract List<Employee> getEmps();
	public abstract void increaseSalaryForAllUnCheckedException();
	@Transactional(rollbackFor = Exception.class)
	public abstract void increaseSalaryForAllCheckedException() throws Exception;
}