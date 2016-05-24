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
package com.hiperium.device.bo.generic;

import javax.inject.Inject;

import com.hiperium.device.dao.factory.DataAccessFactory;

/**
 * This class is an generalization of business objects.
 *
 * @author Andres Solorzano
 *
 */
public class GenericBO {

    /** The property daoFactory. */
    @Inject
    private DataAccessFactory daoFactory;

    /**
     *
     */
    public GenericBO() {
        // Nothing to do.
    }

    /**
     *
     * @return
     */
    public DataAccessFactory getDaoFactory() {
        return daoFactory;
    }

}
