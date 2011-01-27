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
 * The Class ConfigurationReaderTest.
 *
 * @author sdaripelli
 */
public class ConfigurationReaderTest  extends TestCase{

	/**
	 * Test load configurations only css.
	 */
	@Test
	public void testLoadConfigurationsOnlyCss() {
		try {
			ConfigurationReader.loadConfigurations(getFilepathFromTestConfigFolder("ConfigurationCSS"));
			if(!"test_case.css".equals(Context.getContext().getCssFilePath())){
				fail("Css File Not Loaded");
			}
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test load configurations only anno.
	 */
	@Test
	public void testLoadConfigurationsOnlyAnno() {
		try {
			ConfigurationReader.loadConfigurations(getFilepathFromTestConfigFolder("ConfigurationOnlyAnnoParsers"));
			if(Context.getContext().getAnnotationParsers().get("TestAnno")==null){
				fail("Anno Parsers Not Loaded");
			}
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Test load configurations only op.
	 */
	@Test
	public void testLoadConfigurationsOnlyOp() {
		try {
			ConfigurationReader.loadConfigurations(getFilepathFromTestConfigFolder("ConfigurationOnlyOpGen"));
			if(Context.getContext().getOutputGenerators()==null){
				fail("O/p gen  Not Loaded");
			}
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
	}
	

	/**
	 * Test load default configuration.
	 */
	@Test
	public void testLoadDefaultConfiguration() {
		try {
			ConfigurationReader.loadDefaultConfiguration();
		} catch (ConfigurationException e) {
			fail(e.getMessage());
		}
		
	}
   
   /**
    * Gets the filepath from test config folder.
    *
    * @param filename the filename
    * @return the filepath from test config folder
    */
   private String getFilepathFromTestConfigFolder(String filename){
	   return filename+".xml";
   }
}
