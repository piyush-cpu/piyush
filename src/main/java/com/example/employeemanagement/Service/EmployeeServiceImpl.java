package com.example.employeemanagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.Dao.EmployeeDao;
import com.example.employeemanagement.Employee.Employee;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	public EmployeeDao employeeDao;
	@Bean(name = "dataSource")
	public List<Employee> getListOfEmployee() {
		return employeeDao.getListOfEmployee();
	}

	public boolean addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	public boolean updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	public boolean deleteEmployee(String empNo) {
		return employeeDao.deleteEmployee(empNo);
	}

}