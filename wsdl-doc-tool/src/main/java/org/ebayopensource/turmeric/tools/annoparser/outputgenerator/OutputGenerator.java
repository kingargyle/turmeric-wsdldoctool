/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.outputgenerator;

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam;
import org.ebayopensource.turmeric.tools.annoparser.exception.WsdlDocException;
import org.ebayopensource.turmeric.tools.annoparser.exception.XsdDocException;

/**
 * The Interface OutputGenerator.
 *
 * @author srengarajan
 */
public interface OutputGenerator {

	/**
	 * Generate wsdl output.
	 *
	 * @param wsdlDoc the wsdl doc
	 * @param outputGenaratorParam the output genarator param
	 * @throws WsdlDocException the wsdl doc exception
	 */
	public void generateWsdlOutput(WSDLDocInterface wsdlDoc,OutputGenaratorParam outputGenaratorParam) throws WsdlDocException;
	
	/**
	 * Generate xsd output.
	 *
	 * @param xsdDoc the xsd doc
	 * @param outputGenaratorParam the output genarator param
	 * @throws WsdlDocException the wsdl doc exception
	 */
	public void generateXsdOutput(XSDDocInterface xsdDoc,OutputGenaratorParam outputGenaratorParam) throws XsdDocException;
	
	/**
	 * Complete processing.
	 *
	 * @throws WsdlDocException the wsdl doc exception
	 */
	public void completeProcessing() throws XsdDocException;
}
