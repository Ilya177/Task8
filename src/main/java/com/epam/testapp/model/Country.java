package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public final class Country implements Serializable {

	private static final long serialVersionUID = 1766051320874396596L;

	private long countryId;
	private String countryName;

	@Id
	@Column(name = "COUNTRY_ID", nullable = false)
	@SequenceGenerator(name = "countrySeq", sequenceName = "COUNTRY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrySeq")
	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	@Column(name = "COUNTRY_NAME", nullable = false)
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
