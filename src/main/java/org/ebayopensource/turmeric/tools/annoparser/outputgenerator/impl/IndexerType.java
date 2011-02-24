package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.AbstractType;

public class IndexerType extends IndexerBaseDataObject{

	private AbstractType type;

	public AbstractType getType() {
		return type;
	}

	public void setType(AbstractType type) {
		if(type!=null){
			super.setBaseName(type.getName());
		}
		this.type = type;
	}


	

	
}
