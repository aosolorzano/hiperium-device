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
package com.hiperium.device.bo.module;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.model.control.Task;
import com.hiperium.device.model.control.TaskDTO;

/**
 * This interface declares the methods needed for the task service.
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface TaskBO {

	/**
	 * Creates a user device task.
	 * 
	 * @param task
	 *            the task object
	 * @throws InformationException
	 *             if errors occurs when trying to create timer service tasks
	 */
	void create(@NotNull TaskDTO task) throws InformationException;

	/**
	 * Updates an existing timer task.
	 * 
	 * @param task
	 *            the task
	 * @throws InformationException
	 *             if errors occurs when trying to create timer service tasks
	 */
	void update(@NotNull TaskDTO task) throws InformationException;

	/**
	 * Deletes an existing timer task.
	 * 
	 * @param taskPK
	 *            the task primary key
	 * @throws InformationException
	 *             if errors occurs when trying to create timer service tasks
	 */
	void delete(@NotNull @Min(value = 1L) Long id) throws InformationException;

	/**
	 * 
	 * @param id
	 * @param lock
	 * @return
	 */
	Task findById(@NotNull @Min(value = 1L) Long id, boolean lock)
			throws InformationException;

	/**
	 * Find tasks by home ID.
	 * 
	 * @param homeId
	 *            the home ID.
	 * @return the resulting query.
	 */
	List<TaskDTO> findByHomeId(@NotNull @Min(value = 1L) Long homeId)
			throws InformationException;
}
