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

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represents luminosity rules for devices.
 * 
 * @author Andres Solorzano
 */
@XmlRootElement
public class DeviceLuminosityRule implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = 946538265075695219L;

	/** The property pk. */
	@NotNull
	private DeviceLuminosityRulePK pk;

	/** The property active. */
	@NotNull
	private Boolean active;

	/**
	 * Default constructor.
	 */
	public DeviceLuminosityRule() {
		this.pk = new DeviceLuminosityRulePK();
	}

	/**
	 * 
	 * @param deviceId
	 * @param luminosityRuleId
	 */
	public DeviceLuminosityRule(Long deviceId, Long luminosityRuleId) {
		this.pk = new DeviceLuminosityRulePK(deviceId, luminosityRuleId);
	}

	/**
	 * 
	 * @return
	 */
	public DeviceLuminosityRulePK getPk() {
		return pk;
	}

	/**
	 * 
	 * @param pk
	 */
	public void setPk(DeviceLuminosityRulePK pk) {
		this.pk = pk;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * 
	 * @param active
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		DeviceLuminosityRule other = (DeviceLuminosityRule) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceLuminosityRule [pk=" + pk + ", active=" + active + "]";
	}

}
