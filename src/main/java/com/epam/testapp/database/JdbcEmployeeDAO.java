package com.epam.testapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.epam.testapp.database.util.JdbcUtils;
import com.epam.testapp.database.util.ResultSetExtractor;
import com.epam.testapp.exception.CannotGeTotalCountEmployeesException;
import com.epam.testapp.exception.CannotGetListEmployeesException;
import com.epam.testapp.exception.CannotGetListJobsException;
import com.epam.testapp.model.Employee;
import com.epam.testapp.model.Job;

public final class JdbcEmployeeDAO implements IEmployeeDAO {

	private static final Logger LOGGER = Logger.getLogger(JdbcEmployeeDAO.class);
	private static final String FIND_EMPLOYEES = "SELECT * " +
												     "FROM " +
												         "(SELECT " +
												              "inner.*, " +
												              "rownum rm " +
												          "FROM " +
												             "(SELECT " +
												                  "EMPLOYEE.EMPLOYEE_ID, " +
												                  "EMPLOYEE.FIRST_NAME, " +
												                  "EMPLOYEE.LAST_NAME, " +
												                  "CITY.CITY_NAME, " +
												                  "COUNTRY.COUNTRY_NAME, " +
												                  "ADDRESS.STREET_NAME, " +
												                  "ADDRESS.HOUSE_NUMBER, " +
												                  "ADDRESS.OFFICE_NUMBER " +
												              "FROM " +
												                  "EMPLOYEE " +
												              "JOIN " +
												                  "ADDRESS ON EMPLOYEE.ADDRESS_ID = ADDRESS.ADDRESS_ID " +
												              "JOIN " +
												                  "CITY ON ADDRESS.CITY_ID = CITY.CITY_ID " +
												              "JOIN " +
												                  "COUNTRY ON CITY.COUNTRY_ID = COUNTRY.COUNTRY_ID " +
												                  "ORDER BY EMPLOYEE.EMPLOYEE_ID ) inner " +
												              ")" +
												     "WHERE " +
												         "rm > ? AND rm <= ?";
	
	private static final String FIND_JOBS = "SELECT " +
											    "COMPANY.COMPANY_NAME, " +
											    "CITY.CITY_NAME, " +
											    "COUNTRY.COUNTRY_NAME, " +
											    "ADDRESS.STREET_NAME, " +
											    "ADDRESS.HOUSE_NUMBER, " +
											    "ADDRESS.OFFICE_NUMBER, " +											    
											    "(SELECT " +
											         "count(JOB.EMPLOYEE_ID) " +
											     "FROM " +
											         "OFFICE O " +
											     "JOIN " +
											         "JOB " +
											             "ON JOB.COMPANY_ID = O.COMPANY_ID " +
											             "AND JOB.ADDRESS_ID = O.ADDRESS_ID " +
											     "WHERE " +
											         "OFFICE.ADDRESS_ID = O.ADDRESS_ID), " +
											    "POSITION.POSITION_NAME " +
											"FROM " +
											    "JOB " +
											"JOIN " +
											    "OFFICE " +
											        "ON JOB.COMPANY_ID = OFFICE.COMPANY_ID " +
											        "AND JOB.ADDRESS_ID = OFFICE.ADDRESS_ID " +
											"JOIN " +
											    "COMPANY " +
											        "ON OFFICE.COMPANY_ID = COMPANY.COMPANY_ID " +
											"JOIN " +
											    "ADDRESS " +
											        "ON OFFICE.ADDRESS_ID = ADDRESS.ADDRESS_ID " +
											"JOIN " +
											    "CITY " +
											        "ON ADDRESS.CITY_ID = CITY.CITY_ID " +
											"JOIN " +
											    "COUNTRY " +
											        "ON CITY.COUNTRY_ID = COUNTRY.COUNTRY_ID " +
											"JOIN " +
											    "POSITION " +
											        "ON JOB.POSITION_ID = POSITION.POSITION_ID " +
											"WHERE " +
											    "JOB.EMPLOYEE_ID = ?";
	
	private static final String TOTAL_COUNT_EMPLOYEES = "SELECT count(*) FROM EMPLOYEE";
	
	private static final ResultSetExtractor<List<Employee>> EMPLOYEE_EXTRACTOR = new EmployeeResultSetExtractor();
	private static final ResultSetExtractor<Set<Job>> JOB_EXTRACTOR = new JobResultSetExtractor();
	private ConnectionPool connectionPool;

	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

	@Override
	public List<Employee> getList(int start, int range) throws CannotGetListEmployeesException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = null;
		
		if (start < 0 || range <= 0) {
			throw new CannotGetListEmployeesException();
		}

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(FIND_EMPLOYEES);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, range + start);
			resultSet = preparedStatement.executeQuery();

			employees = EMPLOYEE_EXTRACTOR.extractData(resultSet);
			
			for (Employee employee : employees) {
				Set<Job> jobs = getJobSet(employee.getEmployeeId());
				employee.setJobs(jobs);
			}
		} catch (Exception e) {
			LOGGER.error(e);
			throw new CannotGetListEmployeesException(e);
		} finally {
			connectionPool.releaseConnection(connection);
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(preparedStatement);
		}

		return employees;
	}
	
	@Override
	public Long getTotalCountEmployees() throws CannotGeTotalCountEmployeesException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Long totalCountEmployees = 0L;

		try {
			connection = connectionPool.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(TOTAL_COUNT_EMPLOYEES);
			
			if (resultSet.next()) {
				totalCountEmployees = resultSet.getLong(1);			
			}
		} catch (Exception e) {
			LOGGER.error(e);
			throw new CannotGeTotalCountEmployeesException(e);
		} finally {
			connectionPool.releaseConnection(connection);
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(statement);
		}
		
		return totalCountEmployees;
	}
	
	private Set<Job> getJobSet(long employeeId) throws CannotGetListJobsException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<Job> jobs = null;
		
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(FIND_JOBS);
			preparedStatement.setLong(1, employeeId);
			resultSet = preparedStatement.executeQuery();			

			jobs = JOB_EXTRACTOR.extractData(resultSet);
		} catch (Exception e) {
			throw new CannotGetListJobsException(e);
		} finally {
			connectionPool.releaseConnection(connection);
			JdbcUtils.closeResultSet(resultSet);
			JdbcUtils.closeStatement(preparedStatement);
		}
		
		return jobs;
	}	
}
