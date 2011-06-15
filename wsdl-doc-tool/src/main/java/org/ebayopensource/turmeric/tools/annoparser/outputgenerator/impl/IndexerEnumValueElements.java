package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;

/**
 * The Class IndexerEnumValueElements.
 */
public class IndexerEnumValueElements extends IndexerBaseDataObject{
	private String value;

	private EnumElement enumElem;
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		super.setBaseName(value);
		this.value = value;
	}

	/**
	 * Gets the enum elem.
	 *
	 * @return the enum elem
	 */
	public EnumElement getEnumElem() {
		return enumElem;
	}

	/**
	 * Sets the enum elem.
	 *
	 * @param enumElem the new enum elem
	 */
	public void setEnumElem(EnumElement enumElem) {
		this.enumElem = enumElem;
	}

	
}
