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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.path.DeviceRestfulPath;
import com.hiperium.device.bo.module.ZoneBO;
import com.hiperium.device.model.control.Zone;
import com.hiperium.device.restful.generic.GenericREST;


/**
 * This class represents the service administration Zones with the
 * data base.
 * 
 * @author Andres Solorzano
 * 
 */
@Path(DeviceRestfulPath.ZONES)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ZoneResource extends GenericREST<Zone> {

	/** The property log. */
    @Inject
    private HiperiumLogger log;

    /** The property zoneBO. */
    @EJB
    private ZoneBO zoneBO;
    
    /**
	 * 
	 * @return
	 * @throws WebApplicationException
	 */
	@GET
	@Path(DeviceRestfulPath.FIND_ZONE_BY_PROFILE_ID)
	public List<Zone> findByProfileId(@QueryParam("profileId") @NotNull Long profileId) throws WebApplicationException {
		this.log.debug("findByProfileId - BEGIN: " + profileId);
		try {
			return this.zoneBO.findByProfileId(profileId);
		} catch (InformationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param zoneId
	 * @return
	 * @throws WebApplicationException
	 */
	@GET
	@Path(DeviceRestfulPath.FIND_ZONE_BY_ID)
	public Zone findById(@QueryParam("zoneId") Long zoneId) throws WebApplicationException {
		this.log.debug("findById - BEGIN: " + zoneId);
		Zone zone;
		try {
			zone = this.zoneBO.findById(zoneId, false);
			if(zone == null){
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			return zone;
		} catch (InformationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @param register
	 * @return
	 * @throws WebApplicationException
	 */
	@PUT
	@Path(DeviceRestfulPath.UPDATE)
	public Zone update(Zone register) throws WebApplicationException {
		this.log.debug("homeSelection - BEGIN: " + register);
		try {
			return this.zoneBO.update(register);
		} catch (InformationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
}
