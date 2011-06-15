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
 * The Class CommandLineParserException. This exception is thrown when command
 * line arguments are not passed correctly.
 * 
 * @author sdaripelli
 */
public class CommandLineParserException extends WsdlDocException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new command line parser exception.
	 * 
	 * @param t
	 *            the throwable
	 */
	public CommandLineParserException(Throwable t) {
		super(t);
	}

	/**
	 * Instantiates a new command line parser exception.
	 * 
	 * @param message
	 *            the message
	 */
	public CommandLineParserException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new command line parser exception.
	 * 
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public CommandLineParserException(String message, Throwable t) {
		super(message, t);
	}
}
