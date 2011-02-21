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
 * The Class EnumElement.Representation for type Enumeration
 *
 * @author srengarajan
 * 
 */
public class EnumElement implements Comparable<EnumElement> {

	/**
	 * annotations associated with enum
	 */
	public ParsedAnnotationInfo annotations;

	

	/**
	 * represents the extended type
	 */
	public String type;

	/**
	 * captures the value of this enum 
	 */
	public String value;
	

	/**
	 * Gets the type.
	 *
	 * @return type
	 * getter for type
	 */
	public String getType() {
		return type;
	}

	
	/**
	 * Sets the type.
	 *
	 * @param type setter for type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the annotations.
	 *
	 * @return annotations
	 */
	public ParsedAnnotationInfo getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations setter for annotations
	 */
	public void setAnnotations(ParsedAnnotationInfo annotations) {
		this.annotations = annotations;
	}

	/**
	 * Gets the value.
	 *
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 *
	 * @param val setter for value
	 */
	public void setValue(String val) {
		this.value = val;
	}
	
	

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(EnumElement object) {
		  return this.getValue().toUpperCase().compareTo(object.getValue().toUpperCase());
	}
	
	@Override
	public String toString() {
		StringBuffer retVal=new StringBuffer();
		retVal.append("Enum Type: " +type + "\n");
		retVal.append("Enum Value: " +value+ "\n");
		
		if(annotations!=null){
			retVal.append("Annotations: \n");
			retVal.append(annotations.toString());
		}
		return retVal.toString();
	}
}