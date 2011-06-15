package org.ebayopensource.turmeric.tools.annoparser.dataobjects;

public class Comment {
	private String previousComment;

	private String nextComment;

	public String getPreviousComment() {
		return previousComment;
	}

	public void setPreviousComment(String previousComment) {
		this.previousComment = previousComment;
	}

	public String getNextComment() {
		return nextComment;
	}

	public void setNextComment(String nextComment) {
		this.nextComment = nextComment;
	}

}
