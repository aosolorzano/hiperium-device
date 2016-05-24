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
package com.hiperium.device.common.services;

import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.client.http.HttpClient;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.device.common.converter.DeviceConverter;

/**
 * This service class send calls to the REST Service to operate with different
 * actions originated from the system.
 * 
 * @author Andres Solorzano
 * 
 */
@Startup
@Singleton
@LocalBean
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LoggingServiceManager extends HttpClient {

	/** The LOGGER property for logger messages. */
	private static final HiperiumLogger LOGGER = HiperiumLogger.getLogger(LoggingServiceManager.class);
    
	/** The property deviceConverter. */
	private DeviceConverter deviceConverter;
	
	/**
	 * Class constructor
	 */
	public LoggingServiceManager() {
		super();
		this.deviceConverter = new DeviceConverter();
	}
	
	/**
	 * 
	 * @param serviceURI
	 * @param deviceDTO
	 * @param appToken
	 */
	public void createDeviceAudit(@NotNull String serviceURI, @NotNull DeviceDTO deviceDTO, @NotNull String appToken) {
		LOGGER.debug("createDeviceAudit - START");
		try {
			super.postToService(serviceURI, this.deviceConverter.toJSON(deviceDTO), "application/json", appToken);
		} catch (InformationException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
