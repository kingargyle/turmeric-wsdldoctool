/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.tools.annoparser.commons;


/**
 * The Interface Constants.
 *
 * @author goraman
 */
public interface Constants {

	 String DOT_HTML = ".html";
	
	 String XSD_COMPLEX_TYPE = "xsd:complexType";
	
	 String XS_COMPLEX_TYPE = "xs:complexType";
	
	 String XSD_SIMPLE_TYPE = "xs:simpleType";
	
	 String XS_SIMPLE_TYPE = "xs:simpleType";
	
	 String XSD_ENUMERATION = "xsd:enumeration";
	
	 String XS_ENUMERATION = "xs:enumeration";
	
	 String XSD_ELEMENT = "xsd:element";
	
	 String XS_ELEMENT = "xs:element";
	
	 String XSD_DOCUMENTATION = "xs:documentation";
	
	 String XS_DOCUMENTATION = "xs:documentation";

	 String SUPPORT = "Support";
	 
	 String CLASSES = "Services";
	 
	 String CLASSFRAME = "classframe";
	 
	 String ALL_CLASSES = "All Services";
	 
	 String ALLCLASSES = "allclasses";
	 
	 String PACKAGES = "Service Domains";
	 
	 String PACKAGES_INDEX = "packagesindex";
	 
	 String CLASSESFRAME = "classesframe";
	 
	 String INDEX = "index";
	 
	 String FIELDS = "Fields";
	
	 String SUPPORT_URL = "http://developer.ebay.com/support/";
	
	 String KNOWLEDGEB_BASE = "Knowledge Base";
	
	 String KNOWLEDGEB_BASE_URL = "http://developer.ebay.com/DevZone/support/knowledgebase/";
	
	 String FORUMS = "Forums";
	
	 String FORUMS_URL = "http://developer.ebay.com/community/forums/";
	
	 String ALL_TUTORIALS = "All Tutorials";
	
	 String ALL_TUTORIALS_URL = "http://developer.ebay.com/support/docs/tutorials/";
	
	 String ALL_CODE_SAMPLES = "All Code Samples";	
	
	 String ALL_CODE_SAMPLES_URL = "http://developer.ebay.com/community/featured_projects/?name=Code+Samples+Project";
	
	 String API_REFERENCE_DOCS = "apireferencedocs";
	
	 String API_REFERENCE_DOCS_URL = "http://developer.ebay.com/community/codebase/apireferencedocs/default.aspx"; 
	
	 String SERVICE_NAME = "<font id='serviceName'>{#} API</font>";
	
	 String CALL_REF_VERSION = "<font id='callRefVersion'>Call Reference Version {#}</font>";
	
	 String COPYRIGHTS = "© 2010 eBay, Inc. All rights reserved. This documentation and the API may only be used in accordance with the eBay Developers Program and API License Agreement.";
	
	 String DOC_TOOL_FOOTER_INFO = "This document was generated with a customized version of the {#} tool.";
	
	 String ADDITIONAL_DESCRIPTION = "Additional resources are available for this eBay Web Service. Please see the";
	
	 String ADDITIONAL_DESCRIPTION_URL_NAME = "eBay Developer Documentation Center";
	
	 String ADDITIONAL_DESCRIPTION_URL_LINK = "http://developer.ebay.com/support/docs/";	
	
	 String TABLE_OF_CONTENTS = "Table of Contents";
	
	 String TYPE_INDEX_FILE_NAME = "index";
	
	 String TYPE_INDEX = "Type Index";
	
	 String TYPE_INDEX_DESCRIPTION = "This is a list of all types in the schema, types upon which fields are based. You can learn more about how different calls and different types make use of these types.";
	
	 String TYPE_INDEX_TITLE = "All types upon which schema fields are based";
	
	 String FIELD_INDEX_FILE_NAME = "fieldindex";
	
	 String FIELD_INDEX = "Field Index";
	
	 String FIELD_INDEX_DESCRIPTION = "This is a list of all fields in the schema, by name. The same field name may be used by several calls. Note that the underlying type of the field may or may not be the same.";
	
	 String FIELD_INDEX_TITLE = "All fields in the schema";
	
	 String FIELD_FIELD_INDEX_FILE_NAME = "fieldfieldindex";
	
	 String FIELD_FIELD_INDEX = "Field Field Index";
	
	 String FIELD_FIELD_INDEX_DESCRIPTION = "This is a list of all unique field names in the schema";
	
	 String FIELD_FIELD_INDEX_DESCRIPTION_2 = "The benefit of this page over the regular Field Index is that this page is good for searches with Ctrl-F, for example, to help you find each field whose name includes the word Feedback. Note that each field name in this page is a link to the corresponding name in the Field Index (so that you can see in what ways each field name is used).";
	
	 String ENUMERATION_INDEX_FILE_NAME = "enumindex";
	
	 String ENUMERATION_INDEX = "Enumeration Index";
	
	 String ENUMERATION_INDEX_DESCRIPTION = "This is a list of all enumeration values in the schema. The same enumeration value name may be used by several calls.";
	
	 String ENUMERATION_INDEX_TITLE = "All enumeration values in the schema";

	 String SIMPLE_TYPE_NOTE = " * See the {#} to see exact use of each enumeration value in the API. ";
	
	 String LIST_OF_ALL_CALLS_INDEX_FILE_NAME = "index";
	
	 String LIST_OF_ALL_CALLS_INDEX = "List of All Calls";
	
	 String LIST_OF_ALL_CALLS_INDEX_DESCRIPTION = "This Call Reference describes the elements and attributes for each call in the BestMatchItemDetailsInternal API.";
	
	 String LIST_OF_ALL_CALLS_INDEX_TITLE = "List of all available calls in this schema";
	
	 String CALL_INPUT_TABLE_DESCRIPTION = "The box below lists all fields that could be included in the call request. To learn more about an individual field or its type, click its name in the box (or scroll down to find it in the table below the box).";
	 
	 String CALL_OUTPUT_TABLE_DESCRIPTION = "The box below lists all fields that might be returned in the response. To learn more about an individual field or its type, click its name in the box (or scroll down to find it in the table below the box).";
	

	 String HTML_BR = "<br/>";
	 String HTML_BR_TWICE=HTML_BR+HTML_BR;
	 String HTML_HR = "<hr/>";

	 String HTML_TABLE_START_WITH_ATTRS = "<table {#}>";
	 String HTML_TABLE_END = "</table>";
	 String HTML_TABLE_TR_START_WITH_ATTRS = "<tr {#}>";
	 String HTML_TABLE_TR_END = "</tr>";
	 String HTML_TABLE_TH_START_WITH_ATTRS = "<th {#}>";
	 String HTML_TABLE_TH_END = "</th>";
	 String HTML_TABLE_TD_START_WITH_ATTRS = "<td {#}>";
	 String HTML_TABLE_TD_END = "</td>";
	 String HTML_DIV_START = "<div {#}>";
	 String HTML_DIV_END = "</div>";
	 String HTML_SPAN_START = "<span {#}>";
	 String HTML_SPAN_END = "</span>";
	 String HTML_P_START = "<p>";	 
	 String HTML_P_END = "</p>";
	 
	 String COMPLEX_TYPE_HEADER = "ComplexTypeHeader";
	 String SIMPLE_TYPE_HEADER = "SimpleTypeHeader";
	 String OPERATION_HEADER = "operationHeader";
	 String TREE_HEADER = "TreeHeader";
	 String USE_HEADER = "UseHeader";
	 
	 String NBSP = "&nbsp;";
	 
	 String NBSP_TWICE = "&nbsp;&nbsp;";
	 
	 String NBSP_THRICE = "&nbsp;&nbsp;&nbsp;";
	 
	 String INPUT_DOCUMENT="documents";
	
	 String INPUT_OUTPUT_DIR="output_dir";
	
	 String INPUT_CONFIG="config";
	
	 String INPUT_CSS="css";	
	
	 String INPUT_SYSENV_OUTPUT_DIR="ANNOPARSER_OUTPUT_DIR";
	
	 String INPUT_SYSENV_CONFIG="ANNOPARSER_CONFIG";
	
	 String INPUT_SYSENV_CSS="ANNOPARSER_CSS";	
	
	String OPERATION_SUMMARY_HREF="OperationSummary";
	
	String OPERATION_SUMMARY_LABEL="Operation Summary";
	
	String OPERATIONS_LABEL="Operations";
	String OPERATIONS_DETAIL_HREF="OperationDetail";
	String OPERATION_DETAIL_LABEL="Operation Detail";
	
	String HTML_H2_START="<h2>";
	
	String HTML_H3_START="<h3>";
	
	String HTML_H3_END="</h3>";
	
	String SERVICE_LABEL="Service: ";
	
	String HTML_H2_END="</h2>";
	
	String HTML_DL_START="<dl>";
	
	String HTML_DD_START="<dd class=\"JavadocDocumentationTxt\">";
	
	String HTML_DL_END="</dl>";
	
	String HTML_DD_END="</dd>";
	
	String RETURN_TYPE_LABEL="Return Type";
	
	String METHOD_NAME_LABEL="Method Name";
	
	String HTML_PRE_START="<pre>";
	
	String HTML_PRE_END="</pre>";
	
    String HTML_BOLD_START="<b>";
	
	String HTML_BOLD_END="</b>";
	
	

	
	String COMMAND_LINE_HELP_ARG="help";
	
	 String CSS_STYLE_SUB_HEADING = "subHeading";
	
}
