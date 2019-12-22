package com.example.employeemanagement.Dao;

import java.util.List;

import com.example.employeemanagement.Employee.Employee;

public interface EmployeeDao {
	public List<Employee> getListOfEmployee();

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(String empNo);
}