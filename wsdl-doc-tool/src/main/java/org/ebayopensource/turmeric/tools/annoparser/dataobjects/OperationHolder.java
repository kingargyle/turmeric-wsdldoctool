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
 * Captures the wsdl operation with its name documentation, input part elements,
 * output part elements.
 * 
 * @author sdaripelli
 */
public class OperationHolder implements Comparable<OperationHolder> {

	/**
	 * Captures Operation name
	 */
	private String name;

	private ParsedAnnotationInfo annotations;

	/**
	 * Collection of input elements
	 */
	private List<Element> inputElement = new ArrayList<Element>();

	/**
	 * Collection of output elements
	 */
	private List<Element> outputElement = new ArrayList<Element>();

	/**
	 * Gets the name.
	 * 
	 * @return operation name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the input types.
	 * 
	 * @return collection of types
	 */
	public List<Element> getInputTypes() {
		return this.inputElement;
	}

	/**
	 * Gets the output types.
	 * 
	 * @return collection of types
	 */
	public List<Element> getOutputTypes() {
		return this.outputElement;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Adds the input.
	 * 
	 * @param input
	 *            adding input element to the collection
	 */
	public void addInput(Element input) {
		this.inputElement.add(input);
	}

	/**
	 * Adds the output.
	 * 
	 * @param output
	 *            element to the collection
	 */
	public void addOutput(Element output) {
		this.outputElement.add(output);
	}

	/**
	 * Sets the input.
	 * 
	 * @param inputs
	 *            utility for setting the entire collection of input parts
	 */
	public void setInput(List<Element> inputs) {
		this.inputElement = inputs;
	}

	/**
	 * Sets the output.
	 * 
	 * @param outputs
	 *            utility for setting the entire collection of output parts
	 */
	public void setOutput(List<Element> outputs) {
		this.outputElement = outputs;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(OperationHolder o) {
		return this.getName().compareTo(o.getName());
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
	 * @param annotations
	 *            the new annotations
	 */
	public void setAnnotations(ParsedAnnotationInfo annotations) {
		this.annotations = annotations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer retVal = new StringBuffer();
		retVal.append("=========================================================\n");
		retVal.append("Operation Name: " + name + "\n");
		if (annotations != null) {
			retVal.append(name + " Operation Annotations: \n");
			retVal.append("=========================================================\n");
			retVal.append(annotations.toString());
			retVal.append("=========================================================\n");
		}
		retVal.append("Inputs: " + "\n");
		if (this.inputElement != null) {
			for (Element elem : this.inputElement) {
				retVal.append(elem.toString() + "\n");
			}
		}
		retVal.append("Outputs: " + "\n");
		if (this.outputElement != null) {
			for (Element elem : this.outputElement) {
				retVal.append(elem.toString() + "\n");
			}
		}

		retVal.append("=========================================================\n");
		return retVal.toString();
	}
}