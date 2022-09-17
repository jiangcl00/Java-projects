package model;

public class AnchorElement extends TagElement {
	private java.lang.String url;
	private java.lang.String linkText;

	public AnchorElement(java.lang.String url, java.lang.String linkText,
			java.lang.String attributes) {
		super("a", true, null, checkNull(attributes, url));
		this.url = url;
		this.linkText = linkText;
	}

	// identify attributes by checking if attributes is null
	private static String checkNull(String attributes, String url) {
		if (attributes == null) {
			return "href=\"" + url + "\"";
		} else {
			return "href=\"" + url + "\"" + attributes;
		}
	}

	public java.lang.String getLinkText() {
		return linkText;
	}

	public java.lang.String getUrlText() {
		return url;
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		
		return indent + getStartTag() + linkText + getEndTag();
	}
}
