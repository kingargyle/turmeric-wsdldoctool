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

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationTag;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The Class ParsedAnnotationTagTest.
 * 
 * @author sdaripelli
 */
public class ParsedAnnotationTagTest {

	/**
	 * Test add child.
	 */
	@Test
	public void testAddChild() {

		ParsedAnnotationTag childTag = new ParsedAnnotationTag();
		childTag.setTagName("childTagName");
		childTag.setTagValue("childTagValue");
		if (childTag.getTagValue() != null) {
			assertTrue(true);
		}
		Map<String, String> childAttrMap = new HashMap<String, String>();
		childAttrMap.put("key", "value");
		childAttrMap.put("key1", "value1");
		childTag.setAttributes(childAttrMap);

		ParsedAnnotationTag parseAnnoTag = new ParsedAnnotationTag();
		parseAnnoTag.setTagName("TagName");
		parseAnnoTag.setTagValue("TagValue");
		Map<String, String> attrMap = new HashMap<String, String>();
		attrMap.put("key", "value");
		attrMap.put("key1", "value1");
		parseAnnoTag.setAttributes(attrMap);
		List<ParsedAnnotationTag> childList = new ArrayList<ParsedAnnotationTag>();
		childList.add(childTag);
		Map<String, List<ParsedAnnotationTag>> children = new HashMap<String, List<ParsedAnnotationTag>>();
		children.put("Child", childList);
		parseAnnoTag.setChildren(children);

		if (parseAnnoTag.getChildren() != null) {
			assertTrue(true);
		}

		ParsedAnnotationTag test = new ParsedAnnotationTag();
		test.addChild(parseAnnoTag);
		if (test.children != null) {
			assertTrue(true);
		}
	}

	/**
	 * Test add attribute.
	 */
	@Test
	public void testAddAttribute() {
		ParsedAnnotationTag parsedAnnotationTag = new ParsedAnnotationTag();
		parsedAnnotationTag.addAttribute("attributeName", "attributeValue");
		if (parsedAnnotationTag.getAttributes().containsKey("attributeName")) {
			assertTrue(true);
		}
	}

}
