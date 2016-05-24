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

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the register user' home.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class DeviceTask implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = 8177644339208606226L;

	/** The property pk. */
	@NotNull
	private DeviceTaskPK pk;

	/** The property active. */
	@NotNull
	private Boolean active;

	/** The property task. */
	@NotNull
	private Task task;

	/**
	 * Default constructor.
	 */
	public DeviceTask() {
		this.pk = new DeviceTaskPK();
	}

	/**
	 * 
	 * @param deviceId
	 * @param taskId
	 */
	public DeviceTask(Long deviceId, Long taskId) {
		this.pk = new DeviceTaskPK(deviceId, taskId);
	}

	/**
	 * Gets the pk property.
	 * 
	 * @return the pk.
	 */
	public DeviceTaskPK getPk() {
		return pk;
	}

	/**
	 * Sets the pk property.
	 * 
	 * @param pk
	 *            the pk to set.
	 */
	public void setPk(DeviceTaskPK pk) {
		this.pk = pk;
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
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
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
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		DeviceTask other = (DeviceTask) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
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
		return "DeviceTask [pk=" + pk + ", active=" + active + "]";
	}

}
