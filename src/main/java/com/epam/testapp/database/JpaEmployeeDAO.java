package com.epam.testapp.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testapp.exception.CannotGeTotalCountEmployeesException;
import com.epam.testapp.exception.CannotGetListEmployeesException;
import com.epam.testapp.model.Employee;

public final class JpaEmployeeDAO implements IEmployeeDAO {

	private static final Logger LOGGER = Logger.getLogger(JpaEmployeeDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Employee> getList(int start, int range)
			throws CannotGetListEmployeesException {
		List<Employee> employees = null;
		
		if (start < 0 || range <= 0) {
			throw new CannotGetListEmployeesException();
		}
		
		try {
			Query query = entityManager.createNamedQuery("Employee.findAll");
			query.setFirstResult(start);
			query.setMaxResults(range);

			employees = query.getResultList();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new CannotGetListEmployeesException(e);
		}

		return employees;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getTotalCountEmployees()
			throws CannotGeTotalCountEmployeesException {
		Long totalCountEmployees = 0L;
		try {
			totalCountEmployees = (Long) entityManager.createNamedQuery(
					"Employee.totalCountEmployees").getSingleResult();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new CannotGeTotalCountEmployeesException(e);
		}

		return totalCountEmployees;
	}
}
