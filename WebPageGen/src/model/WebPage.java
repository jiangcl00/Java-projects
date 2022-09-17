package model;

import java.util.ArrayList;

public class WebPage extends java.lang.Object
		implements java.lang.Comparable<WebPage> {
	private java.lang.String title;
	private ArrayList<Element> page;

	public WebPage(java.lang.String title) {
		this.title = title;
		page = new ArrayList<Element>();

	}

	public int addElement(Element element) {
		page.add(element);

		if (element instanceof TagElement) {
			return ((TagElement) element).getId();
		} else {
			return -1;
		}
	}

	public java.lang.String getWebPageHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		String html = "";

		html += "<!doctype html>" + "\n" + "<html>" + "\n" + indent + "<head>"
				+ "\n" + indent + "   <meta charset=\"utf-8\"/>" + "\n" + indent
				+ "   <title>" + title + "</title>" + "\n" + indent + "</head>"
				+ "\n" + indent + "<body>" + "\n";

		for (Element element : page) {
			html += element.genHTML(indentation) + "\n";

		}

		html += indent + "</body>" + "\n" + "</html>";

		return html;
	}

	public void writeToFile(java.lang.String filename, int indentation) {
		writeToFile(filename, indentation);
	}

	public Element findElem(int id) {
		for (Element element : page) {
			if (element instanceof Element
					&& ((TagElement) element).getId() == id) {
				return element;
			}
		}
		return null;
	}

	public java.lang.String stats() {
		int listCount = 0;
		int paragraghCount = 0;
		int tableCount = 0;
		double tableUtil = 0.0;
		String stat = "";

		for (Element element : page) {
			if (element instanceof ListElement) {
				listCount++;
			} else if (element instanceof ParagraphElement) {
				paragraghCount++;
			} else if (element instanceof TableElement) {
				tableCount++;
				tableUtil += ((TableElement) element).getTableUtilization();
			}
		}
		tableUtil = tableUtil / tableCount;

		stat += "List Count: " + listCount + "\n" + "Paragraph Count: "
				+ paragraghCount + "\n" + "Table Count: " + tableCount + "\n"
				+ "TableElement Utilization: " + tableUtil;

		return stat;
	}

	public int compareTo(WebPage webPage) {
		if ((this.title.compareTo(webPage.title)) < 0) {
			return -1;
		} else if ((this.title.compareTo(webPage.title)) == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}
}
