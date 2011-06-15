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
 * The Class AnnotationParserException. This exception is raised when the
 * annotation xml is not valid.
 * 
 * @author sdaripelli
 */
public class AnnotationParserException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new annotation parser exception.
	 * 
	 * @param t
	 *            the t
	 */
	public AnnotationParserException(Throwable t) {
		super(t);
	}

	/**
	 * Instantiates a new annotation parser exception.
	 * 
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public AnnotationParserException(String message, Throwable t) {

		super(message, t);
	}
}
