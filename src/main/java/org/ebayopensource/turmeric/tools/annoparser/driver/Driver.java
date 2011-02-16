/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.driver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.commons.Constants;
import org.ebayopensource.turmeric.tools.annoparser.config.ConfigurationReader;
import org.ebayopensource.turmeric.tools.annoparser.context.Context;
import org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam;
import org.ebayopensource.turmeric.tools.annoparser.exception.ConfigurationException;
import org.ebayopensource.turmeric.tools.annoparser.exception.ParserException;
import org.ebayopensource.turmeric.tools.annoparser.exception.XsdDocException;
import org.ebayopensource.turmeric.tools.annoparser.parser.WsdlParser;
import org.ebayopensource.turmeric.tools.annoparser.parser.XSDParser;
import org.ebayopensource.turmeric.tools.annoparser.utils.Utils;

/**
 * The Class Driver is the main class which integrates and calls all the different modules involved.
 * This class should be called by the external tools which are planning to integrate the Annotation Parser tool.
 *
 * @author sdaripelli
 */
public class Driver {

	private final static String CLASS_NAME=Driver.class.getClass().getName();  
	Logger logger  = Logger.getLogger(CLASS_NAME);
	
	/** The output dir. */
	private String outputDir;

	/** The config xml path. */
	private String configXmlPath;

	/** The documents. */
	private List<String> documents;

	/** The css file path. */
	private String cssFilePath;

	/**
	 * Instantiates a new driver.
	 * 
	 * @param outputDir
	 *            the output dir
	 * @param configXmlPath
	 *            the config xml path
	 * @param documents
	 *            the documents
	 * @param cssFilePath
	 *            the css file path
	 */
	public Driver(String outputDir, String configXmlPath,
			List<String> documents, String cssFilePath) {
		this.outputDir = outputDir;
		this.configXmlPath = configXmlPath;
		this.documents = documents;
		this.cssFilePath = cssFilePath;
	}
	
	/**
	 * Instantiates a new driver.
	 * 
	 * @param outputDir
	 *            the output dir
	 * @param configXmlPath
	 *            the config xml path
	 * @param document
	 *            the document
	 * @param cssFilePath
	 *            the css file path
	 */
	public Driver(String outputDir, String configXmlPath,
			String document, String cssFilePath) {
		this.outputDir = outputDir;
		this.configXmlPath = configXmlPath;
		if(document!=null){
			this.documents = new ArrayList<String>();
			documents.add(document);
		}
		this.cssFilePath = cssFilePath;
	}
    
    /**
	 * Sets the system env variables.
	 */
    private void setSystemEnvVariables(){
    	if(configXmlPath==null){
			configXmlPath =System.getenv().get(Constants.INPUT_SYSENV_CONFIG);
			logger.info("configXmlPath" + configXmlPath);
		}
    	if(outputDir==null){
			outputDir =System.getenv().get(Constants.INPUT_SYSENV_OUTPUT_DIR);
			logger.info("outputDir" + outputDir);
		}
    	if(cssFilePath==null){
    		cssFilePath =System.getenv().get(Constants.INPUT_SYSENV_CSS);
    		logger.info("cssFilePath" + cssFilePath);
		}
    }
	
	/**
	 * Creates  and validate context.
	 */
	private void createAndValidateContext() {
		
		logger.entering("Driver", "createAndValidateContext");
		ConfigurationReader.loadDefaultConfiguration();
		setSystemEnvVariables();
		if (configXmlPath != null && !Utils.isEmpty(configXmlPath)) {
			ConfigurationReader.loadConfigurations(configXmlPath);
		}
		Context context = Context.getContext();
		if (documents != null) {
			if (context.getDocuments() == null) {
				context.setDocuments(new ArrayList<String>());
			}
			context.getDocuments().addAll(documents);
		}
		
		if (outputDir != null&& !Utils.isEmpty(outputDir)) {
			context.setOutputDir(outputDir);
		}
		if (cssFilePath != null && !Utils.isEmpty(cssFilePath)) {
			context.setCssFilePath(cssFilePath);
		}
		validateContext();
		logger.exiting("Driver", "createAndValidateContext");
	}
	
	
	/**
	 * Validate context.
	 */
	private void validateContext() {
		Context context = Context.getContext();
		StringBuffer errors = new StringBuffer();
		if (context.getOutputGenerators() == null
				|| context.getOutputGenerators().isEmpty()) {
			errors
					.append("No Output generators Specified in the Configuration\n");

		}else{
			if(context.getOutputGenerators().get(null)!=null){
				errors
				.append("name attribute is required for Output generators Specified in the Configuration\n");
			}
		}
		if (context.getOutputDir() == null || Utils.isEmpty(context.getOutputDir())) {
			errors.append("Output Folder Not specified in the Configuration\n");
		}
		if (context.getDocuments() == null || context.getDocuments().isEmpty()) {
			errors
					.append("Input Documents Not specified in the Configuration\n");
		}
		if (errors.length() > 0) {
			logger.severe("Configuration Exception");
			throw new ConfigurationException(errors.toString() + "Run the tool with help=true argument for complete usage information");
		}
	}

	/**
	 * Process method is the main entry method, and this should be invoked to call the tool.
	 */
	public synchronized void process() {
		logger.entering("Driver", "process");
		Context.refreshContext();
		createAndValidateContext();
		for (String document : Context.getContext().getDocuments()) {
			document=ConfigurationReader.convertToURL(document).toExternalForm();
			boolean isWsdl = isWsdl(document);
			try {			
				if (isWsdl) {
					WsdlParser wsdlParser =Context.getContext().getNewWsdlParser();
					WSDLDocInterface obj = (WSDLDocInterface)wsdlParser.parse(document);
					Context context = Context.getContext();
					for ( Map.Entry<String,OutputGenaratorParam> entry: context
							.getOutputGenerators().entrySet()) {
						OutputGenaratorParam param=entry.getValue();
						param.setNumOfDocuments(Context.getContext().getDocuments().size());
						param.getOutputGenerator().generateWsdlOutput(obj,param);
					}
				} else {
						XSDParser xsdParser = Context.getContext().getNewXsdParser();
						XSDDocInterface obj = xsdParser.parse(document);
						Context context = Context.getContext();
						for ( Map.Entry<String,OutputGenaratorParam> entry: context
								.getOutputGenerators().entrySet()) {
							OutputGenaratorParam param=entry.getValue();
							param.setNumOfDocuments(Context.getContext().getDocuments().size());
							param.getOutputGenerator().generateXsdOutput(obj,param);
						}
				}
				
				
			} catch (ParserException e) {
				logger.log(Level.SEVERE,"ParserException",e);
			}catch (Throwable t){
				logger.log(Level.SEVERE,"Runtime Exception",t);
			}
		}
		Context context = Context.getContext();
		for ( Map.Entry<String,OutputGenaratorParam> entry: context
				.getOutputGenerators().entrySet()) {
			
			try {
				entry.getValue().getOutputGenerator().completeProcessing();
			} catch (XsdDocException e) {
				logger.throwing(this.getClass().getName(), "Process", e);
			}
		}
		logger.exiting("Driver", "process");
	}
   
   /**
	 * Checks if is wsdl.
	 * 
	 * @param document
	 *            the document
	 * @return true, if is wsdl
	 */
   private boolean isWsdl(String document){
	   File file=new File(document);
	      String filenameExtension=file.getName();
		    int dotPos = filenameExtension.lastIndexOf(".");
		    String extension = filenameExtension.substring(dotPos+1);
		   if(extension==null ||Utils.isEmpty(extension) || !(extension.equalsIgnoreCase("xsd") || extension.equalsIgnoreCase("wsdl"))){
			   logger.log(Level.SEVERE, "Document "+document+" is not a valid wsdl//xsd");
			   throw new ConfigurationException("Document "+document+" is not a valid wsdl//xsd");
		   }
		   if(extension.equalsIgnoreCase("wsdl")){
			   return true;
		   }
		   return false;
	   

   }
	/**
	 * Sets the output dir.
	 * 
	 * @param outputDir
	 *            the new output dir
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	/**
	 * Sets the config xml path.
	 * 
	 * @param configXmlPath
	 *            the new config xml path
	 */
	public void setConfigXmlPath(String configXmlPath) {
		this.configXmlPath = configXmlPath;
	}

	/**
	 * Sets the documents.
	 * 
	 * @param documents
	 *            the new documents
	 */
	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}
}
