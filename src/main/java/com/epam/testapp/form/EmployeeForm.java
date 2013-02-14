package com.epam.testapp.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.epam.testapp.model.Employee;

public final class EmployeeForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private int numberItemsPerPage;
	private int pageNumber;
	private long totalCountEmployees;
	private List<Employee> employees;
	
	public EmployeeForm() {
		numberItemsPerPage = 100;
		pageNumber = 1;
	}	
	
	public int getNumberItemsPerPage() {
		return numberItemsPerPage;
	}

	public void setNumberItemsPerPage(int numberItemsPerPage) {
		this.numberItemsPerPage = numberItemsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}	

	public long getTotalCountEmployees() {
		return totalCountEmployees;
	}

	public void setTotalCountEmployees(long totalCountEmployees) {
		this.totalCountEmployees = totalCountEmployees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		employees = null;
	}
}
