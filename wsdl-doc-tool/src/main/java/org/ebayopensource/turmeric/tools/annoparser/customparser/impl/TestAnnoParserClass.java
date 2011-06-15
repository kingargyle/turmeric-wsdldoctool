/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.customparser.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationTag;
import org.ebayopensource.turmeric.tools.annoparser.parser.AnnotationParser;
import org.w3c.dom.Element;

/**
 * The Class TestAnnoParserClass is just a test implementation to check if
 * Custom Class is getting Instantiated.
 * 
 * @author sdaripelli
 */
public class TestAnnoParserClass implements AnnotationParser {

	/**
	 * {@inheritDoc}
	 */
	public ParsedAnnotationTag parseAnnotation(Element elem) {
		System.out.println(" IN Parse Annotation");
		return new ParsedAnnotationTag();
	}

}
