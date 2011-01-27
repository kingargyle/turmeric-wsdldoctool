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

import org.ebayopensource.turmeric.tools.annoparser.driver.Driver;
import org.junit.Test;

/**
 * The Class DriverTest.
 *
 * @author sdaripelli
 */
public class DriverTest  extends TestCase{

	/**
	 * Test driver.
	 */
	@Test
	public void testDriver() {
		
			Driver driver=new Driver("C:\\annoParser\\Documents",null,"sampleWsdl/AddressVerificationService(1.0.0).wsdl",null);
			driver.process();
		
	}
	
//	@Test
//	public void testDriverWithConfig() {
//		
//		Driver driver=new Driver(null,getFilepathFromTestConfigFolder("ConfigurationWithAllProps"),(String)null,null);
//		driver.process();
//	}
	
//	@Test
//	public void testDriverAll() {
//		URL url=this.getClass().getClassLoader().getResource("sampleWsdl/");
//		File file=new File(url.getPath());
//		File[] files=file.listFiles();
//		List<String> documents=new ArrayList<String>();
//		for(File fileName:files){
//			documents.add(fileName.getAbsolutePath());
//		}
//		Driver driver=new Driver("C:\\annoParser\\Documents",null,documents,null);
//		driver.process();
//	}
//	
	/**
 * Test driver with config and sys env.
 */
@Test
	public void testDriverWithConfigAndSysEnv() {
		String annoParserConfig=System.getenv().get("ANNOPARSER_CONFIG");
		if(annoParserConfig==null){
			return;
		}
		Driver driver=new Driver(null,null,(String)null,null);
		driver.process();
	}

	
}
