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

/**
 * 
 * @author Andres Solorzano
 *
 */
public class ProfileZone implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = 5899972459709357259L;

	/** The property pk. */
	@NotNull
	private ProfileZonePK pk;

	/** The property active. */
	@NotNull
	private Boolean active;

	/** The property zone. */
	@NotNull
	private Zone zone;

	/**
	 * Default constructor.
	 */
	public ProfileZone() {
		// Nothing to do.
	}

	/**
	 * 
	 * @return
	 */
	public ProfileZonePK getPk() {
		return pk;
	}

	/**
	 * 
	 * @param pk
	 */
	public void setPk(ProfileZonePK pk) {
		this.pk = pk;
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
	 * Gets the zone property value.
	 * 
	 * @return the zone property value.
	 */
	public Zone getZone() {
		return zone;
	}

	/**
	 * Sets the value to the zone property.
	 * 
	 * @param zone
	 *            the zone to set.
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileZone other = (ProfileZone) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
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
		return "ProfileZone [pk=" + pk + ", active=" + active + "]";
	}

}