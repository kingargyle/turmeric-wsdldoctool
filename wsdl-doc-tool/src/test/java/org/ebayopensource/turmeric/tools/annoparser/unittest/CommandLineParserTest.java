/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.unittest;

import java.util.Map;

import junit.framework.TestCase;

import org.ebayopensource.turmeric.tools.annoparser.exception.CommandLineParserException;
import org.ebayopensource.turmeric.tools.annoparser.parser.impl.CommandLineParser;
import org.junit.Test;

/**
 * The Class CommandLineParserTest.
 * 
 * @author sdaripelli
 */
public class CommandLineParserTest extends TestCase {

	/**
	 * Test create arg map with all params.
	 */
	@Test
	public void testCreateArgMapWithAllParams() {
		String args[] = { "documents=test.wsdl",
				"output_dir=c:/Test/Documents", "config=abc.xml", "css=abc.css" };
		try {
			Map<String, String> argMap = CommandLineParser.createArgMap(args);
			if (!argMap.containsKey("documents")) {
				fail("Documents not found");
			}
			if (!argMap.containsKey("output_dir")) {
				fail("Output Dir not found");
			}
			if (!argMap.containsKey("config")) {
				fail("CSS not found");
			}
			if (!argMap.containsKey("css")) {
				fail("Config not found");
			}
		} catch (CommandLineParserException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test create arg map one params.
	 */
	@Test
	public void testCreateArgMapOneParams() {
		String args[] = { "documents=test.wsdl" };
		try {
			Map<String, String> argMap = CommandLineParser.createArgMap(args);
			if (!argMap.containsKey("documents")) {
				fail("Documents not found");
			}
			if (argMap.containsKey("output_dir")) {
				fail("Output Dir not found");
			}
			if (argMap.containsKey("config")) {
				fail("CSS not found");
			}
			if (argMap.containsKey("css")) {
				fail("Config not found");
			}
		} catch (CommandLineParserException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Test create arg map wrong params.
	 */
	@Test
	public void testCreateArgMapWrongParams() {
		String args[] = { "documents" };
		try {
			Map<String, String> argMap = CommandLineParser.createArgMap(args);
		} catch (CommandLineParserException e) {
			return;

		}
		fail("Not rightly parsed");

	}

}
