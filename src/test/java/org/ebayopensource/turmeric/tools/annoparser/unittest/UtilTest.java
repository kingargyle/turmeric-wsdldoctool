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

import org.ebayopensource.turmeric.tools.annoparser.utils.Utils;
import org.junit.Test;

/**
 * The Class UtilTest.
 *
 * @author sdaripelli
 */
public class UtilTest extends TestCase 
{
	
	/**
	 * Test get empty for null.
	 */
	@Test
	public void testGetEmptyForNull() 
	{
		assertEquals(Utils.getEmptyForNull(null), "");
	}
	
	/**
	 * Test remove name space.
	 */
	@Test
	public void testRemoveNameSpace()
	{
		String tag="xsd:Documentation";
		if(Utils.removeNameSpace(tag).indexOf(":")==-1)
		{
		   assertTrue(true);	
		}
	}

}
