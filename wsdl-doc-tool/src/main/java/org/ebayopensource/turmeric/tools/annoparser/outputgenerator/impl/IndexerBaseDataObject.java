package org.ebayopensource.turmeric.tools.annoparser.outputgenerator.impl;

/**
 * The Class IndexerBaseDataObject.
 */
public class IndexerBaseDataObject implements Comparable<IndexerBaseDataObject>{
	private String packageName;
	private String serviceName;
	private String baseName;

	/**
	 * Gets the package name.
	 *
	 * @return the package name
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * Sets the package name.
	 *
	 * @param packageName the new package name
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Gets the service name.
	 *
	 * @return the service name
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName the new service name
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Gets the base name.
	 *
	 * @return the base name
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
	 * Sets the base name.
	 *
	 * @param baseName the new base name
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IndexerBaseDataObject o) {
		return baseName.compareTo(o.getBaseName());
	}
}
