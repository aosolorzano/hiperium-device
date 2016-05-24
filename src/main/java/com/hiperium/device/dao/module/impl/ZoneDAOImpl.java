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
package com.hiperium.device.dao.module.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.dao.EnumNamedQueryControl;
import com.hiperium.device.dao.GenericDAO;
import com.hiperium.device.dao.module.ZoneDAO;
import com.hiperium.device.model.control.Zone;

/**
 * This class implements the methods declared in the ZoneLocal interface.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ZoneDAOImpl extends GenericDAO<Zone, Long> implements ZoneDAO {
	
	/** The property entityManger. */
	@Inject
    private EntityManager entityManager;
	
	/**
	 * Component post contruct.
	 */
	@PostConstruct
	public void init() {
		super.setEntityManager(this.entityManager);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zone> findByProfileId(@NotNull @Min(value = 1L) Long profileId) throws InformationException {
		return super.createNamedQuery(EnumNamedQueryControl.ZONE_FIND_BY_PROFILE_ID.getName(), true)
				.setParameter("profileId", profileId)
				.getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zone update(Zone register) {
		return super.update(register);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zone findById(Long id, boolean lock, boolean bypassCache) {
		return super.findById(id, lock, bypassCache);
	}
}
