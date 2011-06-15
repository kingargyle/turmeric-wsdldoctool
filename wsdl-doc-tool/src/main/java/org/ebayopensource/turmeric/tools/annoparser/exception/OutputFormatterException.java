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
 * The Class OutputFormatterException.
 * 
 * @author srengarajan
 */
public class OutputFormatterException extends WsdlDocException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new output formatter exception.
	 * 
	 * @param t
	 *            the t
	 */
	public OutputFormatterException(Throwable t) {
		super(t);
	}
}
