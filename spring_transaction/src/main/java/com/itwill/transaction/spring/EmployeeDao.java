package com.itwill.transaction.spring;

import java.util.*;

public interface EmployeeDao {
	public abstract int insert(Employee person);
	public abstract int update(final Employee person);
	public abstract int delete(int idx);
	public abstract List<Employee> list();
	public abstract Employee detail(int idx);
	public abstract int increaseSalary(int idx, int amount);
}