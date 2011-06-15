/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.unittest;

import junit.framework.TestCase;

import org.ebayopensource.turmeric.tools.annoparser.config.ConfigurationReader;
import org.ebayopensource.turmeric.tools.annoparser.context.Context;
import org.ebayopensource.turmeric.tools.annoparser.exception.ConfigurationException;
import org.junit.Test;

/**
 * The Class ConfigurationReaderAllConfigTest.
 * 
 * @author sdaripelli
 */
public class ConfigurationReaderAllConfigTest extends TestCase {

	/**
	 * Test load configurations all.
	 */
	@Test
	public void testLoadConfigurationsAll() {
		try {
			ConfigurationReader
					.loadConfigurations("ConfigurationWithAllProps.xml");
			if (Context.getContext().getOutputGenerators() == null) {
				fail("O/p gen  Not Loaded");
			}
			if (Context.getContext().getAnnotationParsers().get("TestAnno") == null) {
				fail("Anno Parsers Not Loaded");
			}
			if (!"test_case.css".equals(Context.getContext().getCssFilePath())) {
				fail("Css File Not Loaded");
			}
			if (Context.getContext().getOutputDir() == null) {
				fail("O/p Not Loaded");
			}

		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
	}

}
