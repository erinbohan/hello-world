package com.frankc.hellomongo.shortlink;

public class ShortLink {

	private String id;
	private String srcLink;

	public ShortLink(String srcLink) {
		this.setSrcLink(srcLink);
	}

	public ShortLink(String id, String srcLink) {
		this.setId(id);
		this.setSrcLink(srcLink);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSrcLink() {
		return srcLink;
	}

	public void setSrcLink(String srcLink) {
		this.srcLink = srcLink;
	}
}