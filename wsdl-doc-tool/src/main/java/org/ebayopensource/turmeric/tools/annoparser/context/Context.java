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

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocument;
import org.ebayopensource.turmeric.tools.annoparser.XSDDocument;
import org.ebayopensource.turmeric.tools.annoparser.commons.FactoryTypes;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.AttributeElement;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ComplexType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.PortType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.SimpleType;
import org.ebayopensource.turmeric.tools.annoparser.exception.ParserException;
import org.ebayopensource.turmeric.tools.annoparser.parser.AnnotationParser;
import org.ebayopensource.turmeric.tools.annoparser.parser.impl.WsdlParserImpl;
import org.ebayopensource.turmeric.tools.annoparser.parser.impl.XSDParserImpl;

/**
 * The Class Context holds the context information for complete execution of the
 * tool.
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
	private Map<String, AnnotationParser> annotationParsers;

	/** The output generators. */
	private Map<String, OutputGenaratorParam> outputGenerators;

	private Map<FactoryTypes, Class> factoryClasses = new HashMap<FactoryTypes, Class>();

	private AnnotationParser defaultAnnotationParser;

	// /** The context. */
	// private static Context context=new Context();

	/**
	 * Instantiates a new context.
	 */
	private Context() {

	}

	private static ThreadLocal<Context> threadLocSingleton = new ThreadLocal<Context>() {
		protected synchronized Context initialValue() {
			return new Context();
		}
	};

	/**
	 * Refresh context.
	 */
	public static void refreshContext() {
		threadLocSingleton.set(new Context());

	}

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	public static Context getContext() {
		return threadLocSingleton.get();
	}

	/**
	 * Adds the output generator.
	 * 
	 * @param context
	 *            the context
	 */
	public void addOutputGenerator(OutputGenaratorParam context) {
		if (outputGenerators == null) {
			outputGenerators = new HashMap<String, OutputGenaratorParam>();
		}
		outputGenerators.put(context.getName(), context);
	}

	/**
	 * Adds the document.
	 * 
	 * @param document
	 *            the document
	 */
	public void addDocument(String document) {
		if (documents == null) {
			documents = new ArrayList<String>();
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
	public void addParser(String parserName, AnnotationParser parser) {
		if (annotationParsers == null) {
			annotationParsers = new HashMap<String, AnnotationParser>();
		}
		annotationParsers.put(parserName, parser);
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
	public void setAnnotationParsers(
			Map<String, AnnotationParser> annotationParsers) {
		this.annotationParsers = annotationParsers;
	}

	/**
	 * Gets the output generators.
	 * 
	 * @return the output generators
	 */
	public Map<String, OutputGenaratorParam> getOutputGenerators() {
		return outputGenerators;
	}

	/**
	 * Sets the output generators.
	 * 
	 * @param outputGenerators
	 *            the new output generators
	 */
	public void setOutputGenerators(
			Map<String, OutputGenaratorParam> outputGenerators) {
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
	 * @param defaultParser
	 *            the new default annotation parser
	 */
	public void setDefaultAnnotationParser(AnnotationParser defaultParser) {
		this.defaultAnnotationParser = defaultParser;
	}

	/**
	 * Adds the factory class.
	 *
	 * @param factoryTypes the factory types
	 * @param clazz the clazz
	 */
	public void addFactoryClass(FactoryTypes factoryTypes, Class clazz) {
		factoryClasses.put(factoryTypes, clazz);
	}

	/**
	 * Gets the factory class.
	 *
	 * @param factoryTypes the factory types
	 * @return the factory class
	 */
	public Class getFactoryClass(FactoryTypes factoryTypes) {
		return factoryClasses.get(factoryTypes);
	}

	/**
	 * Gets the new simple type.
	 *
	 * @return the new simple type
	 * @throws ParserException the parser exception
	 */
	public SimpleType getNewSimpleType() throws ParserException {
		return (SimpleType) getNewObject(FactoryTypes.SimpleType);
	}

	/**
	 * Gets the new element.
	 *
	 * @return the new element
	 * @throws ParserException the parser exception
	 */
	public Element getNewElement() throws ParserException {
		return (Element) getNewObject(FactoryTypes.Element);
	}

	/**
	 * Gets the new attribute.
	 *
	 * @return the new attribute
	 * @throws ParserException the parser exception
	 */
	public AttributeElement getNewAttribute() throws ParserException {
		return (AttributeElement) getNewObject(FactoryTypes.Attribute);
	}

	/**
	 * Gets the new complex type.
	 *
	 * @return the new complex type
	 * @throws ParserException the parser exception
	 */
	public ComplexType getNewComplexType() throws ParserException {
		return (ComplexType) getNewObject(FactoryTypes.ComplexType);
	}

	/**
	 * Gets the new enumeration.
	 *
	 * @return the new enumeration
	 * @throws ParserException the parser exception
	 */
	public EnumElement getNewEnumeration() throws ParserException {
		return (EnumElement) getNewObject(FactoryTypes.Enumeration);
	}

	/**
	 * Gets the new operation.
	 *
	 * @return the new operation
	 * @throws ParserException the parser exception
	 */
	public OperationHolder getNewOperation() throws ParserException {
		return (OperationHolder) getNewObject(FactoryTypes.Operation);
	}

	/**
	 * Gets the new port type.
	 *
	 * @return the new port type
	 * @throws ParserException the parser exception
	 */
	public PortType getNewPortType() throws ParserException {
		return (PortType) getNewObject(FactoryTypes.PortType);
	}

	/**
	 * Gets the new xsd parser.
	 *
	 * @return the new xsd parser
	 * @throws ParserException the parser exception
	 */
	public XSDParserImpl getNewXsdParser() throws ParserException {
		return (XSDParserImpl) getNewObject(FactoryTypes.XsdParser);
	}

	/**
	 * Gets the new wsdl parser.
	 *
	 * @return the new wsdl parser
	 * @throws ParserException the parser exception
	 */
	public WsdlParserImpl getNewWsdlParser() throws ParserException {
		return (WsdlParserImpl) getNewObject(FactoryTypes.WsdlParser);
	}

	/**
	 * Gets the new xsd document.
	 *
	 * @return the new xsd document
	 * @throws ParserException the parser exception
	 */
	public XSDDocument getNewXsdDocument() throws ParserException {
		return (XSDDocument) getNewObject(FactoryTypes.XsdDocument);
	}

	/**
	 * Gets the new wsdl document.
	 *
	 * @return the new wsdl document
	 * @throws ParserException the parser exception
	 */
	public WSDLDocument getNewWsdlDocument() throws ParserException {
		return (WSDLDocument) getNewObject(FactoryTypes.WsdlDocument);
	}

	private static Map<FactoryTypes, Class> baseClasses = new HashMap<FactoryTypes, Class>();
	static {
		baseClasses.put(FactoryTypes.SimpleType, SimpleType.class);
		baseClasses.put(FactoryTypes.Element, Element.class);
		baseClasses.put(FactoryTypes.Attribute, AttributeElement.class);
		baseClasses.put(FactoryTypes.ComplexType, ComplexType.class);
		baseClasses.put(FactoryTypes.Enumeration, EnumElement.class);
		baseClasses.put(FactoryTypes.Operation, OperationHolder.class);
		baseClasses.put(FactoryTypes.PortType, PortType.class);
		baseClasses.put(FactoryTypes.XsdParser, XSDParserImpl.class);
		baseClasses.put(FactoryTypes.WsdlParser, WsdlParserImpl.class);
		baseClasses.put(FactoryTypes.XsdDocument, XSDDocument.class);
		baseClasses.put(FactoryTypes.WsdlDocument, WSDLDocument.class);
	}

	private Object getNewObject(FactoryTypes type) throws ParserException {
		Class baseClazz = baseClasses.get(type);
		Class clazz = factoryClasses.get(type);
		if (clazz != null) {

			
				try {
					return clazz.asSubclass(baseClazz).newInstance();
				} catch (InstantiationException e) {
					throw new ParserException("Wrong " + type
							+ " Factory class specified, " + type
							+ " Class should have Default Constructor" + "\n"
							+ baseClazz.getName());
				} catch (IllegalAccessException e) {
					throw new ParserException("Wrong " + type
							+ " Factory class specified, " + type
							+ " Class should extend" + "\n"
							+ baseClazz.getName());
				}
			
		}
		throw new ParserException(type + " Factory class is required, " + type
				+ " Class should extend" + "\n" + baseClazz.getName());

	}

}
