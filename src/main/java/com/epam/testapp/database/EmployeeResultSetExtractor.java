package com.epam.testapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.testapp.database.util.ResultSetExtractor;
import com.epam.testapp.model.Address;
import com.epam.testapp.model.City;
import com.epam.testapp.model.Country;
import com.epam.testapp.model.Employee;

public final class EmployeeResultSetExtractor implements
		ResultSetExtractor<List<Employee>> {

	@Override
	public List<Employee> extractData(ResultSet resultSet) throws SQLException {
		List<Employee> employees = new ArrayList<Employee>();
		while (resultSet.next()) {
			Country country = new Country();

			City city = new City();
			city.setCountry(country);

			Address address = new Address();
			address.setCity(city);

			Employee employee = new Employee();
			employee.setAddress(address);

			employee.setEmployeeId(resultSet.getLong(1));
			employee.setFirstName(resultSet.getString(2));
			employee.setLastName(resultSet.getString(3));
			city.setCityName(resultSet.getString(4));
			country.setCountryName(resultSet.getString(5));
			address.setStreetName(resultSet.getString(6));
			address.setHouseNumber(resultSet.getInt(7));
			address.setOfficeNumber(resultSet.getInt(8));

			employees.add(employee);
		}
		return employees;
	}
}
