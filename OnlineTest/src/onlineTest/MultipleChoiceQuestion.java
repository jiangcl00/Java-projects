package onlineTest;

import java.io.Serializable;
import java.util.*;

public class MultipleChoiceQuestion extends Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[] key;

	public MultipleChoiceQuestion(int number, String text, double credit,
			String[] key) {
		super(number, text, credit);
		this.key = key;
	}

	public String[] getKey() {
		return key;
	}

	public String toString() {
		ArrayList<String> answerKey = new ArrayList<String>();
		String keyOutput = "";
		
		for (int i = 0; i < key.length; i++) {
			answerKey.add(key[i]);
		}
		Collections.sort(answerKey);
		
		for (int i = 0; i < answerKey.size(); i++) {
			if (i != 0) {
				keyOutput += ", ";
			}
			keyOutput += answerKey.get(i);
		}
		return super.toString() + "Correct Answer: [" + keyOutput + "]";
	}
}
