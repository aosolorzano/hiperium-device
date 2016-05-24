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

/**
 * This class represents the luminosity rule that will be applied to a group of
 * devices at night.
 * 
 * @author Andres Solorzano.
 */
@XmlRootElement
public class LuminosityRule implements Serializable {

	/**
	 * The property serialVersionUID.
	 */
	private static final long serialVersionUID = -8853662772732714127L;

	/** The property id. */
	@Min(value = 1L)
	private Long id;

	/** The property active. */
	@NotNull
	private Boolean active;

	/** The property comments. */
	@Size(max = 300)
	private String comments;

	/**
	 * Default constructor.
	 */
	public LuminosityRule() {
		// Nothing to do.
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
	 * Gets the comments property.
	 * 
	 * @return the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments property.
	 * 
	 * @param comments
	 *            the comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
		LuminosityRule other = (LuminosityRule) obj;
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
		return "LuminosityRule [id=" + id + ", active=" + active
				+ ", comments=" + comments + "]";
	}

}
