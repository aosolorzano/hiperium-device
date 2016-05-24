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
package com.hiperium.device.dao;

/**
 * This enumeration specifies the names of Named Queries that exists in the
 * application.
 * 
 * @author Andres Solorzano.
 */
public enum EnumNamedQueryControl {

	/** The DEVICE_TASK_FIND_BY_TASK_ID element witch value is DeviceTask.findByTaskId. */
	DEVICE_TASK_FIND_BY_TASK_ID("DeviceTask.findByTaskId"),
	
	/** The ZONE_FIND_BY_PROFILE_ID element witch value is Zone.findByProfileId. */
	ZONE_FIND_BY_PROFILE_ID("Zone.findByProfileId"),
	
	/** The TASK_NEXT_SEQUENTIAL element witch value is Task.findNextSequential. */
	TASK_NEXT_SEQUENTIAL("Task.findNextSequential"),
	
	/** The TASK_FIND_BY_HOME_ID element witch value is Task.findByHomeId. */
	TASK_FIND_BY_HOME_ID("Task.findByHomeId"),

	/** The SELECTION_FIND_BY_DEVICE_ID element witch value is Device.findSelectionByDeviceId. */
	SELECTION_FIND_BY_DEVICE_ID("Device.findSelectionByDeviceId"),
	
	/** The DEVICE_FIND_BY_PROFILE_ID element witch value is Device.findByProfileId. */
	DEVICE_FIND_BY_PROFILE_ID("Device.findByProfileId");

	/** Property name. */
	private final String name;

	/**
	 * The Enumeration constructor.
	 * 
	 * @param name
	 *            Value for the element of the Enumeration
	 */
	private EnumNamedQueryControl(String name) {
		this.name = name;
	}

	/**
	 * Return the value associate to the element of the enumeration.
	 * 
	 * @return the value associate with the enumeration
	 */
	public String getName() {
		return this.name;
	}
}
