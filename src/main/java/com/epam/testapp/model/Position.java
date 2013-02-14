package com.epam.testapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public final class Position implements Serializable {

	private static final long serialVersionUID = -7846439831392393692L;
	
	private long positionId;
	private String positionName;
	
	@Id
	@Column(name = "POSITION_ID", nullable = false)
	@SequenceGenerator(name = "positionSeq", sequenceName = "POSITION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positionSeq")
	public long getPositionId() {
		return positionId;
	}

	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}

	@Column(name = "POSITION_NAME", nullable = false)
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
