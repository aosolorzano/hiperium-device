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
package com.hiperium.device.bo.module.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.bo.generic.GenericBO;
import com.hiperium.device.bo.module.ZoneBO;
import com.hiperium.device.model.control.Zone;

/**
 * This class implements the methods declared in the ZoneLocal interface.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class ZoneBOImpl extends GenericBO implements ZoneBO {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zone update(@NotNull Zone register)
			throws InformationException {
		return super.getDaoFactory().getZoneDAO().update(register);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Zone findById(@NotNull @Min(value = 1L) Long id, 
			boolean lock) throws InformationException {
		return super.getDaoFactory().getZoneDAO().findById(id, false, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zone> findByProfileId(@NotNull @Min(value = 1L) Long profileId)
					throws InformationException {
		return super.getDaoFactory().getZoneDAO().findByProfileId(profileId);
	}
	
}
