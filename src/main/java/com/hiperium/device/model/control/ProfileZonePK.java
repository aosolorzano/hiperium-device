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

/**
 * This class represents the primary key of the user's assigned zones.
 * 
 * @author Andres Solorzano
 */
public class ProfileZonePK implements Serializable {

	/** */
	private static final long serialVersionUID = -5875028618074908524L;

	/** The property profileId. */
	@Min(value = 1L)
	private Long profileId;

	/** The property zoneId. */
	@Min(value = 1L)
	private Long zoneId;

	/**
	 * Default constructor.
	 */
	public ProfileZonePK() {
		// Nothing to do.
	}

	/**
	 * 
	 * @param userId
	 * @param zoneId
	 */
	public ProfileZonePK(Long profileId, Long zoneId) {
		this.profileId = profileId;
		this.zoneId = zoneId;
	}

	/**
	 * Gets the profileId property value.
	 * 
	 * @return the profileId property value.
	 */
	public Long getProfileId() {
		return profileId;
	}

	/**
	 * Sets the value to the profileId property.
	 * 
	 * @param profileId
	 *            the profileId to set.
	 */
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	/**
	 * Gets the zoneId property value.
	 * 
	 * @return the zoneId property value.
	 */
	public Long getZoneId() {
		return zoneId;
	}

	/**
	 * Sets the value to the zoneId property.
	 * 
	 * @param zoneId
	 *            the zoneId to set.
	 */
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
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
		result = prime * result
				+ ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + ((zoneId == null) ? 0 : zoneId.hashCode());
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
		ProfileZonePK other = (ProfileZonePK) obj;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (zoneId == null) {
			if (other.zoneId != null)
				return false;
		} else if (!zoneId.equals(other.zoneId))
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
		return "ProfileZonePK [profileId=" + profileId + ", zoneId=" + zoneId
				+ "]";
	}

}
