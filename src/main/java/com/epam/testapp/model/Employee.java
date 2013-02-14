package com.epam.testapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

@Entity
@NamedQueries({
	@NamedQuery(name="Employee.findAll", query="from Employee order by employeeId"),
	@NamedQuery(name="Employee.totalCountEmployees", query="select count(*) from Employee")})
public final class Employee implements Serializable {

	private static final long serialVersionUID = 6385593425831191490L;

	private long employeeId;
	private String firstName;
	private String lastName;
	private Address address;
	private Set<Job> jobs;

	@Id
	@Column(name = "EMPLOYEE_ID", nullable = false)
	@SequenceGenerator(name = "employeeSeq", sequenceName = "EMPLOYEE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "FIRST_NAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID", nullable = false)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	@BatchSize(size = 100)
	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
}
