package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public final class OfficePK implements Serializable {

	private static final long serialVersionUID = -2951172466166606427L;

	private long companyId;
	private long addressId;

	@Column(name = "COMPANY_ID", nullable = false)
	public long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Column(name = "ADDRESS_ID", nullable = false)
	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (addressId ^ (addressId >>> 32));
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficePK other = (OfficePK) obj;
		if (addressId != other.addressId)
			return false;
		if (companyId != other.companyId)
			return false;
		return true;
	}
}
