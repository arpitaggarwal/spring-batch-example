package com.test.model;

public final class Employee {

	private final int empid;
	private final String name;

	public Employee(final int empid, final String name) {
		this.empid = empid;
		this.name = name;
	}

	public int getEmpid() {
		return empid;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + "]";
	}

}