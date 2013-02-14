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

import org.hibernate.annotations.BatchSize;

@Entity
@BatchSize(size = 100)
public final class Address implements Serializable {

	private static final long serialVersionUID = -8287555188845872694L;

	private long addressId;
	private String streetName;
	private int houseNumber;
	private int officeNumber;
	private City city;

	@Id
	@Column(name = "ADDRESS_ID", nullable = false)
	@SequenceGenerator(name = "addressSeq", sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSeq")
	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "STREET_NAME", nullable = false)
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "HOUSE_NUMBER", nullable = false)
	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Column(name = "OFFICE_NUMBER", nullable = false)
	public int getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(int officeNumber) {
		this.officeNumber = officeNumber;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CITY_ID", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}	
}
