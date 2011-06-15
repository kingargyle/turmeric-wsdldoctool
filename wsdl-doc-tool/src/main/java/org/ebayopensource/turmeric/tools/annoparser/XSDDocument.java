/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ComplexType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.SimpleType;

/**
 * The Class XSDDocument.
 * 
 * @author srengarajan
 */
public class XSDDocument implements XSDDocInterface {

	private URL documentURL = null;
	private List<Element> elements = new ArrayList<Element>();
	private List<SimpleType> simpleTypes = new ArrayList<SimpleType>();
	private List<ComplexType> complexTypes = new ArrayList<ComplexType>();
	private List<EnumElement> enums = new ArrayList<EnumElement>();

	private Map<String, ComplexType> nameToCTypeMap = new HashMap<String, ComplexType>();
	private Map<String, SimpleType> nameToSimpleTypeMap = new HashMap<String, SimpleType>();
	private Map<String, Element> nameToElemMap = new HashMap<String, Element>();
	private Map<String, List<ComplexType>> elemToComplexTypeMap = new HashMap<String, List<ComplexType>>();
	private Map<String, Set<String>> parentToComplexTypeMap = new HashMap<String, Set<String>>();

	private List<String> xsdsProcessed = new ArrayList<String>();

	/**
	 * Gets the xsds processed.
	 * 
	 * @return the xsds processed
	 */
	public List<String> getXsdsProcessed() {
		return xsdsProcessed;
	}

	/**
	 * Sets the xsds processed.
	 * 
	 * @param xsdsProcessed
	 *            the new xsds processed
	 */
	public void setXsdsProcessed(List<String> xsdsProcessed) {
		this.xsdsProcessed = xsdsProcessed;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Set<String>> getParentToComplexTypeMap() {
		return parentToComplexTypeMap;
	}

	/**
	 * Sets the parent to complex type map.
	 * 
	 * @param parentToComplexTypeMap
	 *            the parent to complex type map
	 */
	public void setParentToComplexTypeMap(
			Map<String, Set<String>> parentToComplexTypeMap) {
		this.parentToComplexTypeMap = parentToComplexTypeMap;
	}

	/**
	 * Adds the parent to complex type map.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 */
	public void addParentToComplexTypeMap(String parent, String child) {
		Set<String> children = this.parentToComplexTypeMap.get(parent);
		if (children == null) {
			children = new HashSet<String>();
			parentToComplexTypeMap.put(parent, children);
		}
		children.add(child);
	}

	/**
	 * Add a ComplextType.
	 * 
	 * @param ctype
	 *            the complext type
	 */
	public void addComplexType(ComplexType ctype) {
		complexTypes.add(ctype);
		this.nameToCTypeMap.put(ctype.getName(), ctype);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ComplexType> getAllComplexTypes() {
		return complexTypes;
	}

	/**
	 * {@inheritDoc}
	 */
	public ComplexType searchCType(String ctypeName) {
		ComplexType ctype = null;
		ctype = (ComplexType) this.nameToCTypeMap.get(ctypeName);
		return ctype;
	}

	/**
	 * {@inheritDoc}
	 */
	public SimpleType searchSimpleType(String simpletypeName) {
		SimpleType simpletype = null;
		simpletype = (SimpleType) this.nameToSimpleTypeMap.get(simpletypeName);
		return simpletype;
	}

	/**
	 * {@inheritDoc}
	 */
	public Element searchIndependentElement(String elementName) {
		Element elem = this.nameToElemMap.get(elementName);
		return elem;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Element> getAllIndependentElements() {
		// TODO Auto-generated method stub
		return elements;
	}

	/**
	 * Adds the independent element.
	 * 
	 * @param element
	 *            the element
	 */
	public void addIndependentElement(Element element) {
		elements.add(element);
		this.nameToElemMap.put(element.getName(), element);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EnumElement> getAllEnums() {
		// TODO Auto-generated method stub
		return enums;
	}

	/**
	 * Adds the enum.
	 * 
	 * @param enumElem
	 *            the enum elem
	 */
	public void addEnum(EnumElement enumElem) {
		enums.add(enumElem);
	}

	/**
	 * Adds the simple type.
	 * 
	 * @param type
	 *            the type
	 */
	public void addSimpleType(SimpleType type) {
		simpleTypes.add(type);
		this.nameToSimpleTypeMap.put(type.getName(), type);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SimpleType> getAllSimpleTypes() {
		// TODO Auto-generated method stub
		return simpleTypes;
	}

	/**
	 * {@inheritDoc}
	 */
	public URL getDocumentURL() {
		return this.documentURL;
	}

	/**
	 * Sets the document url.
	 * 
	 * @param url
	 *            the new document url
	 */
	public void setDocumentURL(URL url) {
		this.documentURL = url;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<ComplexType>> getElementComplexTypeMap() {
		return this.elemToComplexTypeMap;
	}

	/**
	 * Sets the element to complex type map.
	 * 
	 * @param elementCTypeMap
	 *            the new element to complex type map
	 */
	public void setElementToComplexTypeMap(
			Map<String, List<ComplexType>> elementCTypeMap) {
		this.elemToComplexTypeMap = elementCTypeMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer retVal = new StringBuffer();
		retVal.append("Complex Types\n");
		retVal.append("=========================================================\n");
		if (complexTypes != null) {
			for (ComplexType cType : complexTypes) {
				retVal.append("=========================================================\n");
				retVal.append(cType.toString());
				retVal.append("=========================================================\n");
			}
		}
		retVal.append("Simple Types\n");
		retVal.append("=========================================================\n");
		if (simpleTypes != null) {

			for (SimpleType cType : simpleTypes) {
				retVal.append("=========================================================\n");
				retVal.append(cType.toString());
				retVal.append("=========================================================\n");
			}
		}

		retVal.append("Independent Elements\n");
		if (elements != null) {
			for (Element cType : elements) {
				if (cType.getContainerComplexType() == null) {
					retVal.append("=========================================================\n");
					retVal.append(cType.toString());
					retVal.append("=========================================================\n");
				}
			}
		}
		return retVal.toString();
	}
}
