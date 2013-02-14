package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public final class Company implements Serializable {

	private static final long serialVersionUID = 7712395101909629722L;
	
	private long companyId;
	private String companyName;
	
	@Id
	@Column(name = "COMPANY_ID", nullable = false)
	@SequenceGenerator(name = "comapanySeq", sequenceName = "COMPANY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comapanySeq")
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_NAME", nullable = false)
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
}
