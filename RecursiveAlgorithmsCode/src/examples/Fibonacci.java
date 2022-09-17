package examples;

public class Fibonacci {
	public static long callsCounter = 0;

	public static long fib(long x) {
		callsCounter++;

		if (x == 0) {
			return 0;
		} else if (x == 1) {
			return 1;
		} else {
			return fib(x - 1) + fib(x - 2);
		}
	}

	public static void main(String[] args) {
		long limit = 50;

		for (long i = 0; i <= limit; i++) {
			System.out.print("fib(" + i + "): " + fib(i));
			System.out.println(", Calls: " + callsCounter);
			callsCounter = 0;
		}
	}
}
