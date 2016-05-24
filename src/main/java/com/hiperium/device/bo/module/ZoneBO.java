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
import com.hiperium.device.model.control.Zone;

/**
 * This interface declare the methods needed to zone operations.
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface ZoneBO {

	/**
	 * 
	 * @param id
	 * @param lock
	 * @return
	 */
	Zone findById(@NotNull @Min(value = 1L) Long id, boolean lock)
			throws InformationException;

	/**
	 * Updates an existing timer task.
	 * 
	 * @param register
	 *            the register to homeSelection
	 * @return the updated register
	 * @throws InformationException
	 *             if errors occurs when trying to create timer service tasks
	 */
	Zone update(@NotNull Zone register) throws InformationException;

	/**
	 * Find the zones by profile ID.
	 * 
	 * @return the zones by profile ID.
	 */
	List<Zone> findByProfileId(@NotNull @Min(value = 1L) Long profileId)
			throws InformationException;
}
