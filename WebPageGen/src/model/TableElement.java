package model;

public class TableElement extends TagElement {
	private Element[][] table;

	public TableElement(int rows, int cols, java.lang.String attributes) {
		super("table", true, null, attributes);
		table = new Element[rows][cols];
	}

	public void addItem(int rowIndex, int colIndex, Element item) {
		table[rowIndex][colIndex] = item;
	}

	public double getTableUtilization() {
		int total = 0;
		int used = 0;
		double percentage;
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				total++;
				if (table[row][col] != null) {
					used++;
				}
			}
		}
		percentage = ((double) used) / ((double) total) * 100;

		return percentage;
	}

	public java.lang.String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		String items = "";
		String html = "";

		html += indent + getStartTag() + "\n";

		for (int row = 0; row < table.length; row++) {
			items += indent + Utilities.spaces(3) + "<tr>";

			for (int col = 0; col < table[row].length; col++) {
				if (table[row][col] != null) {
					items += "<td>" + (table[row][col]).genHTML(0) + "</td>";
				} else {
					items += "<td>" + "</td>";
				}
			}
			items += "</tr>" + "\n";
		}

		html += items + indent + getEndTag();

		return html;

	}
}
