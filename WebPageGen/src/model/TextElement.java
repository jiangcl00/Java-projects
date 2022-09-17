package model;

public class TextElement extends java.lang.Object implements Element {
	private java.lang.String text = "";

	public TextElement(java.lang.String text) {
		this.text = text;
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		
		return indent + text;
	}
}
