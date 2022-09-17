package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.AnchorElement;
import model.ImageElement;
import model.ParagraphElement;
import model.TagElement;
import model.TextElement;

public class StudentTests {

	@Test
	public void test() {
		int indentation = 3;
		String answer = "", attributes = null;
		
		TagElement.resetIds();
		TagElement.enableId(true);
		ParagraphElement element = new ParagraphElement(attributes);
		element.addItem(new TextElement("Fear the turtle"));
		element.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", attributes));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";
		
		TagElement.enableId(false);
		indentation = 6;
		attributes = "style=\"color:red\"";
		ParagraphElement element2 = new ParagraphElement(attributes);
		element2.addItem(new TextElement("Fear the turtle"));
		element2.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", ""));
		answer += element2.genHTML(indentation);
		
		assertTrue(TestsSupport.isCorrect("pubParagraphElementTest1.txt", answer));
	}

}
