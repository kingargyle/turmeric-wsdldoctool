package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

/**
 * The Class Comment.
 */
public class Comment {
	private String previousComment;

	private String nextComment;

	/**
	 * Gets the previous comment.
	 *
	 * @return the previous comment
	 */
	public String getPreviousComment() {
		return previousComment;
	}

	/**
	 * Sets the previous comment.
	 *
	 * @param previousComment the new previous comment
	 */
	public void setPreviousComment(String previousComment) {
		this.previousComment = previousComment;
	}

	/**
	 * Gets the next comment.
	 *
	 * @return the next comment
	 */
	public String getNextComment() {
		return nextComment;
	}

	/**
	 * Sets the next comment.
	 *
	 * @param nextComment the new next comment
	 */
	public void setNextComment(String nextComment) {
		this.nextComment = nextComment;
	}

}
