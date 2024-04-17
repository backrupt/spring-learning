package com.itwill.transaction.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImplMyBatis implements EmployeeDao {
	@Autowired
	private SqlSession sqlsession;
	
	
	public int insert(Employee emp) {
		return sqlsession.insert("com.itwill.transaction.spring.EmployeeMapper.insert", emp);
	}

	public int update(Employee emp) {
		return sqlsession.update("com.itwill.transaction.spring.EmployeeMapper.insert", emp);
	}

	public int delete(int id) {
		return sqlsession.delete("com.itwill.transaction.spring.EmployeeMapper.delete", id);
	}

	public List<Employee> list() {
		return sqlsession.selectList("com.itwill.transaction.spring.EmployeeMapper.list");
	}

	public Employee detail(int id) {
		return sqlsession.selectOne("com.itwill.transaction.spring.EmployeeMapper.detail");

	}

	public int increaseSalary(int id, int amount) {
		Map<Object, Object> parameterMap = new HashMap<>();
		parameterMap.put("id", id);
		parameterMap.put("amount", amount);
		return sqlsession.update("com.itwill.transaction.spring.EmployeeMapper.increaseSalary", parameterMap);
	}

}
