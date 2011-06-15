package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;

/**
 * The Class IndexerOperationHolder.
 */
public class IndexerOperationHolder  extends IndexerBaseDataObject{
	private OperationHolder operation;

	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public OperationHolder getOperation() {
		return operation;
	}

	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	public void setOperation(OperationHolder operation) {
		super.setBaseName(operation.getName());
		this.operation = operation;
	}

}
