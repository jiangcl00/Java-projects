package onlineTest;

import java.io.Serializable;

public class TrueFalseQuestion extends Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean key;

	public TrueFalseQuestion(int number, String text, double credit,
			boolean key) {
		super(number, text, credit);
		this.key = key; // "True" or "False"
	}

	public boolean getKey() {
		return key;
	}

	public String toString() {
		if (key) {
			return super.toString() + "Correct Answer: True";
		} else {
			return super.toString() + "Correct Answer: False";
		}

	}

}