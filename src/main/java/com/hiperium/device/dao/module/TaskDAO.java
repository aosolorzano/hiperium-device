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
import com.hiperium.device.model.control.Task;
import com.hiperium.device.model.control.TaskDTO;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface TaskDAO {

	/**
	 * 
	 * @param homeId
	 * @return
	 * @throws InformationException
	 */
	List<Task> findByHomeId(@NotNull @Min(value = 1L) Long homeId)
			throws InformationException;

	/**
	 * 
	 * @param id
	 * @throws InformationException
	 */
	void delete(Long id) throws InformationException;

	/**
	 * 
	 * @param id
	 * @param lock
	 * @param bypassCache
	 * @return
	 */
	Task findById(Long id, boolean lock, boolean bypassCache);

	/**
	 * 
	 * @param taskDTO
	 * @throws InformationException
	 */
	Task create(TaskDTO taskDTO) throws InformationException;

	/**
	 * 
	 * @param taskDTO
	 * @throws InformationException
	 */
	Task update(TaskDTO taskDTO) throws InformationException;
}
