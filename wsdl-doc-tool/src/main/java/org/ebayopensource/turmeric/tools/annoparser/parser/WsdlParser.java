/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.parser;

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.exception.ParserException;

/**
 * interface to wsdl parser parses the input wsdl document and provides a very
 * light weight POJO interface to the document. Note that this is not a heavy
 * weight DOM of the document, but an interface which captures the minimal
 * structure enough to represent the associations with specific emphasis to
 * documentation and appinfo It can however be extended easily to add more
 * specifics
 * 
 * @author srengarajan
 */
public interface WsdlParser {

	/**
	 * Parses the.
	 * 
	 * @param url
	 *            of the input wsdl document
	 * @return POJO interface to the wsdl
	 * @throws ParserException
	 *             the parser exception
	 */
	public WSDLDocInterface parse(String url) throws ParserException;
}
