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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.hiperium.device.model.EnumZoneType;

/**
 * This class represents the existing zones in the smart home.
 * 
 * @author Andres Solorzano.
 */
@XmlRootElement
public class Zone implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = -2844802132428282884L;

	/** The property id. */
	@Min(value = 1L)
	private Long id;

	/** The property homeId. */
	@NotNull
	@Min(value = 1L)
	private Long homeId;

	/** The property name. */
	@Size(max = 100)
	private String name;

	/** The property comments. */
	@Size(max = 300)
	private String comments;

	/** The property iconURL. */
	@Size(max = 100)
	private String iconURL;

	/** The property type. */
	@NotNull
	private EnumZoneType type;
	
	/** The property version. */
	@NotNull
	private Long version;

	/**
	 * Default constructor.
	 */
	public Zone() {
		this.type = EnumZoneType.HALL;
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
	 * Get the homeId property.
	 * 
	 * @return the homeId property.
	 */
	public Long getHomeId() {
		return homeId;
	}

	/**
	 * Set the homeId property.
	 * 
	 * @param homeId
	 *            the homeId to set.
	 */
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
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
	 * Get the comments property.
	 * 
	 * @return the comments property.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Set the comments property.
	 * 
	 * @param comments
	 *            the comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Get the version property.
	 * 
	 * @return the version property.
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * Set the version property.
	 * 
	 * @param version
	 *            the version to set.
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * Get the type property.
	 * 
	 * @return the type property.
	 */
	public EnumZoneType getType() {
		return type;
	}

	/**
	 * Set the type property.
	 * 
	 * @param type
	 *            the type to set.
	 */
	public void setType(EnumZoneType type) {
		this.type = type;
	}

	/**
	 * @return the iconURL
	 */
	public String getIconURL() {
		return iconURL;
	}

	/**
	 * @param iconURL
	 *            the iconURL to set
	 */
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
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
		Zone other = (Zone) obj;
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
		return "Zone [id=" + id + ", homeId=" + homeId + ", name=" + name
				+ ", comments=" + comments + ", version=" + version + ", type="
				+ type + "]";
	}
}
