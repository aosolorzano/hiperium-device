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
package com.hiperium.device.model.control;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hiperium.device.common.utils.EnumDeviceClass;
import com.hiperium.device.common.utils.EnumDeviceType;

/**
 * This class represent an electronic device that is stored in NOSQL data base.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class Device implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = -2565119537745673792L;

	/** The property id. */
	@Min(value = 1L)
	private Long id;

	/** The property homeId. */
	@NotNull
	@Min(value = 1L)
	private Long homeId;

	/** The property zoneId. */
	@NotNull
	@Min(value = 1L)
	private Long zoneId;

	/** The property fatherId. */
	@NotNull
	@Min(value = 1L)
	private Long fatherId;

	/** The property name. */
	@NotNull
	@Size(min = 1, max = 100)
	private String name;

	/** The property active. */
	@NotNull
	private Boolean active;

	/** The property xbeeAddress. */
	@NotNull
	@Size(min = 1, max = 50)
	private String xbeeAddress;

	/** The property xbee16BitAddress. */
	@NotNull
	@Size(min = 1, max = 20)
	private String xbee16BitAddress;

	/** The property pinOperationCode. */
	@NotNull
	@Size(min = 1, max = 4)
	private String pinOperationCode;

	/** The property operationLevel. */
	@NotNull
	@Min(value = 0)
	@Max(value = 100)
	private Integer operationLevel;

	/** The property closeDevicePinId. */
	private Integer closeDevicePinId;

	/** The property openDevicePinId. */
	private Integer openDevicePinId;

	/** The property closedDeviceSensorId. */
	private Integer closedDeviceSensorId;

	/** The property openedDeviceSensorId. */
	private Integer openedDeviceSensorId;

	/** The property deviceClass. */
	@NotNull
	private EnumDeviceClass deviceClass;

	/** The property deviceType. */
	@NotNull
	private EnumDeviceType deviceType;

	/**
	 * Default constructor.
	 */
	public Device() {
		this.deviceClass = EnumDeviceClass.ACTUATOR;
		this.deviceType = EnumDeviceType.ELECTRIC_LIGHT;
	}

	/**
	 * Get the id property.
	 * 
	 * @return the id property.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id property.
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the zoneId property.
	 * 
	 * @return the zoneId property.
	 */
	public Long getZoneId() {
		return zoneId;
	}

	/**
	 * Set the zoneId property.
	 * 
	 * @param zoneId
	 *            the zoneId to set.
	 */
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * Get the name property.
	 * 
	 * @return the name property.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name property.
	 * 
	 * @param name
	 *            the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the active property.
	 * 
	 * @return the active.
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets the active property.
	 * 
	 * @param active
	 *            the active to set.
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Get the xbeeAddress property.
	 * 
	 * @return the xbeeAddress property.
	 */
	public String getXbeeAddress() {
		return xbeeAddress;
	}

	/**
	 * Set the xbeeAddress property.
	 * 
	 * @param xbeeAddress
	 *            the xbeeAddress to set.
	 */
	public void setXbeeAddress(String xbeeAddress) {
		this.xbeeAddress = xbeeAddress;
	}

	/**
	 * Get the pinOperationCode property.
	 * 
	 * @return the pinOperationCode property.
	 */
	public String getPinOperationCode() {
		return pinOperationCode;
	}

	/**
	 * Set the pinOperationCode property.
	 * 
	 * @param pinOperationCode
	 *            the pinOperationCode to set.
	 */
	public void setPinOperationCode(String pinOperationCode) {
		this.pinOperationCode = pinOperationCode;
	}

	/**
	 * Get the operationLevel property.
	 * 
	 * @return the operationLevel property.
	 */
	public Integer getOperationLevel() {
		return operationLevel;
	}

	/**
	 * Set the operationLevel property.
	 * 
	 * @param operationLevel
	 *            the operationLevel to set.
	 */
	public void setOperationLevel(Integer operationLevel) {
		this.operationLevel = operationLevel;
	}

	/**
	 * Get the closeDevicePinId property.
	 * 
	 * @return the closeDevicePinId property.
	 */
	public Integer getCloseDevicePinId() {
		return closeDevicePinId;
	}

	/**
	 * Set the closeDevicePinId property.
	 * 
	 * @param closeDevicePinId
	 *            the closeDevicePinId to set.
	 */
	public void setCloseDevicePinId(Integer closeDevicePinId) {
		this.closeDevicePinId = closeDevicePinId;
	}

	/**
	 * Get the openDevicePinId property.
	 * 
	 * @return the openDevicePinId property.
	 */
	public Integer getOpenDevicePinId() {
		return openDevicePinId;
	}

	/**
	 * Set the openDevicePinId property.
	 * 
	 * @param openDevicePinId
	 *            the openDevicePinId to set.
	 */
	public void setOpenDevicePinId(Integer openDevicePinId) {
		this.openDevicePinId = openDevicePinId;
	}

	/**
	 * Get the closedDeviceSensorId property.
	 * 
	 * @return the closedDeviceSensorId property.
	 */
	public Integer getClosedDeviceSensorId() {
		return closedDeviceSensorId;
	}

	/**
	 * Set the closedDeviceSensorId property.
	 * 
	 * @param closedDeviceSensorId
	 *            the closedDeviceSensorId to set.
	 */
	public void setClosedDeviceSensorId(Integer closedDeviceSensorId) {
		this.closedDeviceSensorId = closedDeviceSensorId;
	}

	/**
	 * Get the openedDeviceSensorId property.
	 * 
	 * @return the openedDeviceSensorId property.
	 */
	public Integer getOpenedDeviceSensorId() {
		return openedDeviceSensorId;
	}

	/**
	 * Set the openedDeviceSensorId property.
	 * 
	 * @param openedDeviceSensorId
	 *            the openedDeviceSensorId to set.
	 */
	public void setOpenedDeviceSensorId(Integer openedDeviceSensorId) {
		this.openedDeviceSensorId = openedDeviceSensorId;
	}

	/**
	 * Get the deviceClass property.
	 * 
	 * @return the deviceClass property.
	 */
	public EnumDeviceClass getDeviceClass() {
		return deviceClass;
	}

	/**
	 * Set the deviceClass property.
	 * 
	 * @param deviceClass
	 *            the deviceClass to set.
	 */
	public void setDeviceClass(EnumDeviceClass deviceClass) {
		this.deviceClass = deviceClass;
	}

	/**
	 * Get the deviceType property.
	 * 
	 * @return the deviceType property.
	 */
	public EnumDeviceType getDeviceType() {
		return deviceType;
	}

	/**
	 * Set the deviceType property.
	 * 
	 * @param deviceType
	 *            the deviceType to set.
	 */
	public void setDeviceType(EnumDeviceType deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * @return the xbee16BitAddress
	 */
	public String getXbee16BitAddress() {
		return xbee16BitAddress;
	}

	/**
	 * @param xbee16BitAddress
	 *            the xbee16BitAddress to set
	 */
	public void setXbee16BitAddress(String xbee16BitAddress) {
		this.xbee16BitAddress = xbee16BitAddress;
	}

	/**
	 * @return the fatherId
	 */
	public Long getFatherId() {
		return fatherId;
	}

	/**
	 * @param fatherId
	 *            the fatherId to set
	 */
	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	/**
	 * @return the homeId
	 */
	public Long getHomeId() {
		return homeId;
	}

	/**
	 * @param homeId
	 *            the homeId to set
	 */
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Device [id=" + id + ", zoneId=" + zoneId + ", fatherId="
				+ fatherId + ", name=" + name + ", active=" + active
				+ ", xbeeAddress=" + xbeeAddress + ", xbee16BitAddress="
				+ xbee16BitAddress + ", deviceClass=" + deviceClass
				+ ", deviceType=" + deviceType + "]";
	}

}
