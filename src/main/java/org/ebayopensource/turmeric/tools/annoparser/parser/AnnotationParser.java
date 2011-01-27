/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.parser;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationTag;
import org.w3c.dom.Element;

/**
 * The Interface AnnotationParser is to be implemented to supply the Annotation Parser for supporting the Custom Annotations.
 *
 * @author srengarajan
 */
public interface AnnotationParser {
	
	/**
	 * Parses the annotation.
	 * 
	 * @param elem
	 *            the elem
	 * @return the parsed annotation tag
	 */
	ParsedAnnotationTag parseAnnotation(Element elem);
}
