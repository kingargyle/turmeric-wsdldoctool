/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

/**
 * The Class AttributeElement.
 *
 * @author sdaripelli
 */
public class AttributeElement extends Element {
	
	
	private String baseType;

	/**
	 * Gets the base type.
	 *
	 * @return the base type
	 */
	public String getBaseType() {
		return baseType;
	}

	/**
	 * Sets the base type.
	 *
	 * @param baseType the new base type
	 */
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

}
