package com.epam.testapp.database;

import java.util.List;

import com.epam.testapp.exception.CannotGeTotalCountEmployeesException;
import com.epam.testapp.exception.CannotGetListEmployeesException;
import com.epam.testapp.model.Employee;

public interface IEmployeeDAO {

	List<Employee> getList(int start, int range)
			throws CannotGetListEmployeesException;

	Long getTotalCountEmployees() 
			throws CannotGeTotalCountEmployeesException;
}
