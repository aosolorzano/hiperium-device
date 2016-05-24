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
package com.hiperium.device.bo.module.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.device.bo.generic.GenericBO;
import com.hiperium.device.bo.module.DeviceBO;
import com.hiperium.device.common.utils.EnumDeviceClass;
import com.hiperium.device.logging.bo.LoggingManagerBO;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class DeviceBOImpl extends GenericBO implements DeviceBO {

	/** The property log. */
	@Inject
	private HiperiumLogger log;
	
	/** The property auditManagerBO. */
	@EJB
	private LoggingManagerBO auditManagerBO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByHomeId(@NotNull @Min(value = 1L) Long homeId) throws InformationException {
		return super.getDaoFactory().getDeviceDAO().findByHomeId(homeId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByZoneId(@NotNull @Min(value = 1L) Long zoneId) throws InformationException {
		return super.getDaoFactory().getDeviceDAO().findByZoneId(zoneId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void userOperation(@NotNull DeviceDTO deviceDTO, String tokenID) throws InformationException {
		this.log.debug("userOperation - BEGIN");
		super.getDaoFactory().getDeviceDAO().updateUserDeviceState(deviceDTO);
		try {
			this.auditManagerBO.create(deviceDTO, tokenID);
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		this.log.debug("userOperation - END");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void homeOperation(@NotNull DeviceDTO deviceDTO, String tokenID) {
		this.log.debug("homeOperation - BEGIN");
		super.getDaoFactory().getDeviceDAO().updateHomeDeviceState(deviceDTO);
		try {
			// The token ID is the session identifier obtained from Hiperium Home at start up time.
			this.auditManagerBO.create(deviceDTO, tokenID);
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		this.log.debug("homeOperation - END");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByClass(Long homeId, EnumDeviceClass deviceClass)
			throws InformationException {
		this.log.debug("findByClass - BEGIN");
		return super.getDaoFactory().getDeviceDAO().findByClass(homeId, deviceClass);
	}
}
