package com.example.employeemanagement.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.employeemanagement.Employee.Employee;
import com.gurugubelli.util.GurugubelliUtil;


@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	
	@Autowired(required=true)
	public DataSource dataSource;
	
	public List<Employee> getListOfEmployee() {
		List<Employee> employeesList = new ArrayList<Employee>();
		try {
			
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GurugubelliUtil.SELECT_EMP_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Employee emp = new Employee();
				emp.setEmpNo(GurugubelliUtil.EMP_ID_PATTERN + resultSet.getString("EMP_NO"));
				emp.setFirstName(resultSet.getString("FIRST_NAME"));
				emp.setLastName(resultSet.getString("LAST_NAME"));
				emp.setDeptName(resultSet.getString("DEPT_NAME"));
				employeesList.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeesList;
	}

	public boolean addEmployee(Employee employee) {
		if(employee != null) {
			try {
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GurugubelliUtil.INSERT_EMP_QUERY);
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getDeptName());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean updateEmployee(Employee employee) {
		if(employee != null) {
			try {
				System.out.println(employee.toString());
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GurugubelliUtil.UPDATE_EMP_QUERY);
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getDeptName());
				preparedStatement.setInt(4, GurugubelliUtil.getEmpId(employee.getEmpNo()));
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean deleteEmployee(String empNo) {
		if(empNo != null) {
			try {
				Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GurugubelliUtil.DELETE_EMP_QUERY);
				preparedStatement.setInt(1, GurugubelliUtil.getEmpId(empNo));
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
