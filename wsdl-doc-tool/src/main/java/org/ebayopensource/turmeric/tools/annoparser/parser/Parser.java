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
 * The Interface Parser. redundant with IXSDParser. To be removed.
 * @author srengarajan
 */
public interface Parser {
	
	/**
	 * Parses the XSD.
	 *
	 * @param url the url
	 * @return the xSD doc interface
	 * @throws ParserException the parser exception
	 */
	public XSDDocInterface parse(String url) throws ParserException;
}
