/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.exception.OutputFormatterException;
import org.ebayopensource.turmeric.tools.annoparser.parser.impl.WsdlParserImpl;
import org.w3c.dom.Node;

/**
 * The Class Utils.
 *
 * @author goraman
 */
public class Utils {

	
	private final static String CLASS_NAME = WsdlParserImpl.class.getClass().getName();  
	
	/** The logger. */
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
		logger.finest("getUsageHiearchyForField: " + fieldName);
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
	
	/**
	 * Gets the previous comment.
	 *
	 * @param element the element
	 * @return the previous comment
	 */
	public static String getPreviousComment(Node element) {
		while (element.getPreviousSibling() != null) {
			Node prev = element.getPreviousSibling();
			if (prev.getNodeType() == Node.COMMENT_NODE) {
				return prev.getTextContent();
			} else if (prev.getNodeType() == Node.TEXT_NODE) {
				return getPreviousComment(prev);
			} else if (prev.getNodeType() == Node.ELEMENT_NODE) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Gets the next comment.
	 *
	 * @param element the element
	 * @return the next comment
	 */
	public static String getNextComment(Node element) {
		while (element.getNextSibling() != null) {
			Node prev = element.getNextSibling();
			if (prev.getNodeType() == Node.COMMENT_NODE) {
				return prev.getTextContent();
			} else if (prev.getNodeType() == Node.TEXT_NODE) {
				return getNextComment(prev);
			} else if (prev.getNodeType() == Node.ELEMENT_NODE) {
				return null;
			}
		}
		return null;
	}
	/**
	 * Gets the file as string.
	 * 
	 * @param path
	 *            the path
	 * @return the file as string
	 * @throws OutputFormatterException
	 *             the output formatter exception
	 */
	public static StringBuffer getFileAsString(String path)
			throws OutputFormatterException {
		File file = new File(path);
		StringBuffer sb = new StringBuffer("");
		if (file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				String line = reader.readLine();
				while (line != null) {
					sb.append(line + System.getProperty("line.separator"));
					line = reader.readLine();
				}

			} catch (FileNotFoundException e) {
				throw new OutputFormatterException(e);
			} catch (IOException e) {
				throw new OutputFormatterException(e);
			}

		}
		return sb;

	}

	/**
	 * Gets the file as string.
	 *
	 * @param iStream the i stream
	 * @return the file as string
	 * @throws OutputFormatterException the output formatter exception
	 */
	public static StringBuffer getFileAsString(InputStream iStream)
			throws OutputFormatterException {

		StringBuffer sb = new StringBuffer("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					iStream));

			String line = reader.readLine();
			while (line != null) {
				sb.append(line + System.getProperty("line.separator"));
				line = reader.readLine();
			}

		} catch (IOException e) {
			throw new OutputFormatterException(e);
		}

		return sb;

	}

	/**
	 * Convert to url. The path supplied is first tried as a URL external form
	 * string, if it fails it is tried as a Class path resource, Then it is
	 * tried as a local file path.
	 *
	 * @param path the path
	 * @return the uRL
	 * @throws OutputFormatterException the output formatter exception
	 */
	public static URL convertToURL(String path) throws OutputFormatterException {
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			url = Thread.currentThread().getContextClassLoader().getResource(
					path);
			if (url == null) {
				File file = new File(path);
				if (file.exists()) {
					try {
						url = file.toURI().toURL();
					} catch (MalformedURLException e1) {
						Logger.getLogger(Utils.class.getName()).log(
								Level.SEVERE, path + " is Not valid", e);
						throw new OutputFormatterException(e1.getCause());
					}
				}
			}
		}
		return url;
	}
}
