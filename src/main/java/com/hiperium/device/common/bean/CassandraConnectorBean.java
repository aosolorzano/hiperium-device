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
package com.hiperium.device.common.bean;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.hiperium.commons.services.logger.HiperiumLogger;

/**
 * This service class represents the driver connector for the Apache Cassandra
 * instance.
 *
 * @author Andres Solorzano
 *
 */
@Startup
@Singleton
@LocalBean
@Lock(LockType.READ)
@DependsOn("ConfigurationBean")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CassandraConnectorBean {

	/** The property FIND_BY_ID. */
	private static final String FIND_BY_ID = "SELECT id,home_id,name,status,value,device_class,device_type,operation_level,device_pin_id,xbee_16bits_msb,xbee_16bits_lsb "
			+ "FROM hcontrol.con_device WHERE id=?;";
	
	/** The property FIND_BY_HOME_ID_QUERY. */
	private static final String FIND_BY_HOME_ID_QUERY = "SELECT id,home_id,name,status,value,device_class,device_type,operation_level,device_pin_id,xbee_16bits_msb,xbee_16bits_lsb "
			+ "FROM hcontrol.con_device WHERE home_id=?;";
	
	/** The property FIND_BY_ZONE_ID_QUERY. */
	private static final String FIND_BY_ZONE_ID_QUERY = "SELECT id,home_id,name,status,value,device_class,device_type,operation_level,device_pin_id,xbee_16bits_msb,xbee_16bits_lsb "
			+ "FROM hcontrol.con_device WHERE zone_id=?;";
	
	/** The property FIND_BY_CLASS_QUERY. */
	private static final String FIND_BY_CLASS_QUERY = "SELECT id,home_id,name,status,value,device_class,device_type,operation_level,device_pin_id,xbee_16bits_msb,xbee_16bits_lsb "
			+ "FROM hcontrol.con_device WHERE home_id=? AND device_class=? ALLOW FILTERING;";
	
	/** The property UPDATE_USER_DEVICE_QUERY. */
	private static final String UPDATE_USER_DEVICE_QUERY = "UPDATE hcontrol.con_device SET status=?, operation_level=? WHERE id=?;";
	
	/** The property UPDATE_HOME_DEVICE_QUERY. */
	private static final String UPDATE_HOME_DEVICE_QUERY = "UPDATE hcontrol.con_device SET status=?, value=?, operation_level=? WHERE id=?;";

	/** The property log. */
	@Inject
	private HiperiumLogger log;

	/** Cassandra Cluster. */
	@Inject
	private Cluster cluster;
	/** Cassandra Session. */
	private Session session;
	
	/** The property findById. */
	private PreparedStatement findById;
	/** The property findByHomeIdPS. */
	private PreparedStatement findByHomeIdPS;
	/** The property findByZoneIdPS. */
	private PreparedStatement findByZoneIdPS;
	/** The property findByClass. */
	private PreparedStatement findByClass;
	/** The property updateUserDevicePS. */
	private PreparedStatement updateUserDevicePS;
	/** The property updateHomeDevicePS. */
	private PreparedStatement updateHomeDevicePS;
	
	/**
	 * Component constructor.
	 */
	@PostConstruct
	public void init() {
		this.log.debug("init() - BEGIN");
		final Metadata metadata = this.cluster.getMetadata();
		this.log.debug("Connected to cluster: " + metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			this.log.debug("Datacenter: " + host.getDatacenter() + " " + host.getAddress() + " " + host.getRack());
		}
		this.session = this.cluster.connect();
		
		// Prepared statement for search devices by ID
		this.findById = this.session.prepare(FIND_BY_ID);
		// Prepared statement for search devices by zone ID
		this.findByHomeIdPS = this.session.prepare(FIND_BY_HOME_ID_QUERY);
		// Prepared statement for search devices by zone ID
		this.findByZoneIdPS = this.session.prepare(FIND_BY_ZONE_ID_QUERY);
		// Prepared statement for search devices by a class
		this.findByClass = this.session.prepare(FIND_BY_CLASS_QUERY);
		// Prepared statement for update device from user interaction
		this.updateUserDevicePS = this.session.prepare(UPDATE_USER_DEVICE_QUERY);
		// Prepared statement for update device from home interaction
		this.updateHomeDevicePS = this.session.prepare(UPDATE_HOME_DEVICE_QUERY);
				
		this.log.debug("init() - END");
	}

	/**
	 * Provide Cassandra Session to be injected in another component.
	 *
	 * @return My session.
	 */
	@Produces
	public Session getSession() {
		return this.session;
	}

	/**
	 * @return the findByHomeIdPS
	 */
	public PreparedStatement getFindByHomeIdPS() {
		return findByHomeIdPS;
	}

	/**
	 * @return the findByZoneIdPS
	 */
	public PreparedStatement getFindByZoneIdPS() {
		return findByZoneIdPS;
	}

	/**
	 * @return the updateUserDevicePS
	 */
	public PreparedStatement getUpdateUserDevicePS() {
		return updateUserDevicePS;
	}

	/**
	 * @return the updateHomeDevicePS
	 */
	public PreparedStatement getUpdateHomeDevicePS() {
		return updateHomeDevicePS;
	}

	/**
	 * @return the findByClass
	 */
	public PreparedStatement getFindByClass() {
		return findByClass;
	}

	/**
	 * @return the findById
	 */
	public PreparedStatement getFindById() {
		return findById;
	}
	
}
