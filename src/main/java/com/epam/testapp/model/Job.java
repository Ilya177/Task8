package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public final class Job implements Serializable {

	private static final long serialVersionUID = -2935139304572022311L;

	private JobPK jobPK;
	private Employee employee;
	private Office office;
	private Position position;

	@EmbeddedId
	public JobPK getJobPK() {
		return jobPK;
	}

	public void setJobPK(JobPK jobPK) {
		this.jobPK = jobPK;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_ID", nullable = false, insertable = false, updatable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "COMPANY_ID", referencedColumnName="COMPANY_ID", nullable = false, insertable = false, updatable = false),
		@JoinColumn(name = "ADDRESS_ID", referencedColumnName="ADDRESS_ID", nullable = false, insertable = false, updatable = false)
	})
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "POSITION_ID", nullable = false)
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
