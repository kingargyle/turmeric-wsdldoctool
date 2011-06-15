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
 * The Class ParsedAnnotationInfo holds the information retrieved after parsing the Annotations.
 *
 * @author sdaripelli
 */
public class ParsedAnnotationInfo {

	/** The value hold a Map where key the the tagname of the annotation and value is the data collected after parsing the annotation. */
	public Map<String,List<ParsedAnnotationTag>> value;

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Map<String, List<ParsedAnnotationTag>> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value
	 */
	public void setValue(Map<String, List<ParsedAnnotationTag>> value) {
		this.value = value;
	}

	/** The documentation. */
	public String documentation;

	/**
	 * Gets the documentation.
	 * 
	 * @return the documentation
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * Sets the documentation.
	 * 
	 * @param documentation
	 *            the new documentation
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	
	/**
	 * Adds the parsed annotation tag.
	 * 
	 * @param tagName
	 *            the tag name
	 * @param parsedTagValue
	 *            the parsed tag value
	 */
	public void addParsedAnnotationTag(String tagName,ParsedAnnotationTag parsedTagValue){
		if(value==null){
			value=new HashMap<String, List<ParsedAnnotationTag>>();
		}
		if(value.containsKey(tagName)){
			value.get(tagName).add(parsedTagValue);
		}else{
			List<ParsedAnnotationTag> values=new ArrayList<ParsedAnnotationTag>();
			values.add(parsedTagValue);
			value.put(tagName, values);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer retBuf=new StringBuffer();
		retBuf.append("Documentation: \n"+ documentation +"\n");
		if(value==null){
			retBuf.append("No Annotations Found\n");
		}else{
			if (value != null) {
				for (Map.Entry<String, List<ParsedAnnotationTag>> entry : value
						.entrySet()) {
					for(ParsedAnnotationTag tag:entry.getValue()){
						retBuf.append(tag.toString()+"\n");
					}
				}
			}
			
		}
		return retBuf.toString();
	}

}