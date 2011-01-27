/*
 * 
 */
package org.ebayopensource.turmeric.tools.annoparser;

import java.util.List;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.ParsedAnnotationInfo;
import org.ebayopensource.turmeric.tools.annoparser.dataobjects.PortType;

/**
 * The Interface WSDLDocInterface.
 *
 * @author srengarajan
 */
public interface WSDLDocInterface extends XSDDocInterface {

	/**
	 * Gets the all operations.
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
	 * Gets the port types.
	 *
	 * @return the port types
	 */
	public List<PortType> getPortTypes();
	
	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName();
	
	/**
	 * Gets the complete remote path.
	 *
	 * @return the complete remote path
	 */
	public String getCompleteRemotePath();
	
	/**
	 * Gets the annotations.
	 *
	 * @return the annotations
	 */
	public ParsedAnnotationInfo getAnnotations();
	

}
