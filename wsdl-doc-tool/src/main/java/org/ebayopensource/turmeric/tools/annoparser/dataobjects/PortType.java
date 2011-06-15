/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Models the lightweight wsdl Porttypes to capture primarily
 * association with operations, name and documentation information.
 *
 * @author sdaripelli
 */
public class PortType {
	
	/**
	 * captures the name of the PortType 
	 */
	private String name;
	
	private ParsedAnnotationInfo annotations;
	
	
	/**
	 * Captures association with Operations
	 */
	private List<OperationHolder> operations;

	/**
	 * Gets the name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	

	/**
	 * Gets the operations.
	 *
	 * @return operations
	 */
	public List<OperationHolder> getOperations() {
		return operations;
	}

	/**
	 * Sets the operations.
	 *
	 * @param operations the new operations
	 */
	public void setOperations(List<OperationHolder> operations) {
		this.operations = operations;
	}
	
	/**
	 * Adds the operation.
	 *
	 * @param operation utility for adding to the collection
	 */
	public void addOperation(OperationHolder operation){
		if(operations==null){
			operations=new ArrayList<OperationHolder>();
		}
		operations.add(operation);
	}

	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public ParsedAnnotationInfo getAnnotations() {
		return annotations;
	}

	/**
	 * Sets the annotations.
	 *
	 * @param annotations the new annotations
	 */
	public void setAnnotations(ParsedAnnotationInfo annotations) {
		this.annotations = annotations;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer retVal=new StringBuffer();
		retVal.append("=========================================================\n");
		retVal.append("PortType Name: " +name + "\n");
		retVal.append("Operations : " + "\n");
		if(annotations!=null){
			retVal.append(name +" Port Type Annotations: \n");
			retVal.append("=========================================================\n");
			retVal.append(annotations.toString());
			retVal.append("=========================================================\n");
		}
		if(operations!=null){
			for(OperationHolder attr:operations) {
				if(attr != null) {
					retVal.append(attr.toString()+ "\n");
				}
			}
		}
		retVal.append("=========================================================\n");
		return retVal.toString();
	}

}
