package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

import org.ebayopensource.turmeric.tools.annoparser.dataobjects.AbstractType;

/**
 * The Class IndexerType.
 */
public class IndexerType extends IndexerBaseDataObject{

	private AbstractType type;

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public AbstractType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(AbstractType type) {
		if(type!=null){
			super.setBaseName(type.getName());
		}
		this.type = type;
	}


	

	
}
