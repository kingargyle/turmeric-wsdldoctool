package org.ebayopensource.turmeric.tools.annoparser.utils;

import java.util.Set;

/**
 * The Class Node.
 */
public class Node implements Comparable<Node> {
	private String name;
	
	private Node parent;
	
	private Set<Node> children;
	
	private String originalParent;
	
	private boolean flag = true;
	
	private boolean nodeAdded = false;

	private int level;
	
	
	/**
	 * Checks if is node added.
	 *
	 * @return the nodeAdded
	 */
	public boolean isNodeAdded() {
		return nodeAdded;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Sets the node added.
	 *
	 * @param nodeAdded the nodeAdded to set
	 */
	public void setNodeAdded(boolean nodeAdded) {
		this.nodeAdded = nodeAdded;
	}

	/**
	 * Checks if is flag.
	 *
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * Gets the original parent.
	 *
	 * @return the originalParent
	 */
	public String getOriginalParent() {
		return originalParent;
	}

	/**
	 * Sets the original parent.
	 *
	 * @param originalParent the originalParent to set
	 */
	public void setOriginalParent(String originalParent) {
		this.originalParent = originalParent;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the parent to set
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public Set<Node> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the children to set
	 */
	public void setChildren(Set<Node> children) {
		this.children = children;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Node o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
