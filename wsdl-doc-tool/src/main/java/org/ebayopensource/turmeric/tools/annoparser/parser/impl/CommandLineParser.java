/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.parser.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.commons.Constants;
import org.ebayopensource.turmeric.tools.annoparser.exception.CommandLineParserException;


/**
 * The Class CommandLineParser.
 *
 * @author sdaripelli
 */
public class CommandLineParser {
	
	/**
	 * Creates the arg map.
	 * 
	 * @param args
	 *            the args
	 * @return the map
	 * @throws CommandLineParserException
	 *             the command line parser exception
	 */
	public static Map<String,String> createArgMap(String[] args) throws CommandLineParserException{
		
		Logger.getLogger(CommandLineParser.class.getName()).entering("CommandLineParser", "createArgMap",args);
		Map<String,String> retMap=null;
		if(args!=null){
			retMap=new HashMap<String, String>();
			for(String arg:args){
				if(Constants.COMMAND_LINE_HELP_ARG.equalsIgnoreCase(arg)){
					retMap.put(Constants.COMMAND_LINE_HELP_ARG,null);
				}else{
				String[] values=arg.split("=");
				if(values==null || values.length!=2){
					Logger.getLogger(CommandLineParser.class.getName()).severe("Command Line Arguments should be in name=value pairs");
					throw new CommandLineParserException("Command Line Arguments should be in name=value pairs");
				}
				
				retMap.put(values[0], values[1]);
				}
			}
		}
		Logger.getLogger(CommandLineParser.class.getName()).exiting("CommandLineParser", "createArgMap",retMap);
		return retMap;
	}
}
