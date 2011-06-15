/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.parser;

import org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.exception.ParserException;

/**
 * xsd parser interface which parses an schema document and provides a very
 * light weight POJO version of the same which captures the types and their
 * relationships with specific importance to documentation and annotation
 * elements. Note that the POJO interface returned is not a heavy weight replica
 * of the DOM of the input document.
 * 
 * TBD: Standardize interface names
 * 
 * @author srengarajan
 */
public interface XSDParser {

	/**
	 * Parses the.
	 * 
	 * @param url
	 *            representation of the input document
	 * @return light weight POJO interface to the document
	 * @throws ParserException
	 *             the parser exception
	 */
	public XSDDocInterface parse(String url) throws ParserException;
}
