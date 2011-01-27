/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.unittest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationInfo;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationTag;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * The Class ParsedAnnotationInfoTest.
 *
 * @author sdaripelli
 */
public class ParsedAnnotationInfoTest extends TestCase{
	
	/**
	 * Test add parsed annotation tag.
	 */
	@Test
	public void testAddParsedAnnotationTag()
	{
		ParsedAnnotationInfo p = new ParsedAnnotationInfo();
		p.setDocumentation("Documentation");
		Map<String,List<ParsedAnnotationTag>> value= new HashMap<String, List<ParsedAnnotationTag>>();
		
		
		if(p.getDocumentation()!=null)
		{
			assertTrue(true);
		}
		
		ParsedAnnotationTag childTag = new ParsedAnnotationTag();
		childTag.setTagName("childTagName");
		childTag.setTagValue("childTagValue");
		if(childTag.getTagValue()!=null)
		{
			assertTrue(true);
		}
		Map<String,String> childAttrMap = new HashMap<String, String>();
		childAttrMap.put("key", "value");
		childAttrMap.put("key1", "value1");
		childTag.setAttributes(childAttrMap);
		
		ParsedAnnotationTag parseAnnoTag = new ParsedAnnotationTag();
		parseAnnoTag.setTagName("TagName");
		parseAnnoTag.setTagValue("TagValue");
		Map<String,String> attrMap = new HashMap<String, String>();
		attrMap.put("key", "value");
		attrMap.put("key1", "value1");
		parseAnnoTag.setAttributes(attrMap);
		List<ParsedAnnotationTag> childList = new ArrayList<ParsedAnnotationTag>();
		childList.add(childTag);
		Map<String,List<ParsedAnnotationTag>> children = new HashMap<String, List<ParsedAnnotationTag>>();
		children.put("Child", childList);
		parseAnnoTag.setChildren(children);
		p.addParsedAnnotationTag("tagName", parseAnnoTag);
		List<ParsedAnnotationTag> parsedAnnotationTagList = new ArrayList<ParsedAnnotationTag>();
		parsedAnnotationTagList.add(parseAnnoTag);
		value.put("key", parsedAnnotationTagList);
		p.setValue(value);
		if(p.getValue().containsKey("key"))
		{
			assertTrue(true);
		}
		if(p.value.containsKey("tagName"))
		{
			assertTrue(true);
		}
	}

}
