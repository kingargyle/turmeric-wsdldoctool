/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

import java.util.List;


/**
 * Representation of a XSD SimpleType
 * Models association with Enumerations, instance elements and
 * extended type.
 *
 * @author srengarajan
 */
public class SimpleType extends AbstractType {

	/**
	 * Collection of enumerations of this type
	 */
	private List<EnumElement> enums;
	
	/**
	 * Collection of instances of this simple type 
	 */
	private List<Element> instanceElements;
	

	/**
	 * extended base type of the enumeration if any
	 */
	private String base;
  
  
	/**
	 * Gets the base.
	 *
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Sets the base.
	 *
	 * @param base the base to set
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Gets the instance elements.
	 *
	 * @return instances
	 */
	public List<Element> getInstanceElements() {
		return instanceElements;
	}
  
	
	/**
	 * Sets the instance elements.
	 *
	 * @param elements setter for instances
	 */
	public void setInstanceElements(List<Element> elements) {
		this.instanceElements = elements;
	}
  
	
	/**
	 * Gets the enums.
	 *
	 * @return Collection of Enumerations
	 */
	public List<EnumElement> getEnums() {
		return this.enums;
	}

	/**
	 * Sets the enums.
	 *
	 * @param enums setter for enumerations
	 */
	public void setEnums(List<EnumElement> enums) {
		this.enums = enums;
	}
  
	
	/**
	 * method to dump the SimpleType contents to console output.
	 */
	public void print() {
		System.out.println("Name: " + name + "description" + super.annotations.getDocumentation());
		int size = enums.size();
		for(EnumElement elem: enums) {
			elem.print();
		}
		System.out.println(".....................................................");
	}

}