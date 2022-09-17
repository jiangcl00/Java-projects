package cmdLineInterpreter;

import onlineTest.SystemManager;
import java.util.Scanner;

/**
 * 
 * By running the main method of this class we will be able to execute commands
 * associated with the SystemManager. This command interpreter is text based.
 *
 */
public class Interpreter {
	public static void Menu() {
		String menu = "";
		menu += "Input 1 to Add a student" + "\n";
		menu += "Input 2 to Add an exam" + "\n";
		menu += "Input 3 to Add a true/false question" + "\n";
		menu += "Input 4 to Answer a true/false question" + "\n";
		menu += "Input 5 to Get the exam score for a student" + "\n";
		menu += "Input 0 to Exit" + "\n";
		System.out.print(menu);
	}

	public static void main(String[] args) {
		int choice;
		SystemManager manager = new SystemManager();
		Scanner input = new Scanner(System.in);
		Menu();
		choice = input.nextInt();
		while (choice != 0) {
			switch (choice) {
			case 1:
				System.out.println("Input student name");
				input.nextLine();
				String name = input.nextLine();
				manager.addStudent(name);
				break;
			case 2:
				System.out.println("Input exam id");
				int examId = input.nextInt();
				System.out.println("Input exam title");
				String title = input.next();
				manager.addExam(examId, title);
				break;
			case 3:
				System.out.println(
						"Input id of the exam you want to add the question");
				int id = input.nextInt();
				System.out.println("Input the question number");
				int questionNumber = input.nextInt();
				System.out.println("Input the text of the question");
				input.nextLine();
				String text = input.nextLine();
				System.out.println("Input the credit for the question");
				double credit = input.nextDouble();
				System.out.println(
						"Input the correct answer for this question, either true or false");
				boolean key = input.nextBoolean();
				manager.addTrueFalseQuestion(id, questionNumber, text, credit,
						key);
				break;
			case 4:
				System.out.println("Input the student name");
				input.nextLine();
				String student = input.nextLine();
				System.out.println("Input id of the exam");
				int answerExamId = input.nextInt();
				System.out.println("Input the question number");
				int answerQuestionNumber = input.nextInt();
				System.out.println(
						"Input the student's answer for this question, either true or false");
				boolean answer = input.nextBoolean();
				manager.answerTrueFalseQuestion(student, answerExamId,
						answerQuestionNumber, answer);
				break;
			case 5:
				System.out.println("Input the student name");
				input.nextLine();
				String studentName = input.nextLine();
				System.out.println("Input id of the exam");
				int scoreExamId = input.nextInt();
				System.out.println(
						manager.getExamScore(studentName, scoreExamId));
				break;
			}
			Menu();
			choice = input.nextInt();
		}
		input.close();

	}
}
