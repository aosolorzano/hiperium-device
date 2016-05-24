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
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hiperium.commons.client.dto.DeviceDTO;

/**
 * This class represents a programmed Task in the system, to do something in a
 * specific date and time against a device group.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class TaskDTO implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = -366066222812619065L;

	/** The property id. */
	private Long id;

	/** The property homeId. */
	@NotNull
	@Min(value = 1L)
	private Long homeId;

	/** The property executionDate. */
	@Future
	private Date executionDate;

	/** The property deviceOperationLevel. */
	@NotNull
	@Max(value = 100)
	private int deviceOperationLevel;

	/** The property action. */
	@NotNull
	private String action;

	/** The property frequency. */
	@NotNull
	private String frequency;

	/** The property selectedDevices. */
	@NotNull
	private List<DeviceDTO> selectedDevices;

	/** The property comments. */
	@Size(max = 300)
	private String comments;

	/**
	 * Default constructor.
	 */
	public TaskDTO() {

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
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the selectedDevices
	 */
	public List<DeviceDTO> getSelectedDevices() {
		return selectedDevices;
	}

	/**
	 * @param selectedDevices
	 *            the selectedDevices to set
	 */
	public void setSelectedDevices(List<DeviceDTO> selectedDevices) {
		this.selectedDevices = selectedDevices;
	}

}
