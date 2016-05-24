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
import com.hiperium.device.model.control.Zone;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface ZoneDAO {

	/**
	 * 
	 * @param profileId
	 * @return
	 * @throws InformationException
	 */
	List<Zone> findByProfileId(@NotNull @Min(value = 1L) Long profileId)
			throws InformationException;

	/**
	 * 
	 * @param register
	 * @return
	 */
	Zone update(Zone register);

	/**
	 * 
	 * @param id
	 * @param lock
	 * @param bypassCache
	 * @return
	 */
	Zone findById(Long id, boolean lock, boolean bypassCache);

}
