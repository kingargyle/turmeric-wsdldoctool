/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.exception;

/**
 * The Class WsdlDocException.
 * 
 * @author srengarajan
 */
public class WsdlDocException extends XsdDocException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new wsdl doc exception.
	 * 
	 * @param t
	 *            the t
	 */
	public WsdlDocException(Throwable t) {
		super(t);
	}

	/**
	 * Instantiates a new wsdl doc exception.
	 * 
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public WsdlDocException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Instantiates a new wsdl doc exception.
	 * 
	 * @param message
	 *            the message
	 */
	public WsdlDocException(String message) {
		super(message);
	}

}
