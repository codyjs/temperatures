package com.temperatures.temperatures.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Representation of a row from the 'temperature' table
 * @author Cody
 *
 */
@Entity
@Table(name = "temperature")
public class Temperature {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "value")
	private float value;
	
	@Transient
	private String unit = "Celsius";

	@Column(name = "create_date", updatable = false)
	private Date createdDate;

	@Column(name = "update_date", insertable = false)
	private Date updatedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
