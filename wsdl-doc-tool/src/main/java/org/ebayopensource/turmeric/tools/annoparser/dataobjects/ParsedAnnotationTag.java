/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class ParsedAnnotationTag holds the data collected from each annotation
 * tag.
 * 
 * @author sdaripelli
 */
public class ParsedAnnotationTag {

	/** The tag name. */
	public String tagName;

	/** The children. */
	public Map<String, List<ParsedAnnotationTag>> children;

	/** The tag value. */
	public String tagValue;

	/** The my parsed annotation info. */
	public ParsedAnnotationInfo myParsedAnnotationInfo;

	/** The attributes. */
	private Map<String, String> attributes;

	/**
	 * Gets the tag name.
	 * 
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * Sets the tag name.
	 * 
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public Map<String, List<ParsedAnnotationTag>> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 * 
	 * @param children
	 *            the children to set
	 */
	public void setChildren(Map<String, List<ParsedAnnotationTag>> children) {
		this.children = children;
	}

	/**
	 * Gets the tag value.
	 * 
	 * @return the tagValue
	 */
	public String getTagValue() {
		return tagValue;
	}

	/**
	 * Sets the tag value.
	 * 
	 * @param tagValue
	 *            the tagValue to set
	 */
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * Gets the my parsed annotation info.
	 * 
	 * @return the myParsedAnnotationInfo
	 */
	public ParsedAnnotationInfo getMyParsedAnnotationInfo() {
		return myParsedAnnotationInfo;
	}

	/**
	 * Sets the my parsed annotation info.
	 * 
	 * @param myParsedAnnotationInfo
	 *            the myParsedAnnotationInfo to set
	 */
	public void setMyParsedAnnotationInfo(
			ParsedAnnotationInfo myParsedAnnotationInfo) {
		this.myParsedAnnotationInfo = myParsedAnnotationInfo;
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 * 
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Adds Parsed child data element.
	 * 
	 * @param parsedTagValue
	 *            the parsed tag value
	 */
	public void addChild(final ParsedAnnotationTag parsedTagValue) {
		if (this.children == null) {
			this.children = new HashMap<String, List<ParsedAnnotationTag>>();
		}
		if (children.containsKey(parsedTagValue.getTagName())) {
			children.get(parsedTagValue.getTagName()).add(parsedTagValue);
		} else {
			List<ParsedAnnotationTag> values = new ArrayList<ParsedAnnotationTag>();
			values.add(parsedTagValue);
			children.put(parsedTagValue.getTagName(), values);
		}

	}

	/**
	 * Adds Parsed Attribute.
	 * 
	 * @param attributeName
	 *            the attribute name
	 * @param attributeValue
	 *            the attribute value
	 */
	public void addAttribute(String attributeName, String attributeValue) {
		if (this.attributes == null) {
			this.attributes = new HashMap<String, String>();
		}
		this.attributes.put(attributeName, attributeValue);
	}

	@Override
	public String toString() {
		StringBuffer retVal = new StringBuffer();
		retVal.append("Annotation: " + tagName + "\n");
		retVal.append("Value(if any): " + tagValue + "\n");
		if(attributes!=null){
		retVal.append("Annotation Tag Attributes\n");
			if (attributes != null) {
				for (Map.Entry<String, String> entry : attributes.entrySet()) {
					retVal.append(entry.getKey() + "=" + entry.getValue() +"\n");
				}
			}
		}
		if(children!=null && !children.isEmpty())
		retVal.append("Child Annotations of "+tagName+"\n");
		retVal.append("=========================================================\n");
		if (children != null) {
			for (Map.Entry<String, List<ParsedAnnotationTag>> entry : children
					.entrySet()) {
				for(ParsedAnnotationTag tag:entry.getValue()){
					retVal.append(tag.toString());
				}
			}
		}
		retVal.append("=========================================================\n");
		return retVal.toString();
	}

}