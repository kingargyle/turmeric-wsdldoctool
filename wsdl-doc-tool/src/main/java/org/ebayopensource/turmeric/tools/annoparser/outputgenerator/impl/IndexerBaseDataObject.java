package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

public class IndexerBaseDataObject implements Comparable<IndexerBaseDataObject>{
	private String packageName;
	private String serviceName;
	private String baseName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	@Override
	public int compareTo(IndexerBaseDataObject o) {
		return baseName.compareTo(o.getBaseName());
	}
}
