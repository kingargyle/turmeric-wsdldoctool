package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.EnumElement;

public class IndexerEnumValueElements extends IndexerBaseDataObject{
	private String value;

	private EnumElement enumElem;
	public String getValue() {
		
		return value;
	}

	public void setValue(String value) {
		super.setBaseName(value);
		this.value = value;
	}

	public EnumElement getEnumElem() {
		return enumElem;
	}

	public void setEnumElem(EnumElement enumElem) {
		this.enumElem = enumElem;
	}

	
}
