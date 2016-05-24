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
package com.hiperium.device.logging.bo.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.dto.ServiceDetailsDTO;
import com.hiperium.commons.client.registry.path.LoggingRegistryPath;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.device.bo.generic.GenericBO;
import com.hiperium.device.common.services.LoggingServiceManager;
import com.hiperium.device.logging.bo.LoggingManagerBO;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Startup
@Singleton
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class LoggingManagerBOImpl extends GenericBO implements LoggingManagerBO {

	/** The property log. */
    @Inject
    private HiperiumLogger log;
    
    /** The property loggingServiceManager. */
    @EJB
    private LoggingServiceManager loggingServiceManager;
    
    /** The property curatorClient. */
	@Inject
	private CuratorFramework curatorClient;
	/** The property serviceDiscovery. */
	private ServiceDiscovery<ServiceDetailsDTO> serviceDiscovery;
	/** The property serializer. */
	private JsonInstanceSerializer<ServiceDetailsDTO> serializer;
	
    /**
     * Initializes the component.
     */
    @PostConstruct
    public void init() {
    	this.serializer = new JsonInstanceSerializer<ServiceDetailsDTO>(ServiceDetailsDTO.class); // Payload Serializer
		this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetailsDTO.class)
				.client(this.curatorClient)
				.basePath(LoggingRegistryPath.BASE_PATH)
				.serializer(this.serializer)
				.build();
    }
    
    /**
	 * {@inheritDoc} 
	 */
    @Asynchronous
	public void create(@NotNull DeviceDTO deviceDTO, String tokenID) throws Exception {
		this.log.debug("create - START");
		this.loggingServiceManager.createDeviceAudit(this.getServiceURI(LoggingRegistryPath.CREATE_DEVICE_AUDIT), deviceDTO, tokenID); 
		this.log.debug("create - END");
	}
    
    /**
	 * 
	 * @param serviceRegistryPath
	 * @return
	 * @throws Exception
	 */
	private String getServiceURI(String serviceRegistryPath) throws Exception {
		final Collection<ServiceInstance<ServiceDetailsDTO>> services = this.serviceDiscovery.queryForInstances(serviceRegistryPath);
		if(services == null || services.isEmpty()) {
        	throw new Exception("No results found for querying services called: " + serviceRegistryPath);
        } else {
        	for(final ServiceInstance<ServiceDetailsDTO> service : services) {
        		return service.buildUriSpec();
            }
        }
		return null;
	}
}
