package onlineTest;

import java.io.Serializable;

public class Question implements Comparable<Question>, Serializable {

	private static final long serialVersionUID = 1L;
	private int number;
	private String text;
	private double credit;

	public Question(int number, String text, double credit) {
		this.number = number;
		this.text = text;
		this.credit = credit;
	}

	public int getNumber() {
		return this.number;
	}

	public String getText() {
		return this.text;
	}

	public double getCredit() {
		return this.credit;
	}

	public String toString() {
		String ans = "";
		ans += "Question Text: " + text + "\n";
		ans += "Points: " + credit + "\n";
		return ans;
	}

	public int compareTo(Question o) {
		if (this.number > o.number) {
			return 1;
		} else if (this.number == o.number) {
			return 0;
		} else {
			return -1;
		}
	}

}
