package com.itwill.transaction.spring;

public class Employee {

	private int id;
	private String name;
	private String email;
	private String phone;
	private int salary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String email, String phone, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String toString() {
		return String.format("[%d][%s][%s][%s][%d]", id, name, email, phone,
				salary);
	}

}
