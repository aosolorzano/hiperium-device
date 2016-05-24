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
package com.hiperium.device.restful.module;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.exception.PropertyValidationException;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.path.DeviceRestfulPath;
import com.hiperium.device.bo.module.DeviceBO;
import com.hiperium.device.restful.generic.GenericREST;


/**
 * This class represents the service administration Devices with the
 * data base.
 * 
 * @author Andres Solorzano
 * 
 */
@Path(DeviceRestfulPath.GATEWAY)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GatewayResource extends GenericREST<DeviceDTO> {

	/** The property log. */
    @Inject
    private HiperiumLogger log;
    
    /** The property deviceBO. */
    @EJB
    private DeviceBO deviceBO;
	
    /**
	 * Find devices by Home identifier called by Home Gateway device.
	 * 
	 * @param homeId
	 * @return
	 * @throws WebApplicationException
	 */
	@GET
	@Path(DeviceRestfulPath.FIND_DEVICE_BY_HOME_ID)
	public List<DeviceDTO> findByHomeId (@QueryParam("homeId") @NotNull Long homeId) throws WebApplicationException {
		this.log.debug("findByHomeId(): " + homeId);
		try {
			return this.deviceBO.findByHomeId(homeId);
		} catch (InformationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Update device based on home operation called by Home Gateway device.
	 * 
	 * @return
	 * @throws WebApplicationException
	 * @throws PropertyValidationException 
	 */
	@POST
	@Path(DeviceRestfulPath.HOME_OPERATION)
	public Response homeOperation(@NotNull DeviceDTO deviceDTO) throws WebApplicationException, PropertyValidationException {
		this.log.debug("homeOperation - BEGIN");
		super.validateObjectProperties(deviceDTO);
		try {
			// The token ID is the token identifier obtained from Hiperium Home at start up time.
			this.deviceBO.homeOperation(deviceDTO, super.getTokenId());
			return super.ok();
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
}
