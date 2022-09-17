package model;

public class ImageElement extends TagElement {
	private java.lang.String imageURL;

	public ImageElement(java.lang.String imageURL, int width, int height,
			java.lang.String alt, java.lang.String attributes) {
		super("img", false, null,
				checkNull(attributes, imageURL, width, height, alt));
		this.imageURL = imageURL;
	}

	// identify attributes by checking if attributes is null
	private static String checkNull(String attributes, String imageURL,
			int width, int height, String alt) {
		if (attributes == null) {
			return "src=\"" + imageURL + "\" width=\"" + width + "\" height=\""
					+ height + "\" alt=\"" + alt + "\"";
		} else {
			return "src=\"" + imageURL + "\" width=\"" + width + "\" height=\""
					+ height + "\" alt=\"" + alt + "\"" + attributes;
		}
	}

	public java.lang.String getImageURL() {
		return imageURL;
	}
}
