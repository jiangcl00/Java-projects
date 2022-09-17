package onlineTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class SystemManager implements Manager, Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Integer, ArrayList<Question>> examList = new HashMap<Integer, ArrayList<Question>>();
	private Map<String, Map<Integer, ArrayList<Double>>> studentList = new HashMap<String, Map<Integer, ArrayList<Double>>>();
	private Map<String, Double> gradesCutOffs = new HashMap<String, Double>();

	public boolean addExam(int examId, String title) {
		if (examList.containsKey(examId)) {
			return false;
		} else {
			examList.put(examId, new ArrayList<Question>());
			return true;
		}

	}

	public void addTrueFalseQuestion(int examId, int questionNumber,
			String text, double points, boolean answer) {
		TrueFalseQuestion newTrueFalse = new TrueFalseQuestion(questionNumber,
				text, points, answer);
		examList.get(examId).add(newTrueFalse);
		Collections.sort(examList.get(examId));

	}

	public void addMultipleChoiceQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		MultipleChoiceQuestion newMultiple = new MultipleChoiceQuestion(
				questionNumber, text, points, answer);
		examList.get(examId).add(newMultiple);
		Collections.sort(examList.get(examId));

	}

	public void addFillInTheBlanksQuestion(int examId, int questionNumber,
			String text, double points, String[] answer) {
		FillInTheBlanksQuestion newFillInTheBlanks = new FillInTheBlanksQuestion(
				questionNumber, text, points, answer);
		examList.get(examId).add(newFillInTheBlanks);
		Collections.sort(examList.get(examId));
	}

	public String getKey(int examId) {
		String key = "";

		if (examList.get(examId) == null) {
			return "Exam not found";
		}
		for (int i = 0; i < examList.get(examId).size(); i++) {

			key += examList.get(examId).get(i) + "\n";
		}
		return key;
	}

	public boolean addStudent(String name) {
		if (studentList.containsKey(name)) {
			return false;
		} else {
			// Student newStudent = new Student(name);
			studentList.put(name, new HashMap<Integer, ArrayList<Double>>());
			return true;
		}
	}

	public void answerTrueFalseQuestion(String studentName, int examId,
			int questionNumber, boolean answer) {
		if (studentList.get(studentName).containsKey(examId) == false) {
			studentList.get(studentName).put(examId, new ArrayList<Double>());
		}

		for (int i = 0; i < examList.get(examId).size(); i++) {
			Question Q = (Question) examList.get(examId).get(i);

			if (Q.getNumber() == questionNumber) {
				TrueFalseQuestion TrueFalse = (TrueFalseQuestion) Q;

				if (answer == TrueFalse.getKey()) {
					studentList.get(studentName).get(examId)
							.add(TrueFalse.getCredit());
				} else if (answer != TrueFalse.getKey()) {
					studentList.get(studentName).get(examId).add(0.0);
				}
			}

		}

	}

	public void answerMultipleChoiceQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		if (studentList.get(studentName).containsKey(examId) == false) {
			studentList.get(studentName).put(examId, new ArrayList<Double>());
		}

		int correct = 0;

		for (int i = 0; i < examList.get(examId).size(); i++) {
			Question Q = (Question) examList.get(examId).get(i);

			if (Q.getNumber() == questionNumber) {
				MultipleChoiceQuestion MC = (MultipleChoiceQuestion) Q;
				String[] questionKey = MC.getKey();

				for (String element : questionKey) {
					for (String e : answer) {
						if (e == element) {
							correct++;
						}
					}
				}
				if (answer.length == MC.getKey().length
						&& correct == MC.getKey().length) {
					studentList.get(studentName).get(examId)
							.add(MC.getCredit());
				} else {
					studentList.get(studentName).get(examId).add(0.0);
				}
			}

		}

	}

	public void answerFillInTheBlanksQuestion(String studentName, int examId,
			int questionNumber, String[] answer) {
		if (studentList.get(studentName).containsKey(examId) == false) {
			studentList.get(studentName).put(examId, new ArrayList<Double>());
		}
		double credit = 0.0;
		int correctAmount = 0;
		double partialCredit = 0.0;

		for (int i = 0; i < examList.get(examId).size(); i++) {
			Question Q = (Question) examList.get(examId).get(i);

			if (Q.getNumber() == questionNumber) {
				FillInTheBlanksQuestion fill = (FillInTheBlanksQuestion) Q;
				String[] questionKey = fill.getKey();
				credit = fill.getCredit();
				partialCredit = credit / questionKey.length;

				for (String element : questionKey) {
					for (String e : answer) {
						if (e == element) {
							correctAmount++;
						}
					}
				}
			}

		}

		if (correctAmount != 0) {
			studentList.get(studentName).get(examId)
					.add(correctAmount * partialCredit);
		} else {
			studentList.get(studentName).get(examId).add(0.0);
		}

	}

	public double getExamScore(String studentName, int examId) {
		double score = 0.0;
		for (int i = 0; i < studentList.get(studentName).get(examId)
				.size(); i++) {
			score += studentList.get(studentName).get(examId).get(i);
		}
		return score;
	}

	public String getGradingReport(String studentName, int examId) {
		String report = "";
		double studentScore = 0.0;
		double total = 0.0;
		int questionNumber = 1;

		for (int i = 0; i < studentList.get(studentName).get(examId)
				.size(); i++) {
			studentScore += studentList.get(studentName).get(examId).get(i);
			total += examList.get(examId).get(i).getCredit();
			report += "Question #" + (questionNumber++) + " "
					+ studentList.get(studentName).get(examId).get(i)
					+ " points out of "
					+ examList.get(examId).get(i).getCredit() + "\n";
		}
		report += "Final Score: " + studentScore + " out of " + total;

		return report;
	}

	public void setLetterGradesCutoffs(String[] letterGrades,
			double[] cutoffs) {
		for (int i = 0; i < letterGrades.length; i++) {
			gradesCutOffs.put(letterGrades[i], cutoffs[i]);
		}

	}

	public double getCourseNumericGrade(String studentName) {
		double total = 0.0;
		double percent = 0.0;
		double totalPercent = 0.0;

		for (Integer element : studentList.get(studentName).keySet()) {
			for (int i = 0; i < examList.get(element).size(); i++) {
				total += examList.get(element).get(i).getCredit();
			}
			percent += getExamScore(studentName, element) / total;
			total = 0;
		}
		totalPercent = percent * 100 / examList.size();

		return totalPercent;
	}

	public String getCourseLetterGrade(String studentName) {
		String grade = "";

		for (String element : gradesCutOffs.keySet()) {
			if (gradesCutOffs
					.get(element) <= getCourseNumericGrade(studentName)) {
				grade = element;
				break;
			}
		}
		return grade;
	}

	public String getCourseGrades() {
		String grade = "";
		ArrayList<String> list = new ArrayList<String>();

		for (String element : studentList.keySet()) {
			list.add(element);
		}
		Collections.sort(list);

		for (String e : list) {
			grade += e + " " + getCourseNumericGrade(e) + " "
					+ getCourseLetterGrade(e) + "\n";
		}
		
		return grade;
	}

	public double getMaxScore(int examId) {
		double studentScore = 0.0;
		ArrayList<Double> scoreList = new ArrayList<Double>();

		for (String element : studentList.keySet()) {
			for (Double e : studentList.get(element).get(examId)) {
				studentScore += e;
			}
			scoreList.add(studentScore);
			studentScore = 0.0;
		}

		double max = Collections.max(scoreList);
		
		return max;
	}

	public double getMinScore(int examId) {
		double studentScore = 0.0;
		
		ArrayList<Double> scoreList = new ArrayList<Double>();

		for (String element : studentList.keySet()) {
			for (Double e : studentList.get(element).get(examId)) {
				studentScore += e;
			}
			scoreList.add(studentScore);
			studentScore = 0.0;
		}

		double min = Collections.min(scoreList);
		
		return min;
	}

	public double getAverageScore(int examId) {
		double studentScore = 0.0;
		int studentNum = 0;

		for (String element : studentList.keySet()) {
			for (Double e : studentList.get(element).get(examId)) {
				studentScore += e;
			}
			studentNum++;
		}
		double average = studentScore / studentNum;
		
		return average;
	}

	public void saveManager(Manager manager, String fileName) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(fileName));
			output.writeObject(manager);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Manager restoreManager(String fileName) {
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(fileName));
			SystemManager Manager = (SystemManager) input.readObject();
			input.close();
			return Manager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
