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

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.device.common.utils.EnumDeviceClass;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface DeviceDAO {

	/**
	 * 
	 * @param deviceId
	 * @return
	 * @throws InformationException
	 */
	DeviceDTO findById(@NotNull @Min(value = 1L) Long deviceId)
			throws InformationException;

	/**
	 * 
	 * @param homeId
	 * @return
	 * @throws InformationException
	 */
	List<DeviceDTO> findByHomeId(@NotNull @Min(value = 1L) Long homeId)
			throws InformationException;

	/**
	 * 
	 * @param zoneId
	 * @return
	 * @throws InformationException
	 */
	List<DeviceDTO> findByZoneId(@NotNull @Min(value = 1L) Long zoneId)
			throws InformationException;

	/**
	 * 
	 * @param deviceDTO
	 */
	void updateUserDeviceState(@NotNull DeviceDTO deviceDTO);

	/**
	 * 
	 * @param deviceDTO
	 */
	void updateHomeDeviceState(@NotNull DeviceDTO deviceDTO);

	/**
	 * 
	 * @param homeId
	 * @param deviceClass
	 * @return
	 * @throws InformationException
	 */
	List<DeviceDTO> findByClass(@NotNull @Min(value = 1L) Long homeId,
			@NotNull EnumDeviceClass deviceClass) throws InformationException;
}
