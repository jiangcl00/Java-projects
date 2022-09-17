package model;

import java.util.ArrayList;

public class ListElement extends TagElement {
	private ArrayList<Element> list = new ArrayList<Element>();

	public ListElement(boolean ordered, java.lang.String attributes) {
		super(checkOrdered(ordered), true, null, attributes);
	}

	// identify tag name by checking ordered
	private static String checkOrdered(boolean ordered) {
		String tag;

		if (ordered) {
			tag = "ol";
		} else {
			tag = "ul";
		}
		return tag;
	}

	public void addItem(Element item) {
		list.add(item);
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		String items = "";
		String html = "";

		html += indent + getStartTag();

		for (Element element : list) {
			items += indent + Utilities.spaces(3) + "<li>" + "\n";
			items += indent + Utilities.spaces(3) + element.genHTML(3) + "\n";
			items += indent + Utilities.spaces(3) + "</li>" + "\n";
		}
		html += "\n" + items + indent + getEndTag();

		return html;
	}
}
