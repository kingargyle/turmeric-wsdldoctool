package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;

public class IndexerElementHolder extends IndexerBaseDataObject{
	private Element element;
	private boolean isReqResp;
	
	private IndexerOperationHolder operationHolder;
	private boolean isInput;

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
		super.setBaseName(element.getName());
	}

	public boolean isReqResp() {
		return isReqResp;
	}

	public void setReqResp(boolean isReqResp) {
		this.isReqResp = isReqResp;
	}

	public IndexerOperationHolder getOperationHolder() {
		return operationHolder;
	}

	public void setOperationHolder(IndexerOperationHolder operationHolder) {
		this.operationHolder = operationHolder;
	}

		public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	

}
