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
package com.hiperium.device.dao.module;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.model.control.DeviceTask;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface DeviceTaskDAO {

	/**
	 * 
	 * @param taskId
	 * @return
	 * @throws InformationException
	 */
	List<DeviceTask> findByTaskId(@NotNull @Min(value = 1L) Long taskId)
			throws InformationException;

}
