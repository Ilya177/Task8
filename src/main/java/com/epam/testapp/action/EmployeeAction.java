package com.epam.testapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.MappingDispatchActionSupport;

import com.epam.testapp.database.IEmployeeDAO;
import com.epam.testapp.exception.ApplicationException;
import com.epam.testapp.form.EmployeeForm;
import com.epam.testapp.model.Employee;

@SuppressWarnings("deprecation")
public final class EmployeeAction extends MappingDispatchActionSupport {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeAction.class);
	private IEmployeeDAO employeeDAO;
	
	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		EmployeeForm employeeForm = (EmployeeForm) form;
		int numberItemsPerPage = employeeForm.getNumberItemsPerPage();
		int pageNumber = employeeForm.getPageNumber() - 1;
		int start = numberItemsPerPage * pageNumber;				
		List<Employee> employees = null;
		
		try {
			employees = employeeDAO.getList(start, numberItemsPerPage);
			employeeForm.setEmployees(employees);
			
			long totalCountEmployees = employeeDAO.getTotalCountEmployees();
			employeeForm.setTotalCountEmployees(totalCountEmployees);
		} catch (ApplicationException e) {
			LOGGER.error(e);
			return mapping.findForward("failure");
		}
		
		return mapping.findForward("list");
	}
}
