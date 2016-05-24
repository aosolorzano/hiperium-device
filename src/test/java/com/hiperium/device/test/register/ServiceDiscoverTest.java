/**
 * 
 */
package com.hiperium.device.test.register;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hiperium.commons.client.dto.ServiceDetailsDTO;
import com.hiperium.commons.client.registry.ServiceRegister;
import com.hiperium.commons.client.registry.path.DeviceRegistryPath;
import com.hiperium.commons.client.registry.path.IdentityRegistryPath;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.path.DeviceRestfulPath;

/**
 * 
 * @author Andres Solorzano
 *
 */
@RunWith(Arquillian.class)
public class ServiceDiscoverTest {

	/**
	 * 
	 * @return
	 */
	@Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "device-service-test.war").addClasses(
        		DeviceRestfulPath.class
        );
    }
	
	/** The LOGGER property for logger messages. */
	private static final HiperiumLogger LOGGER = HiperiumLogger.getLogger(ServiceDiscoverTest.class);
	
	private static final String ZK_HOST = "app.hiperium.com:2181";
	public static final String SERVER_HOST = "app.hiperium.com";
	public static final Integer SERVER_PORT = 8182;
	
	private CuratorFramework curatorClient;
	private ServiceDiscovery<ServiceDetailsDTO> serviceDiscovery;
	private JsonInstanceSerializer<ServiceDetailsDTO> serializer;
	private Client client;
	
	/** The property servers. */
	private List<ServiceRegister> registers;
	
	/**
	 * Initial test initialization.
	 */
    @Before
    public void setUp() {
    	LOGGER.info("setUp() - START");
        this.curatorClient = CuratorFrameworkFactory.newClient(ZK_HOST, new ExponentialBackoffRetry(1000, 3));
        this.curatorClient.start();
        this.client = ClientBuilder.newClient();
        this.registers = new ArrayList<ServiceRegister>();
        LOGGER.info("setUp() - END");
    }
    
    /**
     * Test of get a list of existing registered services.
     */
    @Test @InSequence(1)
    public void queryService() throws Exception {
    	LOGGER.info("queryService() - START");
    	String serviceURI = String.format("{scheme}://%s:{port}%s%s%s", 
    			SERVER_HOST,
    			DeviceRestfulPath.DEVICE_CONTEXT_ROOT, 
    			DeviceRestfulPath.DEVICE_PATH, 
    			DeviceRestfulPath.DEVICES.concat(DeviceRestfulPath.FIND_DEVICE_BY_HOME_ID));
		ServiceRegister server;
		try {
			// CURATOR CLIENT PART FOR REGISTERING SERVICES
			server = new ServiceRegister(this.curatorClient, SERVER_PORT, serviceURI, DeviceRegistryPath.FIND_DEVICE_BY_HOME_ID, 
					IdentityRegistryPath.BASE_PATH, "Service that get devices by home identifier.", "1.0");
			server.start();
			this.registers.add(server);
			LOGGER.debug("Service added to the Registry: " + serviceURI);
			
			// CURATOR CLIENT PART TO QUERY REGISTERED SERVICES
			this.serializer = new JsonInstanceSerializer<ServiceDetailsDTO>(ServiceDetailsDTO.class); // Payload Serializer
    		this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetailsDTO.class)
					.client(this.curatorClient)
					.basePath(IdentityRegistryPath.BASE_PATH)
					.serializer(this.serializer)
					.build();
            final Collection<ServiceInstance<ServiceDetailsDTO>> services = this.serviceDiscovery.queryForInstances(DeviceRegistryPath.FIND_DEVICE_BY_HOME_ID);
            if(services == null || services.isEmpty()) {
            	throw new Exception("No results found for querying services called: " + DeviceRegistryPath.FIND_DEVICE_BY_HOME_ID);
            } else {
            	for(final ServiceInstance<ServiceDetailsDTO> service : services) {
                    final String uri = service.buildUriSpec();
                    LOGGER.debug("Founded Service URI: " + uri);
                    assertNotNull(uri);
                    // Print the service discovered information
                    LOGGER.debug("Service Payload Information: " + service.getPayload().toString());
                }
            }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail();
		}
    	LOGGER.info("queryService() - END");
    }
    
    /**
     * Close all opened resources.
     */
    @After
    public void destroy() {
    	LOGGER.info("destroy() - START");
    	for(ServiceRegister register : this.registers) {
			CloseableUtils.closeQuietly(register);
		}
		this.registers.clear();
		this.curatorClient.close();
		this.client.close();
		LOGGER.info("destroy() - END");
    }
    
}