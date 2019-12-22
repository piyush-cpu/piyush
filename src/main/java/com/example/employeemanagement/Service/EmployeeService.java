package com.example.employeemanagement.Service;
import java.util.List;


import com.example.employeemanagement.Employee.Employee;

public interface EmployeeService {
	
	public List<Employee> getListOfEmployee();

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(String empNo);
}