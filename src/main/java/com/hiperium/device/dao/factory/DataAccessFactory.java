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
package com.hiperium.device.dao.factory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import com.hiperium.device.dao.module.DeviceDAO;
import com.hiperium.device.dao.module.DeviceTaskDAO;
import com.hiperium.device.dao.module.TaskDAO;
import com.hiperium.device.dao.module.ZoneDAO;

/**
 * Abstract Factory class that contains references to all DAO objects.
 * 
 * @author Andres Solorzano
 *
 */
@ApplicationScoped
public class DataAccessFactory {

	/** The property deviceDAO */
	@EJB
	private DeviceDAO deviceDAO;

	/** The property taskDAO */
	@EJB
	private TaskDAO taskDAO;

	/** The property deviceTaskDAO */
	@EJB
	private DeviceTaskDAO deviceTaskDAO;

	/** The property zoneDAO */
	@EJB
	private ZoneDAO zoneDAO;

	/**
	 * Class constructor.
	 */
	public DataAccessFactory() {
		// Nothing to do
	}

	/**
	 *
	 * @return
	 */
	public DeviceDAO getDeviceDAO() {
		return deviceDAO;
	}

	/**
	 *
	 * @return
	 */
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	/**
	 *
	 * @return
	 */
	public ZoneDAO getZoneDAO() {
		return zoneDAO;
	}

	/**
	 * @return the deviceTaskDAO
	 */
	public DeviceTaskDAO getDeviceTaskDAO() {
		return deviceTaskDAO;
	}

}
