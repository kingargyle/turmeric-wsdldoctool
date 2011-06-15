package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class IndexerDataObject.
 */
public class IndexerDataObject {
	private List<IndexerBaseDataObject> dataObjects=new ArrayList<IndexerBaseDataObject>();

	/**
	 * Gets the data objects.
	 *
	 * @return the data objects
	 */
	public List<IndexerBaseDataObject> getDataObjects() {
		return dataObjects;
	}
	
	/**
	 * Adds the data objects.
	 *
	 * @param dataObject the data object
	 */
	public void addDataObjects(IndexerBaseDataObject dataObject) {
		dataObjects.add(dataObject);
	}
	
	/**
	 * Sets the data objects.
	 *
	 * @param dataObjects the new data objects
	 */
	public void setDataObjects(List<IndexerBaseDataObject> dataObjects) {
		this.dataObjects = dataObjects;
	}
	
}
