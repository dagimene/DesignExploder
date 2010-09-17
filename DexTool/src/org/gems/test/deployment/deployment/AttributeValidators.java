/******************************************************************************
 * Copyright (c) 2005, 2006 Jules White.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Jules White - initial API and implementation 
 ****************************************************************************/


package org.gems.test.deployment.deployment;


import org.eclipse.gmt.gems.model.ModelObject;



public class AttributeValidators extends org.eclipse.gmt.gems.model.AttributeValidators {

    private static AttributeValidators instance_ = new AttributeValidators();
    
    public static AttributeValidators getInstance() {
        return instance_;
    }
    /**
     * 
     */
    private AttributeValidators() {
        super();        
    }
    

    public void createValidators() {
		
        super.createValidators();
    }
}
