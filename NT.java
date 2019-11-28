import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class NT {
	public static int[] gcd(int p, int q) {
		if (q == 0)
			return new int[] { p, 1, 0 };
		
		int[] values = gcd(q, p % q);
		int d = values[0];
		int a = values[2];
		int b = values[1] - (p / q) * values[2];
		return new int[] { d, a, b };
	}

	public static String[] isPrime(int n) {
		Instant start = Instant.now();
		String[] op;
		if (n <= 1) {
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();
			op = new String[] { "false", Long.toString(timeElapsed) };
			return op;
		}
		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0) {
				Instant finish = Instant.now();
				long timeElapsed = Duration.between(start, finish).toMillis();
				op = new String[] { "false", Long.toString(timeElapsed) };
				return op;
			}
		}
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		op = new String[] { "true", Long.toString(timeElapsed) };
		return op;
	}

	public static void main(String[] args) {
		int a = 50, b = 15;
		int[] k = gcd(a, b);
		System.out.print(Arrays.toString(k));
		System.out.println("\n" + Arrays.toString(isPrime(100)) + "\n" + Arrays.toString(isPrime(101)) + "\n"
				+ Arrays.toString(isPrime(1000111)) + "\n" + Arrays.toString(isPrime(20)));
	}

}
