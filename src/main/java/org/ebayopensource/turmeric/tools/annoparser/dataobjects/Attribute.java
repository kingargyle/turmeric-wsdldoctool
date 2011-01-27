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
 * Meant to be used as a holder for attribute of elements in a
 * wsdl/xsd document.
 *
 * @author srengarajan
 */
public class Attribute {
	
	private String name;
	
	private String value;
	
	/**
	 * getter for the attribute value.
	 *
	 * @return attribute value
	 */
	public String getValue() {
		return value;
	}
	
	
	/**
	 * setter for attribute value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	/**
	 * name of the attribute.
	 *
	 * @return attribute name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * setter for attribute name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println("name : " + name + "value " + value);
	}

}
