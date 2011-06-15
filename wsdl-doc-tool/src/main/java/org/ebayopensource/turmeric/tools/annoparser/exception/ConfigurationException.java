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
 * The Class ConfigurationException.
 * This exception is thrown when the Configuration file supplied is not valid.
 *
 * @author sdaripelli
 */
public class ConfigurationException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new configuration exception.
	 * 
	 * @param t
	 *            the t
	 */
	public ConfigurationException(Throwable t) {
		super(t);
	}

	/**
	 * Instantiates a new configuration exception.
	 * 
	 * @param message
	 *            the message
	 * @param t
	 *            the t
	 */
	public ConfigurationException(String message, Throwable t) {

		super(message, t);
	}

	/**
	 * Instantiates a new configuration exception.
	 * 
	 * @param message
	 *            the message
	 */
	public ConfigurationException(String message) {

		super(message);
	}
}
