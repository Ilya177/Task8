package com.epam.testapp.database;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testapp.exception.CannotGeTotalCountEmployeesException;
import com.epam.testapp.exception.CannotGetListEmployeesException;
import com.epam.testapp.model.Employee;

public final class HibernateEmployeeDAO implements IEmployeeDAO {

	private static final Logger LOGGER = Logger
			.getLogger(HibernateEmployeeDAO.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

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
			Query query = sessionFactory.getCurrentSession().getNamedQuery(
					"Employee.findAll");
			query.setFirstResult(start);
			query.setMaxResults(range);

			employees = query.list();
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
			totalCountEmployees = (Long) sessionFactory.getCurrentSession()
					.getNamedQuery("Employee.totalCountEmployees")
					.uniqueResult();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new CannotGeTotalCountEmployeesException(e);
		}
		return totalCountEmployees;
	}
}
