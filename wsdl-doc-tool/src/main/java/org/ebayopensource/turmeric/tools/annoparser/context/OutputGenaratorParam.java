/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.context;

import java.util.Map;

import org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator;



/**
 * The Class OutputFormatterContext.
 *
 * @author sdaripelli
 */
public class OutputGenaratorParam {
	
	private String name;
   
   /** The output generator. */
   private OutputGenerator outputGenerator; 
   
   /** The mode. */
   private String outputDir;
   
   private Map<String,String> parameters;
   
   private int numOfDocuments=1;

   /**
    * Gets the num of documents.
    *
    * @return the num of documents
    */
   public int getNumOfDocuments() {
	return numOfDocuments;
}

/**
 * Sets the num of documents.
 *
 * @param numOfDocuments the new num of documents
 */
public void setNumOfDocuments(int numOfDocuments) {
	this.numOfDocuments = numOfDocuments;
}

/**
    * Instantiates a new output genarator param.
    */
   public OutputGenaratorParam() {
	
   }
   
   /**
	 * Instantiates a new output formatter context.
	 * 
	 * @param gen
	 *            the gen
	 */
   public OutputGenaratorParam(OutputGenerator gen){
		 this.outputGenerator=gen;
	   }

/**
 * Gets the output generator.
 * 
 * @return the output generator
 */
public OutputGenerator getOutputGenerator() {
	return outputGenerator;
}

/**
 * Sets the output generator.
 * 
 * @param outputGenerator
 *            the new output generator
 */
public void setOutputGenerator(OutputGenerator outputGenerator) {
	this.outputGenerator = outputGenerator;
}

/**
 * Gets the name.
 *
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * Sets the name.
 *
 * @param name the new name
 */
public void setName(String name) {
	this.name = name;
}

/**
 * Gets the output dir.
 *
 * @return the output dir
 */
public String getOutputDir() {
	return outputDir;
}

/**
 * Sets the output dir.
 *
 * @param outputDir the new output dir
 */
public void setOutputDir(String outputDir) {
	this.outputDir = outputDir;
}

/**
 * Gets the parameters.
 *
 * @return the parameters
 */
public Map<String, String> getParameters() {
	return parameters;
}

/**
 * Sets the parameters.
 *
 * @param parameters the parameters
 */
public void setParameters(Map<String, String> parameters) {
	this.parameters = parameters;
}


}
