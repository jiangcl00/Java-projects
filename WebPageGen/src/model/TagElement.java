package model;

public class TagElement extends java.lang.Object implements Element {
	private String tagName = "p";
	private boolean endTagBoolean = true;
	private Element content;
	private String attributes = "";
	private static int id = 1;
	private static boolean idBoolean = true;

	public TagElement(java.lang.String tagName, boolean endTag, Element content,
			java.lang.String attributes) {
		this.tagName = tagName;
		this.endTagBoolean = endTag;
		this.content = content;
		this.attributes = attributes;
	}

	public int getId() {
		id++;
		return id - 1;
	}

	public java.lang.String getStringId() {
		return tagName + getId();
	}

	public java.lang.String getStartTag() {
		if (attributes == null) {
			if (idBoolean) {
				return "<" + tagName + " id=\"" + getStringId() + "\"" + ">";
			} else {
				return "<" + tagName + ">";
			}
		} else {
			if (idBoolean) {
				return "<" + tagName + " id=\"" + getStringId() + "\" "
						+ attributes + ">";
			} else {
				return "<" + tagName + " " + attributes + ">";
			}
		}

	}

	public java.lang.String getEndTag() {
		if (endTagBoolean) {
			return "</" + tagName + ">";
		} else {
			return "";
		}

	}

	public void setAttributes(java.lang.String attributes) {
		this.attributes = attributes;
	}

	public static void resetIds() {
		id = 1;
	}

	public static void enableId(boolean choice) {
		idBoolean = choice;
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);

		if (endTagBoolean) {
			return indent + getStartTag() + content.genHTML(0) + getEndTag();
		} else {
			return indent + getStartTag();
		}

	}

}
