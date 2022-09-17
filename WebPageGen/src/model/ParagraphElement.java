package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {
	private ArrayList<Element> paragraph = new ArrayList<Element>();

	public ParagraphElement(java.lang.String attributes) {
		super("p", true, null, attributes);
	}

	public void addItem(Element item) {
		paragraph.add(item);
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		String items = "";
		String html = "";

		html += indent + getStartTag();

		for (Element element : paragraph) {
			items += indent + element.genHTML(3) + "\n";
		}
		html += "\n" + items + indent + getEndTag();

		return html;
	}
}
