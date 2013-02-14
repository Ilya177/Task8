package com.epam.testapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.epam.testapp.database.util.ResultSetExtractor;
import com.epam.testapp.model.Address;
import com.epam.testapp.model.City;
import com.epam.testapp.model.Company;
import com.epam.testapp.model.Country;
import com.epam.testapp.model.Job;
import com.epam.testapp.model.Office;
import com.epam.testapp.model.Position;

public final class JobResultSetExtractor implements
		ResultSetExtractor<Set<Job>> {

	@Override
	public Set<Job> extractData(ResultSet resultSet) throws SQLException {
		Set<Job> jobs = new HashSet<Job>();
		while (resultSet.next()) {
			Country country = new Country();
			
			City city = new City();
			city.setCountry(country);
			
			Address address = new Address();
			address.setCity(city);
			
			Company company = new Company();

			Office office = new Office();
			office.setCompany(company);
			office.setAddress(address);
			office.setAddress(address);
			
			Position position = new Position();
			
			Job job = new Job();
			job.setOffice(office);		
			job.setPosition(position);

			company.setCompanyName(resultSet.getString(1));
			city.setCityName(resultSet.getString(2));
			country.setCountryName(resultSet.getString(3));
			address.setStreetName(resultSet.getString(4));
			address.setHouseNumber(resultSet.getInt(5));
			address.setOfficeNumber(resultSet.getInt(6));
			office.setEmployeeCount(resultSet.getInt(7));
			position.setPositionName(resultSet.getString(8));

			jobs.add(job);
		}
		return jobs;
	}
}
