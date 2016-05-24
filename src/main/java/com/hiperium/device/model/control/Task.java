/**
 * Product  : Hiperium Project
 * Architect: Andres Solorzano.
 * Created  : 08-05-2009 - 23:30:00
 * 
 * The contents of this file are copyrighted by Andres Solorzano 
 * and it is protected by the license: "GPL V3." You can find a copy of this 
 * license at: http://www.hiperium.com/about/licence.html
 * 
 * Copyright 2014 Andres Solorzano. All rights reserved.
 * 
 */
package com.hiperium.device.model.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hiperium.commons.services.EnumDeviceAction;
import com.hiperium.device.model.EnumFrequency;

/**
 * This class represents a programmed Task in the system, to do something in a
 * specific date and time against a device group.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class Task implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = 6685851621203294011L;

	/** The property id. */
	@Min(value = 1L)
	private Long id;

	/** The property homeId. */
	@NotNull
	@Min(value = 1L)
	private Long homeId;

	/** The property executionDate. */
	@NotNull
	private Date executionDate;

	/** The property deviceOperationLevel. */
	@NotNull
	@Min(value = 0)
	@Max(value = 100)
	private int deviceOperationLevel;

	/** The property action. */
	@NotNull
	private EnumDeviceAction action;

	/** The property frequency. */
	@NotNull
	private EnumFrequency frequency;

	/** The property active. */
	@NotNull
	private Boolean active;

	/** The property comments. */
	@Size(max = 300)
	private String comments;

	/** The property devices. */
	@NotNull
	private List<DeviceTask> devices;

	/** The property version. */
	private Long version;

	/**
	 * Default constructor.
	 */
	public Task() {
		this.action = EnumDeviceAction.ACTIVATE;
		this.frequency = EnumFrequency.ONCE;
		this.devices = new ArrayList<DeviceTask>();
	}

	/**
	 * Gets the id property.
	 * 
	 * @return the id property.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id property.
	 * 
	 * @param id
	 *            the variable id to be assigned.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the executionDate property.
	 * 
	 * @return the executionDate property.
	 */
	public Date getExecutionDate() {
		return executionDate;
	}

	/**
	 * Sets the executionDate property.
	 * 
	 * @param executionDate
	 *            the variable executionDate to be assigned.
	 */
	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	/**
	 * Gets the action property.
	 * 
	 * @return the action property.
	 */
	public EnumDeviceAction getAction() {
		return action;
	}

	/**
	 * Sets the action property.
	 * 
	 * @param action
	 *            the variable action to be assigned.
	 */
	public void setAction(EnumDeviceAction action) {
		this.action = action;
	}

	/**
	 * Gets the active property.
	 * 
	 * @return the active.
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active property.
	 * 
	 * @param active
	 *            the active to set.
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Gets the frequency property.
	 * 
	 * @return the frequency property.
	 */
	public EnumFrequency getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency property.
	 * 
	 * @param frequency
	 *            the variable frequency to be assigned.
	 */
	public void setFrequency(EnumFrequency frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the deviceOperationLevel property.
	 * 
	 * @return the deviceOperationLevel property.
	 */
	public int getDeviceOperationLevel() {
		return deviceOperationLevel;
	}

	/**
	 * Sets the deviceOperationLevel property.
	 * 
	 * @param deviceOperationLevel
	 *            the variable deviceOperationLevel to be assigned.
	 */
	public void setDeviceOperationLevel(int deviceOperationLevel) {
		this.deviceOperationLevel = deviceOperationLevel;
	}

	/**
	 * Gets the comments property.
	 * 
	 * @return the comments property.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments property.
	 * 
	 * @param comments
	 *            the variable comments to be assigned.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the version property.
	 * 
	 * @return the version property.
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Sets the version property.
	 * 
	 * @param version
	 *            the variable version to be assigned.
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * Get the homeId property.
	 * 
	 * @return the homeId property.
	 */
	public Long getHomeId() {
		return homeId;
	}

	/**
	 * Set the homeId property.
	 * 
	 * @param homeId
	 *            the homeId to set.
	 */
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	/**
	 * @return the devices
	 */
	public List<DeviceTask> getDevices() {
		return devices;
	}

	/**
	 * @param devices
	 *            the devices to set
	 */
	public void setDevices(List<DeviceTask> devices) {
		this.devices = devices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", executionDate=" + executionDate
				+ ", active=" + active + ", deviceOperationLevel="
				+ deviceOperationLevel + ", version=" + version + ", action="
				+ action + ", frequency=" + frequency + "]";
	}
}
