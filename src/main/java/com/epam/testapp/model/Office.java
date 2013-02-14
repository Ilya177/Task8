package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Formula;

@Entity
public final class Office implements Serializable {

	private static final long serialVersionUID = -3169047340843271353L;

	private OfficePK officePK;
	private Company company;
	private Address address;
	private int employeeCount;

	@EmbeddedId
	public OfficePK getOfficePK() {
		return officePK;
	}

	public void setOfficePK(OfficePK officePK) {
		this.officePK = officePK;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = false, insertable = false, updatable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID", nullable = false, insertable = false, updatable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Formula(value = "(select count(job.employee_id) from office o join job on job.company_id = o.company_id and job.address_id = o.address_id where address_id = o.address_id)")
	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}	
}
