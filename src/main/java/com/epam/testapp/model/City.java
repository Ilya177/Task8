package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public final class City implements Serializable {

	private static final long serialVersionUID = -6853009223349957760L;
	
	private long cityId;
	private String cityName;
	private Country country;
	
	@Id
	@Column(name = "CITY_ID", nullable = false)
	@SequenceGenerator(name = "citySeq", sequenceName = "CITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citySeq")
	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "CITY_NAME", nullable = false)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
