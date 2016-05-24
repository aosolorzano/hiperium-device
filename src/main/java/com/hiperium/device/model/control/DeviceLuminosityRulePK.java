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

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents the primary key of the device luminosity rules.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class DeviceLuminosityRulePK implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = -4024855100351440978L;

	/** The property deviceId. */
	@Min(value = 1L)
	private Long deviceId;

	/** The property ruleId. */
	@Min(value = 1L)
	private Long ruleId;
	
	/**
	 * Default Constructor.
	 */
	public DeviceLuminosityRulePK() {
		// Nothing to do.
	}

	/**
	 * 
	 * @param deviceId
	 * @param luminosityRuleId
	 */
	public DeviceLuminosityRulePK(Long deviceId, Long luminosityRuleId) {
		this.deviceId = deviceId;
		this.ruleId = luminosityRuleId;
	}

	/**
	 * Gets the deviceId property.
	 * 
	 * @return the deviceId.
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * Sets the deviceId property.
	 * 
	 * @param deviceId
	 *            the deviceId to set.
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 
	 * @return
	 */
	public Long getRuleId() {
		return ruleId;
	}

	/**
	 * 
	 * @param ruleId
	 */
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceLuminosityRulePK other = (DeviceLuminosityRulePK) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (ruleId == null) {
			if (other.ruleId != null)
				return false;
		} else if (!ruleId.equals(other.ruleId))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "DeviceLuminosityRulePK [deviceId=" + deviceId + ", ruleId="
				+ ruleId + "]";
	}

}