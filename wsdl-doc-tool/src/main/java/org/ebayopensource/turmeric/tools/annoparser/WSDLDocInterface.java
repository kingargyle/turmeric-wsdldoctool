/*
 * 
 */
package org.ebayopensource.turmeric.tools.annoparser;

import java.util.List;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationInfo;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.PortType;

/**
 * The implementation WSDLDocInterface must hold data from the parsed wsdl document,
 * as required by the output formatters.
 *
 * @author srengarajan
 */
public interface WSDLDocInterface extends XSDDocInterface {

	/**
	 * Gets the all wsdl operations.
	 *
	 * @return the all operations
	 */
	public List<OperationHolder> getAllOperations();
	
	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName();
	
	

	
	/**
	 * Gets all the port types defined in WSDL.
	 *
	 * @return the port types
	 */
	public List<PortType> getPortTypes();
	
	/**
	 * Gets the package name of the WSDL.
	 * Package name is the local part of the WSDL Service URL.
	 * @return the package name
	 */
	public String getPackageName();
	
	/**
	 * Gets the complete remote address of the service endpoint.
	 *
	 * @return the complete remote path
	 */
	public String getCompleteRemotePath();
	
	/**
	 * Gets the annotations on the WSDL.
	 * Typically returns the annotation defined on the service element.
	 * @return the annotations
	 */
	public ParsedAnnotationInfo getAnnotations();
	

}
