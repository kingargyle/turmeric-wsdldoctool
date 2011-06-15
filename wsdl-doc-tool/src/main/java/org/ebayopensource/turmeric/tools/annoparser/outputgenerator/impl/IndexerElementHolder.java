package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.Element;

/**
 * The Class IndexerElementHolder.
 */
public class IndexerElementHolder extends IndexerBaseDataObject{
	private Element element;
	private boolean isReqResp;
	
	private IndexerOperationHolder operationHolder;
	private boolean isInput;

	/**
	 * Gets the element.
	 *
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * Sets the element.
	 *
	 * @param element the new element
	 */
	public void setElement(Element element) {
		this.element = element;
		super.setBaseName(element.getName());
	}

	/**
	 * Checks if is req resp.
	 *
	 * @return true, if is req resp
	 */
	public boolean isReqResp() {
		return isReqResp;
	}

	/**
	 * Sets the req resp.
	 *
	 * @param isReqResp the new req resp
	 */
	public void setReqResp(boolean isReqResp) {
		this.isReqResp = isReqResp;
	}

	/**
	 * Gets the operation holder.
	 *
	 * @return the operation holder
	 */
	public IndexerOperationHolder getOperationHolder() {
		return operationHolder;
	}

	/**
	 * Sets the operation holder.
	 *
	 * @param operationHolder the new operation holder
	 */
	public void setOperationHolder(IndexerOperationHolder operationHolder) {
		this.operationHolder = operationHolder;
	}

		/**
		 * Checks if is input.
		 *
		 * @return true, if is input
		 */
		public boolean isInput() {
		return isInput;
	}

	/**
	 * Sets the input.
	 *
	 * @param isInput the new input
	 */
	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	

}
