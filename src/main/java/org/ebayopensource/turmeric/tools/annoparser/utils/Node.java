package org.ebayopensource.turmeric.tools.annoparser.utils;

import java.util.Set;

public class Node implements Comparable<Node> {
	private String name;
	
	private Node parent;
	
	private Set<Node> children;
	
	private String originalParent;
	
	private boolean flag = true;
	
	private boolean nodeAdded = false;

	private int level;
	
	
	/**
	 * @return the nodeAdded
	 */
	public boolean isNodeAdded() {
		return nodeAdded;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param nodeAdded the nodeAdded to set
	 */
	public void setNodeAdded(boolean nodeAdded) {
		this.nodeAdded = nodeAdded;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the originalParent
	 */
	public String getOriginalParent() {
		return originalParent;
	}

	/**
	 * @param originalParent the originalParent to set
	 */
	public void setOriginalParent(String originalParent) {
		this.originalParent = originalParent;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public Set<Node> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<Node> children) {
		this.children = children;
	}

	@Override
	public int compareTo(Node o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
