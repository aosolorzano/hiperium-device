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

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the primary key of the user's home.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class DeviceTaskPK implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = -891435365405348306L;

	/** The property deviceId. */
	@Min(value = 1L)
	private Long deviceId;

	/** The property taskId. */
	@Min(value = 1L)
	private Long taskId;

	/**
	 * Default Constructor.
	 */
	public DeviceTaskPK() {
		// Nothing to do.
	}

	/**
	 * 
	 * @param deviceId
	 * @param taskId
	 */
	public DeviceTaskPK(Long deviceId, Long taskId) {
		this.deviceId = deviceId;
		this.taskId = taskId;
	}

	/**
	 * Gets the deviceId property.
	 * 
	 * @return the deviceId.
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * Sets the deviceId property.
	 * 
	 * @param deviceId
	 *            the deviceId to set.
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Gets the taskId property.
	 * 
	 * @return the taskId.
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * Sets the taskId property.
	 * 
	 * @param taskId
	 *            the taskId to set.
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
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
		DeviceTaskPK other = (DeviceTaskPK) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceTaskPK [deviceId=" + deviceId + ", taskId=" + taskId
				+ "]";
	}

}