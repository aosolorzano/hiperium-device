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
package com.hiperium.device.logging.bo.impl;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.device.bo.generic.GenericBO;
import com.hiperium.device.common.services.LoggingServiceManager;
import com.hiperium.device.logging.bo.LoggingManagerBO;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Startup
@Singleton
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LoggingManagerBOImpl extends GenericBO implements LoggingManagerBO {

	/** The property log. */
    @Inject
    private HiperiumLogger log;
    
    /** The property loggingServiceManager. */
    @EJB
    private LoggingServiceManager loggingServiceManager;
    
    /**
	 * {@inheritDoc} 
	 */
    @Asynchronous
	public void create(@NotNull DeviceDTO deviceDTO, String tokenID) throws Exception {
		this.log.debug("create - START");
		this.loggingServiceManager.createDeviceAudit("", deviceDTO, tokenID); 
		this.log.debug("create - END");
	}
    
}
