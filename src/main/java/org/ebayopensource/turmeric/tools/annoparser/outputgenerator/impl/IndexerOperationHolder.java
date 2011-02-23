package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.OperationHolder;

public class IndexerOperationHolder  extends IndexerBaseDataObject{
	private OperationHolder operation;

	public OperationHolder getOperation() {
		return operation;
	}

	public void setOperation(OperationHolder operation) {
		super.setBaseName(operation.getName());
		this.operation = operation;
	}

}
