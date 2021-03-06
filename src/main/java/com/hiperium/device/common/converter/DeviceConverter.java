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
package com.hiperium.device.common.converter;

import org.apache.log4j.Logger;

import com.google.gson.JsonSyntaxException;
import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.gson.GsonConverterUtil;
import com.hiperium.commons.client.http.HttpClient;

/**
 * This is a utility class for serializing or deserializing java objects in a
 * JSON format.
 * 
 * @author Andres Solorzano
 */
public final class DeviceConverter extends GsonConverterUtil {

	/** The LOGGER property for logger messages. */
	private static final Logger LOGGER = Logger.getLogger(HttpClient.class);

	/**
	 * Default constructor.
	 */
	public DeviceConverter() {
		super();
	}
	
	/**
	 * Returns a specific object implementation from a JSON.
	 * 
	 * @param jsonData
	 *            Single object to be parsed.
	 * @return a specific object implementation from a JSON.
	 */
	public DeviceDTO fromJsonToDeviceDTO(String jsonData) {
		DeviceDTO register = null;
		try {
			register = super.getGson().fromJson(jsonData, DeviceDTO.class);
		} catch (JsonSyntaxException e) {
			LOGGER.error("fromJsonToDeviceActivationDTO() - ERROR: " + e.getMessage());
		}
		return register;
	}
}
