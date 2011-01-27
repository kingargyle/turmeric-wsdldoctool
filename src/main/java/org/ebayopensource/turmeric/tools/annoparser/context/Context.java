/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.tools.annoparser.parser.AnnotationParser;

/**
 * The Class Context holds the context information for complete execution of the tool.
 *
 * @author sdaripelli
 */
public class Context {
	
	/** The css file path. */
	private String cssFilePath;
	
	/** The output dir. */
	private String outputDir;
	
	/** The documents. */
	private List<String> documents;
	
	/** The annotation parsers. */
	private Map<String,AnnotationParser> annotationParsers;
	
	/** The output generators. */
	private Map<String,OutputGenaratorParam> outputGenerators;
	
	
	
	private AnnotationParser defaultAnnotationParser;
	
//	/** The context. */
//	private static Context context=new Context();
	
	

	/**
	 * Instantiates a new context.
	 */
	private Context(){
		
	}
	
	private static ThreadLocal<Context> threadLocSingleton = new ThreadLocal<Context>() {
        protected synchronized Context initialValue() {
            return new Context();
        }
    };

	/**
	 * Refresh context.
	 */
	public static void refreshContext(){
		threadLocSingleton.set(new Context());
		
	}
	
	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public static Context getContext(){
		return threadLocSingleton.get();
	}
	
	/**
	 * Adds the output generator.
	 * 
	 * @param context
	 *            the context
	 */
	public void addOutputGenerator(OutputGenaratorParam context){
		if(outputGenerators==null){
			outputGenerators=new HashMap<String,OutputGenaratorParam>();
		}
		outputGenerators.put(context.getName(), context);
	}
	
	/**
	 * Adds the document.
	 * 
	 * @param document
	 *            the document
	 */
	public void addDocument(String document){
		if(documents==null){
			documents=new ArrayList<String>();
		}
		documents.add(document);
	}
	
	/**
	 * Adds the parser.
	 * 
	 * @param parserName
	 *            the parser name
	 * @param parser
	 *            the parser
	 */
	public void addParser(String parserName,AnnotationParser parser){
		if(annotationParsers==null){
			annotationParsers=new HashMap<String, AnnotationParser>();
		}
		annotationParsers.put(parserName,parser);
	}

	/**
	 * Gets the css file path.
	 * 
	 * @return the css file path
	 */
	public String getCssFilePath() {
		return cssFilePath;
	}

	/**
	 * Sets the css file path.
	 * 
	 * @param cssFilePath
	 *            the new css file path
	 */
	public void setCssFilePath(String cssFilePath) {
		this.cssFilePath = cssFilePath;
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
	 * @param outputDir
	 *            the new output dir
	 */
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	/**
	 * Gets the documents.
	 * 
	 * @return the documents
	 */
	public List<String> getDocuments() {
		return documents;
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

	/**
	 * Gets the annotation parsers.
	 * 
	 * @return the annotation parsers
	 */
	public Map<String, AnnotationParser> getAnnotationParsers() {
		return annotationParsers;
	}

	/**
	 * Sets the annotation parsers.
	 * 
	 * @param annotationParsers
	 *            the annotation parsers
	 */
	public void setAnnotationParsers(Map<String, AnnotationParser> annotationParsers) {
		this.annotationParsers = annotationParsers;
	}

	/**
	 * Gets the output generators.
	 * 
	 * @return the output generators
	 */
	public Map<String,OutputGenaratorParam> getOutputGenerators() {
		return outputGenerators;
	}

	/**
	 * Sets the output generators.
	 * 
	 * @param outputGenerators
	 *            the new output generators
	 */
	public void setOutputGenerators(Map<String,OutputGenaratorParam> outputGenerators) {
		this.outputGenerators = outputGenerators;
	}

	/**
	 * Gets the default annotation parser.
	 *
	 * @return the default annotation parser
	 */
	public AnnotationParser getDefaultAnnotationParser() {
		return defaultAnnotationParser;
	}

	/**
	 * Sets the default annotation parser.
	 *
	 * @param defaultParser the new default annotation parser
	 */
	public void setDefaultAnnotationParser(AnnotationParser defaultParser) {
		this.defaultAnnotationParser = defaultParser;
	}
	
	
	

}
