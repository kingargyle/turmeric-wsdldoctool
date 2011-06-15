/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/

package org.ebayopensource.turmeric.tools.annoparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.commons.Constants;
import org.ebayopensource.turmeric.tools.annoparser.driver.Driver;
import org.ebayopensource.turmeric.tools.annoparser.exception.CommandLineParserException;
import org.ebayopensource.turmeric.tools.annoparser.exception.ConfigurationException;
import org.ebayopensource.turmeric.tools.annoparser.exception.WsdlDocException;
import org.ebayopensource.turmeric.tools.annoparser.parser.impl.CommandLineParser;
import org.ebayopensource.turmeric.tools.annoparser.utils.Utils;

/**
 * This the is Main class which should be invoked from command line for
 * Annotation parsing and Documentation Generation. The Parameters can be passed
 * in 3 ways, 1. Through System Environment variables 2. Through Configuration
 * xml, whose path can passed as Command Line argument or System Envronment
 * variable 3. Through Command line argument.
 * 
 * The Names of system variables are, ANNOPARSER_OUTPUT_DIR = This System
 * Environment variable should specify the Output Directory. ANNOPARSER_CONFIG=
 * This System Environment variable should specify the Config file location
 * ANNOPARSER_CSS=This System Environment variable should specify the CSS file
 * path which has to be used for Documents
 * 
 * The Command line Arguments should be passed as follows, output_dir=<Output
 * Directory Path> config=<Configuration file Location> css=<CSS file path>
 * 
 * The precedence of input methodology is taken in low to high precedence of
 * below order. 1. System Environment variables 2. Configuration xml 3. Command
 * line arguments
 * 
 * We can invoke with argument to get the complete help text.
 * 
 * @author sdaripelli
 */
public class Main {

	/** The help content. */
	private static String helpContent = getHelpText();

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws WsdlDocException
	 *             the wsdl doc exception
	 */
	public static void main(String[] args) throws WsdlDocException {
		// args java Main output_dir=abc documents=abc.wsdl;xyz.xsd
		// config=abc.xml css=abc.css

		Map<String, String> argMap = CommandLineParser.createArgMap(args);
		if (argMap.containsKey(Constants.COMMAND_LINE_HELP_ARG)) {
			System.out.println(helpContent);
			return;
		}
		List<String> documents = null;
		if (argMap.containsKey(Constants.INPUT_DOCUMENT)) {
			String documentValues = argMap.get("documents");
			String[] documentval = documentValues.split(";");
			if (documentval == null || documentval.length == 0) {
				throw new CommandLineParserException(
						"; saperated documents should be supplied if documents argument is specified");
			}
			documents = removeEmptyValues(documentval);
		}
		Driver driver = new Driver(argMap.get(Constants.INPUT_OUTPUT_DIR),
				argMap.get(Constants.INPUT_CONFIG), documents,
				argMap.get(Constants.INPUT_CSS));
		try {
			driver.process();
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the help text.
	 * 
	 * @return text displayed when help option is supplied.
	 */
	private static String getHelpText() {
		String help = null;
		InputStream is = Main.class.getClassLoader().getResourceAsStream(
				"Help.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}

			help = sb.toString();
		} catch (IOException e) {
			Logger.getLogger(Main.class.getName()).throwing(
					Main.class.getName(), "main", e);
		}
		return help;
	}

	/**
	 * Removes any empty values supplied as documents.
	 * 
	 * @param documentval
	 *            the documentval
	 * @return the list
	 */
	private static List<String> removeEmptyValues(String[] documentval) {
		List<String> retList = new ArrayList<String>();
		for (String value : documentval) {
			if (value != null && !Utils.isEmpty(value)) {
				retList.add(value);
			}
		}
		return retList;
	}

	/**
	 * Gets the help content.
	 * 
	 * @return the help content
	 */
	public static String getHelpContent() {
		return helpContent;
	}

}
