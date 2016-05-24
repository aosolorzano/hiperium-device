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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.hiperium.commons.services.exception.PropertyValidationException;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.path.DeviceRestfulPath;
import com.hiperium.device.bo.module.TaskBO;
import com.hiperium.device.model.control.Task;
import com.hiperium.device.model.control.TaskDTO;
import com.hiperium.device.restful.generic.GenericREST;

/**
 * This class represents the service administration Tasks with the data base.
 * 
 * @author Andres Solorzano
 * 
 */
@Path(DeviceRestfulPath.TASKS)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TaskResource extends GenericREST<TaskDTO> {

	/** The property log. */
    @Inject
    private HiperiumLogger log;
	
    /** The property taskBO. */
    @EJB
    private TaskBO taskBO;
    
	/**
	 * 
	 * @param register
	 * @throws WebApplicationException
	 */
	@POST
	@Path(DeviceRestfulPath.CREATE)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(@NotNull TaskDTO register) throws WebApplicationException {
		this.log.debug("create - BEGIN");
		try {
			super.validateObjectProperties(register);
		} catch (PropertyValidationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.NOT_ACCEPTABLE);
		}
		try {
			this.taskBO.create(register);
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		return super.ok();
	}

	/**
	 * 
	 * @param register
	 * @return
	 * @throws WebApplicationException
	 */
	@PUT
	@Path(DeviceRestfulPath.UPDATE)
	public Response update(@NotNull TaskDTO register) throws WebApplicationException {
		this.log.debug("update - BEGIN: " + register);
		try {
			super.validateObjectProperties(register);
		} catch (PropertyValidationException e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.NOT_ACCEPTABLE);
		}
		try {
			this.taskBO.update(register);
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		return super.ok();
	}

	/**
	 * 
	 * @param taskId
	 * @throws WebApplicationException
	 */
	@DELETE
	@Path(DeviceRestfulPath.DELETE)
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@QueryParam("taskId") @NotNull Long taskId) throws WebApplicationException {
		this.log.debug("delete - BEGIN");
		Task task;
		try {
			task = this.taskBO.findById(taskId, false);
			if (task == null) {
				throw new WebApplicationException(Response.Status.NOT_FOUND);
			}
			this.taskBO.delete(taskId);
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
		return super.ok();
	}

	/**
	 * 
	 * @param deviceId
	 * @return
	 * @throws WebApplicationException
	 */
	@GET
	@Path(DeviceRestfulPath.FIND_TASK_BY_HOME_ID)
	public List<TaskDTO> findByhomeId(@QueryParam("homeId") @NotNull Long homeId) throws WebApplicationException {
		this.log.debug("findByhomeId - " + homeId);
		try {
			return this.taskBO.findByHomeId(homeId);
		} catch (Exception e) {
			this.log.error(e.getMessage(), e);
			throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
		}
	}
	
}
