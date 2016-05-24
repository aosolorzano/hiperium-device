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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.common.bean.CassandraConnectorBean;
import com.hiperium.device.common.utils.EnumDeviceClass;
import com.hiperium.device.dao.GenericDAO;
import com.hiperium.device.dao.module.DeviceDAO;
import com.hiperium.device.model.control.Device;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DeviceDAOImpl extends GenericDAO<Device, Long> implements DeviceDAO {
	
	/** The property entityManger. */
	@Inject
    private EntityManager entityManager;
	
	/** The property cassandraConnector. */
	@EJB
	private CassandraConnectorBean cassandraConnector;
	
	/** The property findById. */
	private BoundStatement findById;
	/** The property findByHomeIdBS. */
	private BoundStatement findByHomeIdBS;
	/** The property findByZoneIdBS. */
	private BoundStatement findByZoneIdBS;
	/** The property findByClass. */
	private BoundStatement findByClass;
	/** The property updateUserDeviceBS. */
	private BoundStatement updateUserDeviceBS;
	/** The property updateHomeDeviceBS. */
	private BoundStatement updateHomeDeviceBS;
	
	/**
	 * Component post construct.
	 */
	@PostConstruct
	public void init() {
		super.setEntityManager(this.entityManager);
		this.findById = new BoundStatement(this.cassandraConnector.getFindById());
		this.findByHomeIdBS = new BoundStatement(this.cassandraConnector.getFindByHomeIdPS());
		this.findByZoneIdBS = new BoundStatement(this.cassandraConnector.getFindByZoneIdPS());
		this.findByClass = new BoundStatement(this.cassandraConnector.getFindByClass());
		this.updateUserDeviceBS = new BoundStatement(this.cassandraConnector.getUpdateUserDevicePS());
		this.updateHomeDeviceBS = new BoundStatement(this.cassandraConnector.getUpdateHomeDevicePS());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeviceDTO findById(@NotNull @Min(value = 1L) Long deviceId) throws InformationException {
		ResultSet results = this.cassandraConnector.getSession().execute(this.findById.bind(deviceId));
		Row row = results.one();
		DeviceDTO dto = new DeviceDTO(
				row.getLong("id"), 
				row.getLong("home_id"), 
				row.getString("name"),
				row.getBool("status"), 
				row.getInt("value"), 
				row.getString("device_class"), 
				row.getString("device_type"), 
				row.getInt("operation_level"), 
				row.getInt("device_pin_id"), 
				row.getInt("xbee_16bits_msb"), 
				row.getInt("xbee_16bits_lsb"));
		return dto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByHomeId(@NotNull @Min(value = 1L) Long homeId) throws InformationException {
		ResultSet results = this.cassandraConnector.getSession().execute(this.findByHomeIdBS.bind(homeId));
		List<DeviceDTO> list = new ArrayList<DeviceDTO>();
		for (Row row : results) {
			list.add(new DeviceDTO(
					row.getLong("id"), 
					row.getLong("home_id"), 
					row.getString("name"),
					row.getBool("status"), 
					row.getInt("value"), 
					row.getString("device_class"), 
					row.getString("device_type"), 
					row.getInt("operation_level"), 
					row.getInt("device_pin_id"), 
					row.getInt("xbee_16bits_msb"), 
					row.getInt("xbee_16bits_lsb")));
		}
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByZoneId(@NotNull @Min(value = 1L) Long zoneId) throws InformationException {
		ResultSet results = this.cassandraConnector.getSession().execute(this.findByZoneIdBS.bind(zoneId));
		List<DeviceDTO> list = new ArrayList<DeviceDTO>();
		for (Row row : results) {
			list.add(new DeviceDTO(
					row.getLong("id"), 
					row.getLong("home_id"), 
					row.getString("name"),
					row.getBool("status"), 
					row.getInt("value"), 
					row.getString("device_class"), 
					row.getString("device_type"), 
					row.getInt("operation_level"), 
					row.getInt("device_pin_id"), 
					row.getInt("xbee_16bits_msb"), 
					row.getInt("xbee_16bits_lsb")));
		}
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeviceDTO> findByClass(@NotNull @Min(value = 1L) Long homeId, @NotNull EnumDeviceClass deviceClass) throws InformationException {
		ResultSet results = this.cassandraConnector.getSession().execute(this.findByClass.bind(homeId, deviceClass.name()));
		List<DeviceDTO> list = new ArrayList<DeviceDTO>();
		for (Row row : results) {
			list.add(new DeviceDTO(
					row.getLong("id"), 
					row.getLong("home_id"), 
					row.getString("name"),
					row.getBool("status"), 
					row.getInt("value"), 
					row.getString("device_class"), 
					row.getString("device_type"), 
					row.getInt("operation_level"), 
					row.getInt("device_pin_id"), 
					row.getInt("xbee_16bits_msb"), 
					row.getInt("xbee_16bits_lsb")));
		}
		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUserDeviceState(@NotNull DeviceDTO deviceDTO) {
		this.cassandraConnector.getSession().execute(this.updateUserDeviceBS.bind(deviceDTO.getStatus(), 
				deviceDTO.getOperationLevel(), deviceDTO.getId()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateHomeDeviceState(@NotNull DeviceDTO deviceDTO) {
		this.cassandraConnector.getSession().execute(this.updateHomeDeviceBS.bind(deviceDTO.getStatus(), 
				deviceDTO.getValue(), deviceDTO.getOperationLevel(), deviceDTO.getId()));
	}
}
