package examples;

public class Absolute {
	public static void main(String[] args) {
		System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
		System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
		System.out.println("Math.abs(Integer.MIN_VALUE): " + Math.abs(Integer.MIN_VALUE));
		boolean comparison = (Integer.MIN_VALUE == Math.abs(Integer.MIN_VALUE));
		System.out.println("Integer.MIN_VALUE == Math.abs(Integer.MIN_VALUE): " + comparison);
		System.out.println("Integer.MIN_VALUE % 3: " + Integer.MIN_VALUE % 3);
		System.out.println("Math.abs(Integer.MIN_VALUE) % 3: " + Math.abs(Integer.MIN_VALUE) % 3);
		System.out.println("Math.abs(Integer.MIN_VALUE % 3): " + Math.abs(Integer.MIN_VALUE % 3));
	}
}
