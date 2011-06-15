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
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ComplexType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.SimpleType;

/**
 * The Interface XSDDocInterface.
 *
 * @author srengarajan
 */
public interface XSDDocInterface {
	
	/**
	 * Gets the all independent elements.
	 *
	 * @return the all independent elements
	 */
	public List<Element> getAllIndependentElements();
	
	/**
	 * Gets the all enums.
	 *
	 * @return the all enums
	 */
	public List<EnumElement> getAllEnums();
	
	/**
	 * Gets the all simple types.
	 *
	 * @return the all simple types
	 */
	public List<SimpleType> getAllSimpleTypes();
	
	/**
	 * Gets the all complex types.
	 *
	 * @return the all complex types
	 */
	public List<ComplexType> getAllComplexTypes();
	
	/**
	 * Gets the element complex type map.
	 *
	 * @return the element complex type map
	 */
	public Map<String,List<ComplexType>> getElementComplexTypeMap();
	
	/**
	 * Search c type.
	 *
	 * @param name the name
	 * @return the complex type
	 */
	public ComplexType searchCType(String name);
	
	/**
	 * Search simple type.
	 *
	 * @param name the name
	 * @return the simple type
	 */
	public SimpleType searchSimpleType(String name);
	
	/**
	 * Search independent element.
	 *
	 * @param elementName the element name
	 * @return the element
	 */
	public Element searchIndependentElement(String elementName);
	
	/**
	 * Gets the document url.
	 *
	 * @return the document url
	 */
	public URL getDocumentURL();
	
	
	
	/**
	 * Gets the parent to complex type map.
	 *
	 * @return the parent to complex type map
	 */
	public Map<String, Set<String>> getParentToComplexTypeMap();
	
	
	
}
