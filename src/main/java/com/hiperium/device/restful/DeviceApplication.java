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
package com.hiperium.device.restful;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

import com.hiperium.commons.client.registry.ServiceRegister;
import com.hiperium.commons.client.registry.path.DeviceRegistryPath;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.path.DeviceRestfulPath;
import com.hiperium.device.common.bean.ConfigurationBean;


/**
 * This class represents the JAX-RS Activation Implementation for JAX-RS
 * applications.
 * 
 * @author Andres Solorzano
 *
 */
@ApplicationPath(DeviceRestfulPath.DEVICE_PATH)
public class DeviceApplication extends Application {
	
	/** The LOGGER property for logger messages. */
	private static final HiperiumLogger LOGGER = HiperiumLogger.getLogger(DeviceApplication.class);

	/** The property client. */
	@Inject
	private CuratorFramework client;

	/** The property servers. */
	private List<ServiceRegister> registers;
	
	/** The property serviceHost. */
	private String serviceHost; 
	/** The property servicePort. */
	private Integer servicePort;
	
    /**
	 * Register all web services in apache zookeeper service registry.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("init() - START");
		this.registers = new ArrayList<ServiceRegister>();
		this.serviceHost = ConfigurationBean.getPropertyValue(ConfigurationBean.SERVER_HOST);
		this.servicePort = Integer.valueOf(ConfigurationBean.getPropertyValue(ConfigurationBean.SERVER_PORT));
		this.registerService(DeviceRestfulPath.DEVICES.concat(DeviceRestfulPath.FIND_DEVICE_BY_HOME_ID), DeviceRegistryPath.FIND_DEVICE_BY_HOME_ID,         "1.0", "");
		this.registerService(DeviceRestfulPath.DEVICES.concat(DeviceRestfulPath.FIND_DEVICE_BY_ZONE_ID), DeviceRegistryPath.FIND_DEVICE_BY_ZONE_ID,         "1.0", "");
		this.registerService(DeviceRestfulPath.DEVICES.concat(DeviceRestfulPath.FIND_DEVICE_BY_CLASS),   DeviceRegistryPath.FIND_DEVICE_BY_CLASS,           "1.0", "");
		this.registerService(DeviceRestfulPath.DEVICES.concat(DeviceRestfulPath.USER_OPERATION),         DeviceRegistryPath.DEVICE_USER_OPERATION,          "1.0", "");
		this.registerService(DeviceRestfulPath.GATEWAY.concat(DeviceRestfulPath.HOME_OPERATION),         DeviceRegistryPath.DEVICE_HOME_OPERATION,   	   "1.0", "");
		this.registerService(DeviceRestfulPath.GATEWAY.concat(DeviceRestfulPath.FIND_DEVICE_BY_HOME_ID), DeviceRegistryPath.GATEWAY_FIND_DEVICE_BY_HOME_ID, "1.0", "");
		this.registerService(DeviceRestfulPath.TASKS.concat(DeviceRestfulPath.CREATE),                   DeviceRegistryPath.CREATE_TASK,                    "1.0", "");
		this.registerService(DeviceRestfulPath.TASKS.concat(DeviceRestfulPath.UPDATE),                   DeviceRegistryPath.UPDATE_TASK,                    "1.0", "");
		this.registerService(DeviceRestfulPath.TASKS.concat(DeviceRestfulPath.DELETE),                   DeviceRegistryPath.DELETE_TASK,                    "1.0", "");
		this.registerService(DeviceRestfulPath.TASKS.concat(DeviceRestfulPath.FIND_TASK_BY_HOME_ID),     DeviceRegistryPath.FIND_TASK_BY_HOME_ID,           "1.0", "");
		this.registerService(DeviceRestfulPath.ZONES.concat(DeviceRestfulPath.FIND_ZONE_BY_PROFILE_ID),  DeviceRegistryPath.FIND_ZONE_BY_PROFILE_ID,        "1.0", "");
		this.registerService(DeviceRestfulPath.ZONES.concat(DeviceRestfulPath.FIND_ZONE_BY_ID),          DeviceRegistryPath.FIND_ZONE_BY_ID,                "1.0", "");
		this.registerService(DeviceRestfulPath.ZONES.concat(DeviceRestfulPath.UPDATE),                   DeviceRegistryPath.FIND_TASK_BY_HOME_ID,           "1.0", "");
		LOGGER.info("init() - END");
	}
	
	/**
	 * 
	 * @param servicePath
	 * @param serviceName
	 * @param serviceVersion
	 * @param serviceDetails
	 */
	public void registerService(String servicePath, String serviceName, String serviceVersion, String serviceDetails) {
		String serviceURI = this.getUri(servicePath);
		ServiceRegister server;
		try {
			server = new ServiceRegister(this.client, this.servicePort, serviceURI, serviceName, DeviceRegistryPath.BASE_PATH, serviceDetails, serviceVersion);
			server.start();
			this.registers.add(server);
			LOGGER.info("Service added to the Registry: " + serviceURI);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Close all opened resource in this component.
	 */
	@PreDestroy
	public void destroy() {
		for(ServiceRegister register : this.registers) {
			CloseableUtils.closeQuietly(register);
		}
		this.registers.clear();
		this.client.close();
	}
	
	/**
	 * 
	 * @param servicePath
	 * @return
	 */
	private String getUri(final String servicePath) {
		return String.format("{scheme}://%s:{port}%s%s%s", 
				this.serviceHost,
				DeviceRestfulPath.DEVICE_CONTEXT_ROOT, 
				DeviceRestfulPath.DEVICE_PATH, 
				servicePath);
	}
}
