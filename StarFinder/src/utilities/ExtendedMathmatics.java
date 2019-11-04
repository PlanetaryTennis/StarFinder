package utilities;

public class ExtendedMathmatics {

	public static double log(double number, double base) {
		return Math.log(number) / Math.log(base);
	}

	public static int choose(int n, int r) {
		long start = factorial(n);
		long temp = (factorial(r) * factorial(n - r));
		return (int) (start / temp);
	}

	private static long factorial(int n) {
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}
}
