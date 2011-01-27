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
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class ComplexType.
 *
 * @author srengarajan
 */

public class ComplexType extends AbstractType implements
		Comparable<ComplexType> {

	/**
     *  Direct children elements(type <Element>) of this ComplexType
     *  Note that inherited children can be collected by traversing 
     *  up the hierarchy through parentType  
     */
	private Set<Element> childElements;
	
	private Set<AttributeElement> simpleAttributeContent;

	/**
     *  this collection relates the complex type to all its instance elements
     *  its extremely useful in traversals for building Usage paths
     */
	private List<Element> instanceElements;

	/**
	 * points to the name of the parent Complex Type element. Used for search 
	 * and retrieval of the Complex Type
	 */
	private String parentType = null;

	/**
	 * Gets the simple attribute content.
	 *
	 * @return the simple attribute content
	 */
	public Set<AttributeElement> getSimpleAttributeContent() {
		return simpleAttributeContent;
	}

	/**
	 * Adds the simple attribute content.
	 *
	 * @param simpleAttributeContent the simple attribute content
	 */
	public void addSimpleAttributeContent(AttributeElement simpleAttributeContent) {
		if(this.simpleAttributeContent==null){
			this.simpleAttributeContent=new TreeSet<AttributeElement>();
		}
		this.simpleAttributeContent.add(simpleAttributeContent);
	}
	
	/**
	 * Gets the parent type.
	 *
	 * @return getter for parent type
	 */
	public String getParentType() {
		return parentType;
	}

	/**
	 * Sets the parent type.
	 *
	 * @param parentType the new parent type
	 */
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	/**
	 * inheriting child ComplexTypes. Useful for traversal of inherited 
	 * types
	 */
	private List<String> childType = new ArrayList<String>();

	/**
	 * Sets the child type.
	 *
	 * @param childType the new child type
	 */
	public void setChildType(List<String> childType) {
		this.childType = childType;
	}

	/**
	 * Gets the child type.
	 *
	 * @return Collection of child types
	 */
	public List<String> getChildType() {
		return childType;
	}

	/**
	 * Gets the child elements.
	 *
	 * @return Collection of child elements ( Composition relationship )
	 */
	public Set<Element> getChildElements() {
		return childElements;
	}

	
	/**
	 * Sets the child elements.
	 *
	 * @param elements the new child elements
	 */
	public void setChildElements(Set<Element> elements) {
		this.childElements = elements;
	}

	/**
	 * Gets the instance elements.
	 *
	 * @return collection of instances of this complex type
	 */
	public List<Element> getInstanceElements() {
		return instanceElements;
	}

	
	/**
	 * Sets the instance elements.
	 *
	 * @param elements the new instance elements
	 */
	public void setInstanceElements(List<Element> elements) {
		this.instanceElements = elements;
	}

	
	/**
	 * Utility method used to console print this Complex type.
	 */
	public void print() {
		int size = childElements.size();
		System.out.println("ComplexType::::::::::Name = " + super.getName());
		System.out.println("Printing child Elements { ");
		// System.out.println("Name: " + name + "size: " + size);
		for (Element elem : childElements) {
			elem.print();
		}
		System.out.println(" } ");

		System.out.println("Parent Type ......." + this.getParentType());
		System.out.println("Child Type ........" + this.getChildType());

		System.out.println("Printing Instance Elements { ");
		System.out.println("Name: " + name + "size: " + size);
		if (instanceElements != null) {
			for (Element elem1 : instanceElements) {
				if (elem1 != null)
					elem1.print();
			}
		}
		System.out.println(" } ");
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ComplexType object) {
		return this.getName().toUpperCase().compareTo(
				object.getName().toUpperCase());
	}
}