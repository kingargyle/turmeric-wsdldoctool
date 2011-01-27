/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.parser.impl.WsdlParserImpl;

/**
 * The Class Utils.
 *
 * @author goraman
 */
public class Utils {

	
	private final static String CLASS_NAME = WsdlParserImpl.class.getClass().getName();  
	static Logger logger  = Logger.getLogger(CLASS_NAME);
	
	/**
	 * Gets the empty for null.
	 *
	 * @param str the str
	 * @return the empty for null
	 */
	public static String getEmptyForNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}
	
	/**
	 * Removes the name space.
	 *
	 * @param str the str
	 * @return the string
	 */
	public static String removeNameSpace(String str) {
		if (str.indexOf(':') != -1) {
			str = str.substring(str.indexOf(':') + 1);
		}
		return str;
	}
	
	/**
	 * Checks if is empty.
	 *
	 * @param string the string
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String string){
		if(string==null || "".equals(string.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the usage hierarchy for field.
	 *
	 * @param allPossiblePaths the all possible paths
	 * @param fieldName the field name
	 * @return the possible usage paths to a field.
	 */
	public static List<String> getUsageHierarchyForField(List<String> allPossiblePaths, String fieldName) {
		logger.info("getUsageHiearchyForField: " + fieldName);
		logger.entering("Utils", "getUsageHiearchyForField", fieldName);
		ArrayList<String> paths = new ArrayList<String>();

		for (String path : allPossiblePaths) {
			//System.out.println("path not filtered" + path);
			if (path.endsWith(fieldName)) {
				paths.add(path.substring(0, path.lastIndexOf(".")));
			} else if ( path.contains("." + fieldName + ".") ) {
				paths.add(path.substring(0,path.indexOf("." + fieldName + ".")));
			}
		}
		logger.exiting("Utils", "getUsageHiearchyForField", paths);
		return paths;
	}
	
	/**
	 * Comma sep line to list.
	 *
	 * @param commSepLine the comm sep line
	 * @return the list
	 */
	public static List<String> commaSepLineToList(String commSepLine) {
		List<String> retList = null;
		if (commSepLine != null) {
			String[] calls = commSepLine.split(",");
			if(calls!=null){
				retList=new ArrayList<String>();
				for (String call : calls) {
					retList.add(call.trim());
				}
			}
			
		}
		return retList;
	}
	
	
}
