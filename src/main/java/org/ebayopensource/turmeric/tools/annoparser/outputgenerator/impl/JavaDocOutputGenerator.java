/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface;
import org.ebayopensource.turmeric.tools.annoparser.commons.AnnotationsHelper;
import org.ebayopensource.turmeric.tools.annoparser.commons.Constants;
import org.ebayopensource.turmeric.tools.annoparser.context.Context;
import org.ebayopensource.turmeric.tools.annoparser.context.OutputGenaratorParam;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.AbstractType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Attribute;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ComplexType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationInfo;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationTag;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.PortType;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.SimpleType;
import org.ebayopensource.turmeric.tools.annoparser.exception.OutputFormatterException;
import org.ebayopensource.turmeric.tools.annoparser.outputgenerator.OutputGenerator;
import org.ebayopensource.turmeric.tools.annoparser.utils.HtmlUtils;
import org.ebayopensource.turmeric.tools.annoparser.utils.Utils;

/**
 * The Class JavaDocOutputGenerator generated javadoc Output for the WSDL
 * Document Supplied.
 * 
 * @author sdaripelli
 */
public class JavaDocOutputGenerator implements OutputGenerator {

	/** The current types folder path. */
	private String currentTypesFolderPath = null;
	private final static String CLASS_NAME = JavaDocOutputGenerator.class
			.getClass().getName();
	Logger logger = Logger.getLogger(CLASS_NAME);

	private OutputGenaratorParam outputGenaratorParam;

	private Map<String, List<String>> packageServicesMap = new HashMap<String, List<String>>();
	private List<AbstractType> processedTypes = new ArrayList<AbstractType>();
	
	private static final String SEPARATOR="/";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.tools.annoparser.outputformatter.DocHandler
	 * #handleWsdlDoc
	 * (org.ebayopensource.turmeric.tools.annoparser.WSDLDocInterface,
	 * java.lang.String)
	 */

	public void generateWsdlOutput(WSDLDocInterface wsdlDoc,
			OutputGenaratorParam outputGenaratorParam) throws OutputFormatterException {
		logger.entering("JavaDocOutputGenerator", "handleWsdlDoc",
				new Object[] { wsdlDoc, outputGenaratorParam });
		String packageName = wsdlDoc.getPackageName();
		this.outputGenaratorParam = outputGenaratorParam;
		if(packageName==null){
			packageName="DomainNotAvailable";
		}
			currentTypesFolderPath = getCurrentOutputDir() 
					+ File.separator + packageName + File.separator + "types"
					+ File.separator;
		
		writeCssFiles();
		logger.logp(Level.INFO, "JavaDocOutputGenerator", "handleWsdlDoc",
				"Types Folder Path");
		List<PortType> portTypes = wsdlDoc.getPortTypes();
		for (PortType portType : portTypes) {
			StringBuffer html = new StringBuffer();
			html.append(HtmlUtils.getStartTags(wsdlDoc.getServiceName(),
					packageName));
			buildHeader(html);
			html.append(Constants.HTML_BR);
			buildPortType(html, portType, wsdlDoc);
			html.append(Constants.HTML_BR);
			buildOperationTable(html, portType, wsdlDoc);
			html.append(Constants.HTML_BR);
			buildOperationDetails(html, portType, wsdlDoc);
			addFooter(html);
			html.append(HtmlUtils.getEndTags());
			String outputDir = getCurrentOutputDir() + File.separator;
			writeFile(html, outputDir + File.separator
					+ packageName, wsdlDoc.getServiceName()
					+ Constants.DOT_HTML);
		}
		addPackageToServiceMap(packageName, wsdlDoc.getServiceName());
		processedTypes = new ArrayList<AbstractType>();
		logger.exiting("JavaDocOutputGenerator", "handleWsdlDoc", new Object[] {
				wsdlDoc, outputGenaratorParam });
	}

	/**
	 * Adds the package to service map.
	 * 
	 * @param packageName
	 *            the package name
	 * @param serviceName
	 *            the service name
	 */
	private void addPackageToServiceMap(String packageName, String serviceName) {

		List<String> list = packageServicesMap.get(packageName);
		if (list == null) {
			list = new ArrayList<String>();
		}
		list.add(serviceName);
		packageServicesMap.put(packageName, list);
	}

	/**
	 * Creates the all packages description file.
	 * @throws OutputFormatterException 
	 */
	private void createAllPackagesDescriptionFile() throws OutputFormatterException {
		StringBuffer html = new StringBuffer();
		html.append(HtmlUtils.getStartTags(Constants.ALL_CLASSES, null));
		html.append(getTextInDiv("WSDL API specification", "pageHeading"));
		html.append(Constants.HTML_BR);
		addFooter(html);
		html.append(HtmlUtils.getEndTags());
		writeFile(html, getCurrentOutputDir(), Constants.PACKAGES.toLowerCase()
				+ Constants.INDEX + Constants.DOT_HTML);
	}

	/**
	 * Creates the index file.
	 * @throws OutputFormatterException 
	 */
	private void createIndexFile() throws OutputFormatterException {
		StringBuffer html = new StringBuffer(200);
		html.append("<html><frameset cols=\"20%,80%\">");
		html.append("<frameset rows=\"30%,70%\">");
		html.append("<frame src='" + Constants.PACKAGES.toLowerCase()
				+ Constants.DOT_HTML + "'/>");
		html.append("<frame src='" + Constants.ALLCLASSES.toLowerCase()
				+ Constants.DOT_HTML + "' name='" + Constants.CLASSESFRAME
				+ "'/>");
		html.append("</frameset>");
		html.append("<frame src='" + Constants.PACKAGES.toLowerCase()
				+ Constants.INDEX + Constants.DOT_HTML + "' name='" + Constants.CLASSFRAME
				+ "'/>");
		html.append("</frameset></html>");
		writeFile(html, getCurrentOutputDir(), Constants.INDEX
				+ Constants.DOT_HTML);
	}

	/**
	 * Creates the individual package files.
	 * @throws OutputFormatterException 
	 */
	private void createIndividualPackageFiles() throws OutputFormatterException {
		Set<String> set = packageServicesMap.keySet();
		for (String packageName : set) {
			List<String> list = packageServicesMap.get(packageName);
			StringBuffer html = new StringBuffer();
			html.append(HtmlUtils.getStartTags(Constants.CLASSES, packageName));
			html.append(Constants.HTML_BOLD_START + Constants.CLASSES
					+ Constants.HTML_BOLD_END + Constants.HTML_BR);
			for (String className : list) {
				html.append(HtmlUtils.getAnchorTag(className, className
						+ Constants.DOT_HTML, null, className,
						Constants.CLASSFRAME, null)
						+ Constants.HTML_BR);
			}
			html.append(HtmlUtils.getEndTags());
			writeFile(html, getCurrentOutputDir() + packageName,
					Constants.ALLCLASSES + Constants.DOT_HTML);
		}
	}

	/**
	 * Creates the all classes file.
	 * @throws OutputFormatterException 
	 */
	private void createAllClassesFile() throws OutputFormatterException {
		Set<String> set = packageServicesMap.keySet();
		StringBuffer html = new StringBuffer();
		html.append(HtmlUtils.getStartTags(Constants.ALL_CLASSES, null));
		html.append(Constants.HTML_BOLD_START + Constants.ALL_CLASSES
				+ Constants.HTML_BOLD_END + Constants.HTML_BR);
		for (String packageName : set) {
			List<String> list = packageServicesMap.get(packageName);
			for (String className : list) {
				html.append(HtmlUtils.getAnchorTag(className, "."+SEPARATOR+ packageName + SEPARATOR
						+ className + Constants.DOT_HTML, null, className,
						Constants.CLASSFRAME, null)
						+ Constants.HTML_BR);
			}
		}
		html.append(HtmlUtils.getEndTags());
		writeFile(html, getCurrentOutputDir(), Constants.ALLCLASSES
				+ Constants.DOT_HTML);
	}

	/**
	 * Creates the all packages file.
	 * @throws OutputFormatterException 
	 */
	private void createAllPackagesFile() throws OutputFormatterException {
		StringBuffer html = new StringBuffer();
		html.append(HtmlUtils.getStartTags(Constants.PACKAGES, null));
		Set<String> set = packageServicesMap.keySet();
		html.append(Constants.HTML_BOLD_START + Constants.PACKAGES
				+ Constants.HTML_BOLD_END + Constants.HTML_BR);
		for (String packageName : set) {
			html.append(HtmlUtils.getAnchorTag(packageName, "."+SEPARATOR + packageName + SEPARATOR
					+ Constants.ALLCLASSES + Constants.DOT_HTML, null,
					packageName, Constants.CLASSESFRAME, null)
					+ Constants.HTML_BR);
		}
		html.append(HtmlUtils.getEndTags());
		writeFile(html, getCurrentOutputDir(), Constants.PACKAGES.toLowerCase()
				+ Constants.DOT_HTML);
	}

	/**
	 * Write complex type file.
	 * 
	 * @param doc
	 *            the doc
	 * @param type
	 *            the type
	 * @param parentPath
	 *            the parent path
	 * @throws OutputFormatterException 
	 */
	private void writeComplexTypeFile(WSDLDocInterface doc, ComplexType type,
			String parentPath) throws OutputFormatterException {
		logger.entering("JavaDocOuputGenerator", "writeComplexTypeFile",
				new Object[] { doc, type });
		processedTypes.add(type);
		boolean isRecursive = false;
		StringBuffer html = new StringBuffer();
		String typeName = type.getName();
		if (typeName != null && !Utils.isEmpty(typeName)) {
			String parentType = type.getParentType();
			if (!Utils.isEmpty(parentType)) {
				parentType = Utils.removeNameSpace(parentType);
				ComplexType parent = doc.searchCType(parentType);
				if (parent != null && !processedTypes.contains(parent)) {
					writeComplexTypeFile(doc, parent, parentPath);
				}
			}
			html = new StringBuffer();
			html.append(HtmlUtils.getStartTags(typeName, doc.getPackageName()
					+ File.separator + "types"));

			html.append(getTextInDiv("Type : " + typeName, "JavadocHeading"));

			html.append(Constants.HTML_HR);
			html.append(Constants.HTML_DL_START + Constants.HTML_DD_START);
			if (!Utils.isEmpty(parentType)) {
				html.append(getTextInSpan("Extends: ", "javaDocInlineHeading"));
				html.append(HtmlUtils.getAnchorTag(null, parentType
						+ Constants.DOT_HTML, parentType, parentType)
						+ Constants.HTML_BR);
			}
			if (type.getAnnotations().getDocumentation() != null) {
				html.append(getTextInSpan(type.getAnnotations()
						.getDocumentation(), "javaDocOpDetail"));
			}
			html.append(Constants.HTML_DD_END + Constants.HTML_DL_END);
			StringBuffer deprDet = AnnotationsHelper.processDeprication(type
					.getAnnotations());
			if (deprDet != null) {
				html
						.append(getTextInDiv("Depricated: ",
								"javaDocInlineHeading"));
				html.append(deprDet);
			}
			processRelatedInfo(html, doc, type.getAnnotations());

			html.append(Constants.HTML_HR);

			html.append(Constants.HTML_BR + Constants.HTML_BR);
			html.append(getTableTagWithStyle("JavadocTable"));
			html.append(getTableRowTagWithStyle("JavadocTableTr"));
			html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
			html.append("Fields");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(Constants.HTML_TABLE_TR_END);
			html.append(Constants.HTML_TABLE_END);
			html.append(Constants.HTML_BR + Constants.HTML_BR);
			Set<Element> elements = type.getChildElements();
			if (type.getSimpleAttributeContent() != null) {
				elements.addAll(type.getSimpleAttributeContent());
			}
			for (Element element : elements) {
				if (parentPath.contains("." + element.getName() + ".")) {
					isRecursive = true;
				}
				String nodePath = parentPath + "." + element.getName();
				String typeCName = getCTypeTypeName(element.getType());
				if (type.getName().equalsIgnoreCase(typeCName)) {
					isRecursive = true;
				}

				if (doc.searchCType(typeCName) != null && !isRecursive) {
					writeComplexTypeFile(doc, doc.searchCType(typeCName),
							nodePath);
					html.append(getTextInSpan(element.getName()
							+ "  : <b>"
							+ HtmlUtils.getAnchorTag(null, typeCName
									+ Constants.DOT_HTML, typeCName, typeCName)
							+ "</b>", "javaDocMethodTypes"));
				} else if (doc.searchSimpleType(typeCName) != null) {
					writeSimpleTypeFile(doc, doc.searchSimpleType(typeCName));
					html.append(getTextInSpan(element.getName()
							+ "  : <b>"
							+ HtmlUtils.getAnchorTag(null, typeCName
									+ Constants.DOT_HTML, typeCName, typeCName)
							+ "</b>", "javaDocMethodTypes"));
				} else {
					html.append(getTextInSpan(element.getName() + "  : <b>"
							+ typeCName + "</b>", "javaDocMethodTypes"));
				}
				if (element.getAnnotationInfo().getDocumentation() != null) {
					html.append(Constants.HTML_BR
							+ getTextInSpan(element.getAnnotationInfo()
									.getDocumentation(), "javaDocOpDetail"));
				}
				processDefaultsAndBoundries(html, doc, element);
				deprDet = AnnotationsHelper.processDeprication(element
						.getAnnotationInfo());
				if (deprDet != null) {
					html.append(getTextInDiv("Depricated: ",
							"javaDocInlineHeading"));
					html.append(deprDet);
				}
				processRelatedInfo(html, doc, element.getAnnotationInfo());

				html.append(Constants.HTML_HR + Constants.HTML_BR);

			}

			addFooter(html);
			writeFile(html, currentTypesFolderPath, typeName
					+ Constants.DOT_HTML);
		}
		logger.exiting("JavaDocOuputGenerator", "writeComplexTypeFile", html);
	}

	private void processSeeLinks(StringBuffer html, ParsedAnnotationInfo annInfo) {

		if (annInfo != null) {
			List<ParsedAnnotationTag> seeLinks = AnnotationsHelper
					.getAnnotationTag(annInfo, "SeeLink");
			if (seeLinks != null) {
				html.append(getTextInDiv("<br>See: <br>", "javaDocRelatedHeading"));
				for (ParsedAnnotationTag seeLink : seeLinks) {
					html.append(AnnotationsHelper.processSeelink(seeLink)
							+ "<br>");
				}
			}
			List<ParsedAnnotationTag> callInofs = AnnotationsHelper
					.getCallInfo(annInfo);
			if (callInofs != null) {
				for (ParsedAnnotationTag callInfo : callInofs) {
					seeLinks = AnnotationsHelper.getCallInfoChildren(callInfo,
							"SeeLink");
					if (seeLinks != null) {

						if (callInfo.getChildren().get("AllCalls") != null) {
							html.append(getTextInDiv("For All Calls <br>See: ",
									"javaDocRelatedHeading"));
							
						} else {
							String value = AnnotationsHelper
									.getFirstCallInfoTagValue(callInfo,
											"AllCallsExcept");
							if (!Utils.isEmpty(value)) {
								html.append(getTextInDiv(
										"For All Calls Except: " + value
												+ " <br>See: ",
										"javaDocRelatedHeading"));
								
							}
						}
						List<ParsedAnnotationTag> calls = AnnotationsHelper
								.getCallInfoChildren(callInfo, "CallName");
						if (calls != null) {
							String callString = "For Calls : ";
							Iterator<ParsedAnnotationTag> callIter = calls
									.iterator();
							while (callIter.hasNext()) {
								ParsedAnnotationTag call = callIter.next();
								callString = callString + call.getTagValue();
								if (callIter.hasNext()) {
									callString = callString + ",";
								}
							}
							html.append(getTextInDiv(callString + " <br>See:",
									"javaDocRelatedHeading"));
							
						}

						for (ParsedAnnotationTag seeLink : seeLinks) {
							html.append(AnnotationsHelper
									.processSeelink(seeLink)
									+ "<br>");
						}
					}
				}
			}
		}

	}

	/**
	 * Write simple type file.
	 * 
	 * @param doc
	 *            the doc
	 * @param type
	 *            the type
	 * @throws OutputFormatterException 
	 */
	private void writeSimpleTypeFile(WSDLDocInterface doc, SimpleType type) throws OutputFormatterException {
		logger.entering("JavaDocOuputGenerator", "writeSimpleTypeFile",
				new Object[] { doc, type });
		String typeName = type.getName();
		StringBuffer html = new StringBuffer();
		if (typeName != null && !Utils.isEmpty(typeName)) {
			html = new StringBuffer();
			html.append(HtmlUtils.getStartTags(typeName, doc.getPackageName()
					+ File.separator + "types"));
			html.append(getTextInDiv("Type : " + typeName, "JavadocHeading"));

			html.append(Constants.HTML_HR);
			html.append(Constants.HTML_DL_START + Constants.HTML_DD_START);

			if (type.getAnnotations().getDocumentation() != null) {
				html.append(getTextInSpan(type.getAnnotations()
						.getDocumentation(), "javaDocOpDetail"));
			}
			html.append(Constants.HTML_DD_END + Constants.HTML_DL_END);
			StringBuffer deprDet = AnnotationsHelper.processDeprication(type
					.getAnnotations());
			if (deprDet != null) {
				html
						.append(getTextInDiv("Depricated: ",
								"javaDocInlineHeading"));
				html.append(deprDet);
			}
			processRelatedInfo(html, doc, type.getAnnotations());

			html.append(Constants.HTML_HR);

			html.append(Constants.HTML_BR + Constants.HTML_BR);

			html.append(getTableTagWithStyle("JavadocTable"));
			html.append(getTableRowTagWithStyle("JavadocTableTr"));
			html.append("<th colspan=\"2\" class=\"JavadocTableHeaders\">");
			html.append("Enumeration Values");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(Constants.HTML_TABLE_TR_END);
			html.append(getTableRowTagWithStyle("JavadocTableTr"));
			html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
			html.append("Value");
			html.append(Constants.HTML_TABLE_TH_END);

			html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
			html.append("Description");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(Constants.HTML_TABLE_TR_END);

			List<EnumElement> enumElements = type.getEnums();
			for (EnumElement enumElement : enumElements) {
				html.append(getTableRowTagWithStyle("JavadocTableTr"));
				html.append(getTableDataTagWithStyle("JavadocTableTd"));
				html.append(enumElement.getValue());
				html.append(Constants.HTML_TABLE_TD_END);
				html.append(getTableDataTagWithStyle("JavadocTableTd"));
				if (enumElement.getAnnotations().getDocumentation() != null) {
					html
							.append(enumElement.getAnnotations()
									.getDocumentation());
				}
				deprDet = AnnotationsHelper.processDeprication(enumElement
						.getAnnotations());
				if (deprDet != null) {
					html.append(getTextInDiv("Depricated: ",
							"javaDocInlineHeading"));
					html.append(deprDet);
				}
				processRelatedInfo(html, doc, enumElement.getAnnotations());
				html.append(Constants.HTML_TABLE_TD_END);
				html.append(Constants.HTML_TABLE_TR_END);
			}

			html.append(Constants.HTML_TABLE_END);

			addFooter(html);
			writeFile(html, currentTypesFolderPath, typeName
					+ Constants.DOT_HTML);

		}

		logger.exiting("JavaDocOuputGenerator", "writeSimpleTypeFile", html);
	}

	/**
	 * Gets the c type type name.
	 * 
	 * @param type
	 *            the type
	 * @return the c type type name
	 */
	private String getCTypeTypeName(String type) {
		String retType = "";
		if (type != null) {
			String[] types = type.split(":");
			if (types != null) {
				switch (types.length) {
				case 1:
					retType = types[0];
					break;
				case 2:
					retType = types[1];
					break;
				default:
					retType = "";
				}

			}
		}
		return retType;
	}

	/**
	 * Write css files.
	 * @throws OutputFormatterException 
	 */
	private void writeCssFiles() throws OutputFormatterException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(
				"style.css");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			String outputDir = getCurrentOutputDir();
			writeFile(sb, outputDir + File.separator + "css",
					"JavaDocDefaultStyle.css");
			if (Context.getContext().getCssFilePath() != null) {
				sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				writeFile(sb, outputDir + File.separator + "css",
						"CustomStyle.css");

			}

		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	}

	/**
	 * Write file.
	 * 
	 * @param html
	 *            the html
	 * @param dir
	 *            the dir
	 * @param fileName
	 *            the file name
	 * @throws OutputFormatterException 
	 */
	private void writeFile(StringBuffer html, String dir, String fileName) throws OutputFormatterException {
		try {
			File file = new File(dir);
			file.mkdirs();
			FileWriter fw = new FileWriter(dir + File.separator + fileName);
			fw.write(html.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			throw new OutputFormatterException(e);
		}
	}

	/**
	 * Gets the table tag with style.
	 * 
	 * @param styleClassName
	 *            the style class name
	 * @return the table tag with style
	 */
	private String getTableTagWithStyle(String styleClassName) {
		String tableWithAttr = Constants.HTML_TABLE_START_WITH_ATTRS;
		return tableWithAttr.replaceAll("\\{#\\}", "class=\"" + styleClassName
				+ "\"");
	}

	/**
	 * Gets the table row tag with style.
	 * 
	 * @param styleClassName
	 *            the style class name
	 * @return the table row tag with style
	 */
	private String getTableRowTagWithStyle(String styleClassName) {
		String tableWithAttr = Constants.HTML_TABLE_TR_START_WITH_ATTRS;
		return tableWithAttr.replaceAll("\\{#\\}", "class=\"" + styleClassName
				+ "\"");
	}

	/**
	 * Gets the table data tag with style.
	 * 
	 * @param styleClassName
	 *            the style class name
	 * @return the table data tag with style
	 */
	private String getTableDataTagWithStyle(String styleClassName) {
		String tableWithAttr = Constants.HTML_TABLE_TD_START_WITH_ATTRS;
		return tableWithAttr.replaceAll("\\{#\\}", "class=\"" + styleClassName
				+ "\"");
	}

	/**
	 * Gets the table head tag with style.
	 * 
	 * @param styleClassName
	 *            the style class name
	 * @return the table head tag with style
	 */
	private String getTableHeadTagWithStyle(String styleClassName) {
		String tableWithAttr = Constants.HTML_TABLE_TH_START_WITH_ATTRS;
		return tableWithAttr.replaceAll("\\{#\\}", "class=\"" + styleClassName
				+ "\"");
	}

	/**
	 * Builds the header.
	 * 
	 * @param html
	 *            the html
	 */
	private void buildHeader(StringBuffer html) {

		logger.entering("JavaDocOutputGenerator", "buildHeader", html);
		html.append(HtmlUtils.getAnchorTag("Top", null, null, null));
		html.append(getTableTagWithStyle("JavadocHeaderTable"));
		html.append(getTableRowTagWithStyle("JavadocTableTr"));
		html.append(getTableDataTagWithStyle("JavadocTableTd"));
		html.append("Summary: ");
		html.append(HtmlUtils.getAnchorTag(null, "#"
				+ Constants.OPERATION_SUMMARY_HREF, Constants.OPERATIONS_LABEL,
				Constants.OPERATIONS_LABEL));

		html.append(Constants.HTML_TABLE_TD_END);

		html.append(getTableDataTagWithStyle("JavadocTableTd"));
		html.append("Detail: ");
		html.append(HtmlUtils.getAnchorTag(null, "#"
				+ Constants.OPERATIONS_DETAIL_HREF, Constants.OPERATIONS_LABEL,
				Constants.OPERATIONS_LABEL));
		html.append(Constants.HTML_TABLE_TD_END);

		html.append(Constants.HTML_TABLE_TR_END);
		html.append(Constants.HTML_TABLE_END);
		html.append(Constants.HTML_HR);
		logger.exiting("JavaDocOutputGenerator", "buildHeader", html);
	}

	/**
	 * Adds the footer.
	 * 
	 * @param html
	 *            the html
	 */
	private void addFooter(StringBuffer html) {
		logger.entering("JavaDocOutputGenerator", "addFooter", html);
		String content = getFooterInformation();
		html.append(getTextInDiv(content, "footer"));
		html.append(HtmlUtils.getEndTags());
		logger.exiting("JavaDocOutputGenerator", "addFooter", html);
	}

	/**
	 * Gets the footer information. This method can be over ridden to supply
	 * Footer.
	 * 
	 * @return the footer information
	 */
	protected String getFooterInformation() {
		return "";
	}

	/**
	 * Gets the text in div.
	 * 
	 * @param text
	 *            the text
	 * @param styleClassName
	 *            the style class name
	 * @return the text in div
	 */
	private String getTextInDiv(String text, String styleClassName) {
		String temp = "";
		if (styleClassName != null) {
			temp += "class='" + styleClassName + "'";
		}
		String tag = Constants.HTML_DIV_START.replace("{#}", temp);
		tag += text;
		tag += Constants.HTML_DIV_END;
		logger.info("Div Tag:" + tag);
		return tag;
	}

	/**
	 * Gets the text in span.
	 * 
	 * @param text
	 *            the text
	 * @param styleClassName
	 *            the style class name
	 * @return the text in span
	 */
	private String getTextInSpan(String text, String styleClassName) {
		String temp = "";
		if (styleClassName != null) {
			temp += "class='" + styleClassName + "'";
		}
		String tag = Constants.HTML_SPAN_START.replace("{#}", temp);
		tag += text;
		tag += Constants.HTML_SPAN_END;
		return tag;
	}

	/**
	 * Builds the port type.
	 * 
	 * @param html
	 *            the html
	 * @param portType
	 *            the port type
	 * @param serviceName
	 *            the service name
	 */
	private void buildPortType(StringBuffer html, PortType portType,
			WSDLDocInterface doc) {
		String serviceName = doc.getServiceName();
		logger.entering("JavaDocOutputGenerator", "buildPortType",
				new Object[] { html, portType, serviceName });
		String heading = Constants.SERVICE_LABEL + serviceName;

		html.append(getTextInDiv(heading, "JavadocHeading"));
		ParsedAnnotationInfo anno=doc.getAnnotations();
		String version=AnnotationsHelper.getFirstAnnotationValue(anno, "Version");
		if (!Utils.isEmpty(version)) {
			heading = "<br>Version:  " + version;
		}
		html.append(getTextInDiv(heading, "javaDocInlineHeading"));
		html.append(Constants.HTML_HR);
		html.append(Constants.HTML_DL_START + Constants.HTML_DD_START);
		if(anno!=null && !Utils.isEmpty(anno.getDocumentation())){
			html.append(anno.getDocumentation());
		}
		if (portType.getAnnotations()!=null&&  portType.getAnnotations().getDocumentation() != null) {
			html.append("<br>" + portType.getAnnotations().getDocumentation());
		}
		html.append(Constants.HTML_DD_END + Constants.HTML_DL_END);
		html.append(Constants.HTML_HR);
		logger.exiting("JavaDocOutputGenerator", "buildPortType", html);
	}

	/**
	 * Builds the operation table.
	 * 
	 * @param html
	 *            the html
	 * @param portType
	 *            the port type
	 * @param wsdlDoc
	 *            the wsdl doc
	 */
	private void buildOperationTable(StringBuffer html, PortType portType,
			WSDLDocInterface wsdlDoc) {
		logger.entering("JavaDocOutputGenerator", "buildOperationTable",
				new Object[] { html, portType });
		html.append(HtmlUtils.getAnchorTag(Constants.OPERATION_SUMMARY_HREF,
				null, null, null));
		html.append(getTableTagWithStyle("JavadocTable"));
		html.append(getTableRowTagWithStyle("JavadocTableTr"));
		html.append("<th colspan=\"2\" class=\"JavadocTableHeaders\">");
		html.append(Constants.OPERATION_SUMMARY_LABEL);
		html.append(Constants.HTML_TABLE_TH_END);
		html.append(Constants.HTML_TABLE_TR_END);
		html.append(getTableRowTagWithStyle("JavadocTableTr"));
		html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
		html.append(Constants.RETURN_TYPE_LABEL);
		html.append(Constants.HTML_TABLE_TH_END);

		html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
		html.append(Constants.METHOD_NAME_LABEL);
		html.append(Constants.HTML_TABLE_TH_END);
		html.append(Constants.HTML_TABLE_TR_END);
		for (OperationHolder opH : portType.getOperations()) {
			html.append(getTableRowTagWithStyle("JavadocTableTr"));
			html.append(getTableDataTagWithStyle("JavadocTableTd"));
			setOutputTypes(html, opH, wsdlDoc.getServiceName());
			html.append(Constants.HTML_TABLE_TD_END);
			html.append(getTableDataTagWithStyle("JavadocTableTd"));
			html.append(getTextInSpan(HtmlUtils.getAnchorTag(null, "#"
					+ opH.getName(), opH.getName(), opH.getName()),
					"javaDocMethodName")
					+ " (");
			setInputTypes(html, opH, wsdlDoc.getServiceName());

			html.append(")");
			html.append(Constants.HTML_BR);
			html.append(getSummary(wsdlDoc, opH));
			html.append(Constants.HTML_TABLE_TD_END);
			html.append(Constants.HTML_TABLE_TR_END);
		}
		html.append(Constants.HTML_TABLE_END);
		logger.exiting("JavaDocOutputGenerator", "buildOperationTable", html);
	}

	/**
	 * Sets the input types.
	 * 
	 * @param html
	 *            the html
	 * @param opH
	 *            the op h
	 * @param wsdlDoc
	 *            the wsdl doc
	 * @throws OutputFormatterException 
	 */
	private void setInputTypes(StringBuffer html, OperationHolder opH,
			WSDLDocInterface wsdlDoc) throws OutputFormatterException {
		logger.entering("JavaDocOutputGenerator", "setInputTypes",
				new Object[] { html, opH });
		List<Element> inputs = opH.getInputTypes();
		if (inputs != null) {
			Iterator<Element> iter = inputs.iterator();
			while (iter.hasNext()) {
				Element elem = iter.next();
				html.append(getTextInSpan(HtmlUtils.getAnchorTag(null, "types"
						+ SEPARATOR + elem.getType() + Constants.DOT_HTML,
						elem.getType(), elem.getType()), "javaDocMethodTypes"));
				html.append(" ");
				html
						.append(getTextInSpan(elem.getName(),
								"javaDocMethodTypes"));
				if (iter.hasNext()) {
					html.append(",");
				}
				ComplexType cType = wsdlDoc.searchCType(elem.getType());
				if (cType != null) {
					writeComplexTypeFile(wsdlDoc, cType, cType.getName());
				}
			}
		}
		logger.exiting("JavaDocOutputGenerator", "setInputTypes", html);
	}

	/**
	 * Sets the output types.
	 * 
	 * @param html
	 *            the html
	 * @param opH
	 *            the op h
	 * @param wsdlDoc
	 *            the wsdl doc
	 * @throws OutputFormatterException 
	 */
	private void setOutputTypes(StringBuffer html, OperationHolder opH,
			WSDLDocInterface wsdlDoc) throws OutputFormatterException {
		logger.entering("JavaDocOutputGenerator", "setOutputTypes",
				new Object[] { html, opH });
		StringBuffer rtBuf = new StringBuffer("");
		List<Element> inputs = opH.getOutputTypes();
		if (inputs != null) {
			Iterator<Element> iter = inputs.iterator();
			while (iter.hasNext()) {
				Element elem = iter.next();

				html.append(getTextInSpan(HtmlUtils.getAnchorTag(null, "types"
						+ SEPARATOR + elem.getType() + Constants.DOT_HTML,
						elem.getType(), elem.getType()), "javaDocMethodTypes"));
				if (iter.hasNext() && rtBuf.length() > 0) {
					html.append(",");
				}
				ComplexType cType = wsdlDoc.searchCType(elem.getType());
				if (cType != null) {
					writeComplexTypeFile(wsdlDoc, cType, cType.getName());
				}
			}
		}
		html.append(rtBuf);
		logger.exiting("JavaDocOutputGenerator", "setOutputTypes", html);
	}

	/**
	 * Sets the input types.
	 * 
	 * @param html
	 *            the html
	 * @param opH
	 *            the op h
	 * @param serviceName
	 *            the service name
	 */
	private void setInputTypes(StringBuffer html, OperationHolder opH,
			String serviceName) {
		logger.entering("JavaDocOutputGenerator", "setInputTypes",
				new Object[] { html, opH });
		List<Element> inputs = opH.getInputTypes();
		if (inputs != null) {
			Iterator<Element> iter = inputs.iterator();
			while (iter.hasNext()) {
				Element elem = iter.next();

				html.append(HtmlUtils.getAnchorTag(null, "types"
						+ SEPARATOR + elem.getType() + Constants.DOT_HTML,
						elem.getType(), elem.getType()));
				html.append(" ");
				html.append(elem.getName());
				if (iter.hasNext()) {
					html.append(",");
				}
			}
		}
		logger.exiting("JavaDocOutputGenerator", "setInputTypes", html);
	}

	/**
	 * Sets the output types.
	 * 
	 * @param html
	 *            the html
	 * @param opH
	 *            the op h
	 * @param serviceName
	 *            the service name
	 */
	private void setOutputTypes(StringBuffer html, OperationHolder opH,
			String serviceName) {
		logger.entering("JavaDocOutputGenerator", "setOutputTypes",
				new Object[] { html, opH });
		StringBuffer rtBuf = new StringBuffer("");
		List<Element> inputs = opH.getOutputTypes();
		if (inputs != null) {
			Iterator<Element> iter = inputs.iterator();
			while (iter.hasNext()) {
				Element elem = iter.next();

				html.append(HtmlUtils.getAnchorTag(null, "types"
						+ SEPARATOR + elem.getType() + Constants.DOT_HTML,
						elem.getType(), elem.getType()));
				if (iter.hasNext() && rtBuf.length() > 0) {
					html.append(",");
				}
			}
		}
		html.append(rtBuf);
		logger.exiting("JavaDocOutputGenerator", "setOutputTypes", html);
	}

	/**
	 * Builds the operation details.
	 * 
	 * @param html
	 *            the html
	 * @param portType
	 *            the port type
	 * @param serviceName
	 *            the service name
	 * @throws OutputFormatterException 
	 */
	private void buildOperationDetails(StringBuffer html, PortType portType,
			WSDLDocInterface doc) throws OutputFormatterException {
		logger.entering("JavaDocOutputGenerator", "buildOperationDetails",
				new Object[] { html, portType });
		html.append(HtmlUtils.getAnchorTag(Constants.OPERATIONS_DETAIL_HREF,
				null, null, null));

		html.append(getTableTagWithStyle("JavadocTable"));
		html.append(getTableRowTagWithStyle("JavadocTableTr"));
		html.append(getTableHeadTagWithStyle("JavadocTableHeaders"));
		html.append(Constants.OPERATION_DETAIL_LABEL);
		html.append(Constants.HTML_TABLE_TH_END);
		html.append(Constants.HTML_TABLE_TR_END);
		html.append(Constants.HTML_TABLE_END);
		for (OperationHolder opH : portType.getOperations()) {
			html
					.append(HtmlUtils.getAnchorTag(opH.getName(), null, null,
							null));
			html.append(getTextInDiv(opH.getName(), "javaDocInlineHeading"));

			html.append("<pre wrap='true'>");
			setOutputTypes(html, opH, doc);
			html.append(" " + getTextInSpan(opH.getName(), "javaDocOpName")
					+ "(");
			setInputTypes(html, opH, doc);

			html.append(")");
			html.append(Constants.HTML_PRE_END);

			html.append(Constants.HTML_DL_START + Constants.HTML_DD_START);
			appendOperationDocumentation(html, doc, opH);
			html.append(Constants.HTML_DD_END + Constants.HTML_DL_END);

			html.append(Constants.HTML_HR);
		}
		logger.exiting("JavaDocOutputGenerator", "buildOperationDetails",
				new Object[] { html, portType });
	}

	/**
	 * Gets the summary.
	 * 
	 * @param wsdlDoc
	 *            the wsdl doc
	 * @param holder
	 *            the holder
	 * @return the summary
	 */
	private String getSummary(WSDLDocInterface wsdlDoc, OperationHolder holder) {
		String summary = null;
		if (holder != null) {
			List<Element> inputs = holder.getInputTypes();
			for (Element elem : inputs) {
				String type = elem.getType();
				ComplexType ctype = wsdlDoc.searchCType(type);
				ParsedAnnotationInfo annotationInfo = ctype.getAnnotations();
				Map<String, List<ParsedAnnotationTag>> tagList = annotationInfo
						.getValue();
				if (tagList != null) {
					List<ParsedAnnotationTag> summaryTag = (List<ParsedAnnotationTag>) tagList
							.get("Summary");

					if (summaryTag != null) {
						summary = summaryTag.get(0).getTagValue();
						break;
					} else {
						summary = annotationInfo.getDocumentation();
					}
				}

			}
		}
		if (summary == null) {
			summary = "";
		}
		return summary;
	}

	/**
	 * Gets the operation documentation.
	 * 
	 * @param wsdlDoc
	 *            the wsdl doc
	 * @param holder
	 *            the holder
	 * @return the operation documentation
	 */
	private void appendOperationDocumentation(StringBuffer html,
			WSDLDocInterface wsdlDoc, OperationHolder holder) {
		if (holder != null) {
			String documentation=null;
			if(holder.getAnnotations()!=null){
				documentation = holder.getAnnotations().getDocumentation();
			}
			List<Element> inputs = holder.getInputTypes();
			for (Element elem : inputs) {
				String type = elem.getType();
				ComplexType ctype = wsdlDoc.searchCType(type);
				ParsedAnnotationInfo annotationInfo = ctype.getAnnotations();
				if (annotationInfo != null) {
					if (Utils.isEmpty(documentation)) {
						documentation = annotationInfo.getDocumentation();
						if (documentation != null) {
							html.append(getTextInSpan(documentation,
									"javaDocOpDetail"));
						}
					}
					StringBuffer deprDet = AnnotationsHelper
							.processDeprication(annotationInfo);
					if (deprDet != null) {
						html.append(getTextInDiv("Depricated: ",
								"javaDocInlineHeading"));
						html.append(deprDet);
					}
					processRelatedInfo(html, wsdlDoc, annotationInfo);

				}

			}
		}

	}
    private void processOccurances(StringBuffer html,ParsedAnnotationInfo anno){
    	if(anno!=null){
    		List<ParsedAnnotationTag> callInfos = AnnotationsHelper.getCallInfo(anno);
			Map<String,String[]> callReqRet=new HashMap<String, String[]>();
			if (callInfos != null) {
				for (ParsedAnnotationTag callInfo : callInfos) {
					String req=AnnotationsHelper.getFirstCallInfoTagValue(callInfo, "RequiredInput");
					String ret=AnnotationsHelper.getFirstCallInfoTagValue(callInfo, "Returned");
					String[] reqret=new String[2];
					reqret[0]=req==null?"---":req;
					reqret[1]=ret==null?"---":ret;
					if (callInfo!=null && callInfo.getChildren()!=null && callInfo.getChildren().get("AllCalls") != null) {
						
						callReqRet.put("For all calls", reqret);
					} else {
						String value = AnnotationsHelper
								.getFirstCallInfoTagValue(callInfo,
										"AllCallsExcept");
						if (!Utils.isEmpty(value)) {
							callReqRet.put("For all calls except" + value, reqret);
						}
					}
					List<ParsedAnnotationTag> calls = AnnotationsHelper
					.getCallInfoChildren(callInfo, "CallName");
					if (calls != null) {
						Iterator<ParsedAnnotationTag> callIter = calls
								.iterator();
						while (callIter.hasNext()) {
							ParsedAnnotationTag call = callIter.next();
							String callString =  call.getTagValue();
							callReqRet.put(callString, reqret);
						}
						
					}
				}
			}
			if(!callReqRet.isEmpty()){
				html.append(Constants.HTML_BR
						+ getTextInDiv("Occurances: ",
								"javaDocRelatedHeading"));
			
			html.append(Constants.HTML_TABLE_START_WITH_ATTRS.replaceAll("\\{#\\}", "class=\"" + "JavadocOccurancesTable"
					+ "\""));
			html.append(getTableRowTagWithStyle("JavadocOccurancTableTr"));
			html.append(getTableHeadTagWithStyle("JavadocOccurancTableHeaders"));
			html.append("Call Name");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(getTableHeadTagWithStyle("JavadocOccurancTableHeaders"));
			html.append("Required");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(getTableHeadTagWithStyle("JavadocOccurancTableHeaders"));
			html.append("Returned");
			html.append(Constants.HTML_TABLE_TH_END);
			html.append(Constants.HTML_TABLE_TR_END);
			for(Map.Entry<String, String[]> callsRet:callReqRet.entrySet()){
				html.append(getTableRowTagWithStyle("JavadocOccurancTableTr"));
				html.append(getTableDataTagWithStyle("JavadocOccurancTableTd"));
				html.append(callsRet.getKey());
				html.append(Constants.HTML_TABLE_TD_END);
				html.append(getTableDataTagWithStyle("JavadocOccurancTableTd"));
				html.append(callsRet.getValue()[0]);
				html.append(Constants.HTML_TABLE_TD_END);
				html.append(getTableDataTagWithStyle("JavadocOccurancTableTd"));
				html.append(callsRet.getValue()[1]);
				html.append(Constants.HTML_TABLE_TD_END);
				html.append(Constants.HTML_TABLE_TR_END);
			}
			html.append(Constants.HTML_TABLE_END);
			html.append(Constants.HTML_BR);
			}
    	}
    }
    
	private void processDefaultsAndBoundries(StringBuffer html,
			WSDLDocInterface doc, Element element) {
		if (element != null) {
			ParsedAnnotationInfo anno = element.getAnnotationInfo();
			String maxOccursApp = null;
			String minOccursApp = null;
			if (anno != null) {
				
				String defaultVal = AnnotationsHelper.getFirstAnnotationValue(
						anno, "Default");
				if (!Utils.isEmpty(defaultVal)) {
					html.append(Constants.HTML_BR
							+ getTextInDiv("Default Value: " + defaultVal,
									"javaDocRelatedHeading"));
				}
				String maxLength = AnnotationsHelper.getFirstAnnotationValue(
						anno, "MaxLength");
				if (!Utils.isEmpty(maxLength)) {
					html.append(Constants.HTML_BR
							+ getTextInDiv("Max Length: " + maxLength,
									"javaDocRelatedHeading"));
				}
				String max = AnnotationsHelper.getFirstAnnotationValue(anno,
						"Max");
				String min = AnnotationsHelper.getFirstAnnotationValue(anno,
						"Min");
				if (!Utils.isEmpty(max)) {
					html.append(Constants.HTML_BR
							+ getTextInDiv("Max Value: " + max,
									"javaDocRelatedHeading"));
				}
				if (!Utils.isEmpty(min)) {
					html.append(Constants.HTML_BR
							+ getTextInDiv("Min Value: " + max,
									"javaDocRelatedHeading"));
				}
				maxOccursApp = AnnotationsHelper.getFirstAnnotationValue(anno,
						"MaxOccurs");
				minOccursApp = AnnotationsHelper.getFirstAnnotationValue(anno,
						"MinOccurs");

			}
			String minOccurs = null;
			String maxOccurs = null;
			if (element.getAttributes() != null) {
				for (Attribute attr : element.getAttributes()) {
					if ("maxOccurs".equals(attr.getName())) {
						maxOccurs = attr.getValue();
					}
					if ("minOccurs".equals(attr.getName())) {
						minOccurs = attr.getValue();
					}
				}
			}
			if (maxOccurs == null) {
				maxOccurs = maxOccursApp;
			}
			if (minOccurs == null) {
				minOccurs = minOccursApp;
			}
			if (maxOccurs == null) {
				maxOccurs = "?";
			}
			if (minOccurs == null) {
				minOccurs = "?";
			}
			if("unbounded".equals(maxOccurs)){
				maxOccurs="*";
			}
			String repeatableString="";
			if(maxOccurs==minOccurs){
				repeatableString=Constants.HTML_BR+" Repeatable : ["+maxOccurs+"]";
			}else{
				repeatableString=Constants.HTML_BR+" Repeatable : ["+minOccurs+"..."+maxOccurs+"]";
			}
			html.append(getTextInDiv(repeatableString, "javaDocRelatedHeading"));
			if (anno != null) {
				List<ParsedAnnotationTag> callInfos = AnnotationsHelper
						.getCallInfo(anno);
				if (callInfos != null) {
					for (ParsedAnnotationTag callInfo : callInfos) {
						String maxOccursCall = AnnotationsHelper
								.getFirstCallInfoTagValue(callInfo, "MaxOccurs");
						String minOccursCall = AnnotationsHelper
								.getFirstCallInfoTagValue(callInfo, "MinOccurs");
						
						if (!Utils.isEmpty(minOccursCall)
								&& !Utils.isEmpty(maxOccursCall)) {
							if (minOccursCall == null) {
								minOccurs = "?";
							}
							if("unbounded".equals(maxOccursCall)){
								maxOccurs="*";
							}
							repeatableString="";
							if(maxOccurs==minOccurs){
								repeatableString=" Repeatable : ["+maxOccurs+"]";
							}else{
								repeatableString=" Repeatable : ["+minOccurs+"..."+maxOccurs+"]";
							}
							if (callInfo.getChildren().get("AllCalls") != null) {

								html.append(getTextInDiv(
										"For All Calls" +repeatableString,
										"javaDocRelatedHeading"));
							} else {
								String value = AnnotationsHelper
										.getFirstCallInfoTagValue(callInfo,
												"AllCallsExcept");
								if (!Utils.isEmpty(value)) {
									html.append(getTextInDiv(
											"For All Calls Except "+value+repeatableString,
											"javaDocRelatedHeading"));
								}
							}
							List<ParsedAnnotationTag> calls = AnnotationsHelper
									.getCallInfoChildren(callInfo, "CallName");
							if (calls != null) {
								String callString = "For Calls ";
								Iterator<ParsedAnnotationTag> callIter = calls
										.iterator();
								while (callIter.hasNext()) {
									ParsedAnnotationTag call = callIter.next();
									callString = callString
											+ call.getTagValue();
									if (callIter.hasNext()) {
										callString = callString + ",";
									}
								}
								html.append(getTextInDiv(callString
										+ repeatableString,
										"javaDocRelatedHeading"));
							}
						}
					}
				}
			}
			

		}
		
	}

	/**
	 * Process related info.
	 */
	private void processRelatedInfo(StringBuffer html, WSDLDocInterface doc,
			ParsedAnnotationInfo appInfoElem) {
		processOccurances(html,appInfoElem);
		processSeeLinks(html, appInfoElem);
		// process any RelatedCalls block
		String relCallsElem = AnnotationsHelper.getFirstAnnotationValue(
				appInfoElem, "RelatedCalls");
		List<String> relatedCalls = null;
		if (relCallsElem != null) {
			relatedCalls = Utils.commaSepLineToList(relCallsElem);
		}
		// form the Related Calls material, but do not yet add it
		StringBuffer relCallsSB = new StringBuffer();
		if (relatedCalls != null && relatedCalls.size() > 0) {
			relCallsSB
					.append("See also the documentation for these calls:<ul>");
			for (int i = 0; i < relatedCalls.size(); i++) {
				String otherCall = relatedCalls.get(i);
				relCallsSB.append("<li>" + otherCall + "</li>");
			}
			relCallsSB.append("</ul>");
		}
		List<ParsedAnnotationTag> relCallsOtherElems = AnnotationsHelper
				.getAnnotationTag(appInfoElem, "RelatedCallsOther");
		if (relCallsOtherElems != null) {
			// for each RelatedCallsOther block that was found...
			for (int j = 0; j < relCallsOtherElems.size(); j++) {
				ParsedAnnotationTag relCallsOtherElem = relCallsOtherElems
						.get(j);

				// it should identify the apiNickname of the other service that
				// has
				// calls
				// related to this call

				String otherApi = AnnotationsHelper.getSingleAnnoChildVal(
						relCallsOtherElem, "ApiNickname");

				String otherApiURL = AnnotationsHelper.getSingleAnnoChildVal(
						relCallsOtherElem, "ApiCallRefBaseURL");

				List<String[]> relCalls = new ArrayList<String[]>();

				// it should have a list of names of related calls
				List<ParsedAnnotationTag> relCallBlocks = relCallsOtherElem
						.getChildren().get("RelatedCall");

				for (int k = 0; k < relCallBlocks.size(); k++) {
					ParsedAnnotationTag relCallElem = relCallBlocks.get(k);

					String callNameOther = AnnotationsHelper
							.getSingleAnnoChildVal(relCallElem, "Name");

					String summOther = AnnotationsHelper.getSingleAnnoChildVal(
							relCallElem, "Summary");

					// form an array of these two
					relCalls.add(new String[] { callNameOther, summOther });
					relCallsSB
							.append("See also the documentation for these calls (in the "
									+ "<a href=\""
									+ otherApiURL
									+ "/index.html\">"
									+ otherApi
									+ "</a> API ):" + "<ul>");

					relCallsSB.append("<li>" + "<a href=\"" + otherApiURL + "/"
							+ callNameOther + ".html\">" + callNameOther
							+ "</a> - " + summOther + "</li>");

					relCallsSB.append("</ul>");
				}

			}
		}

		// if there is either See Also material OR Related Calls info,
		// inject a heading, then add the material
		if (!relCallsSB.toString().equals("")) {
			html.append(Constants.HTML_BR
					+ Constants.HTML_BR
					+ getTextInDiv("Related Information: ",
							"javaDocRelatedHeading"));
			if (!relCallsSB.toString().equals("")) {
				html.append(relCallsSB.toString());
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.tools.annoparser.outputformatter.DocHandler
	 * #handleXsdDoc
	 * (org.ebayopensource.turmeric.tools.annoparser.XSDDocInterface,
	 * java.lang.String)
	 */

	public void generateXsdOutput(XSDDocInterface xsdDoc,
			OutputGenaratorParam outputGenaratorParam) {
		// Java Doc is not available for XSD
		return;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ebayopensource.turmeric.tools.annoparser.outputformatter.OutputGenerator
	 * #completeProcessing()
	 */
	public void completeProcessing() throws OutputFormatterException {
		if (packageServicesMap != null && !packageServicesMap.isEmpty()) {

			createAllPackagesFile();
			createAllPackagesDescriptionFile();
			createIndividualPackageFiles();
			createAllClassesFile();
			createIndexFile();
		}
	}

	/**
	 * Gets the current output dir.
	 * 
	 * @return the current output dir
	 */
	private String getCurrentOutputDir() {
		String outputDir = Context.getContext().getOutputDir();
		if (outputGenaratorParam != null
				&& outputGenaratorParam.getOutputDir() != null) {
			outputDir = outputGenaratorParam.getOutputDir();
		}
		return outputDir + File.separator + "wsdlApiSpecDocs" + File.separator;
	}
}
