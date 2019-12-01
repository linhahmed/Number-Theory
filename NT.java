import java.time.Duration;
import java.time.Instant;

public class imp {
	public static long naive1(long m, long n, long p) {
		long c = 1;
		for (int i = 0; i < n; i++) {
			c = c * m;
		}
		c = c % p;
		return c;
	}

	public static long naive2(long m, long n, long p) {
		long c = 1;
		for (int i = 0; i < n; i++) {
			c = (c * m) % p;
		}
		c = c % p;
		return c;
	}

	public static long fastModExpI(long a, long b, long m) {
		long res = 1;
		while (b > 0) {
			if ((b & 1) == 1) {
				res = (res * a) % m;

			}
			a = (a * a) % m;
			b = b >> 1;

		}
		// System.out.println(res);
		return res;

	}

	static int ans = 1;

	public static int fastModExpR(int a, int b, int m) {

		if (b == 0)
			return 1;
		else if (b % 2 == 0) {
			int temp = fastModExpR(a, b / 2, m);
			return (temp * temp) % m;

		} else {
			return (a % m * fastModExpR(a, b - 1, m)) % m;

		}

	}

	public static int addCRT(long x, long y, int[] mods) {
		long[] remsX = new long[mods.length];
		long[] remsY = new long[mods.length];
		long[] remsR = new long[mods.length];
		int prodM = 1;
		int subProd;
		int res = 0;
		for (int i = 0; i < mods.length; i++) {
			remsX[i] = fastModExpI(x, 1, mods[i]);
			remsY[i] = fastModExpI(y, 1, mods[i]);
			remsR[i] = (remsX[i] + remsY[i]) % mods[i];
			prodM = prodM * mods[i];
		}
		for (int i = 0; i < mods.length; i++) {
			subProd = prodM / mods[i];
			int[] a = gcd(subProd, mods[i]);

			if (a[1] < 0) {
				a[1] += mods[i];
			}
			res += (subProd * a[1] * remsR[i]) % prodM;
		}
		System.out.println(res % prodM);
		return res % prodM;

	}
	public static int mulCRT(long x, long y, int[] mods) {
		long[] remsX = new long[mods.length];
		long[] remsY = new long[mods.length];
		long[] remsR = new long[mods.length];
		int prodM = 1;
		int subProd;
		int res = 0;
		for (int i = 0; i < mods.length; i++) {
			remsX[i] = fastModExpI(x, 1, mods[i]);
			remsY[i] = fastModExpI(y, 1, mods[i]);
			remsR[i] = (remsX[i] * remsY[i]) % mods[i];
			prodM = prodM * mods[i];
		}
		for (int i = 0; i < mods.length; i++) {
			subProd = prodM / mods[i];
			int[] a = gcd(subProd, mods[i]);

			if (a[1] < 0) {
				a[1] += mods[i];
			}
			res += (subProd * a[1] * remsR[i]) % prodM;
		}
		System.out.println(res % prodM);
		return res % prodM;

	}

	public static void Primes(int n) {
		long startTime = System.currentTimeMillis();
		boolean prime[] = new boolean[n + 1];
		ArrayList<String> op = new ArrayList<String>();
		for (int i = 0; i < n; i++)
			prime[i] = true;

		for (int i = 2; i * i <= n; i++) {
			if (prime[i] == true) {
				for (int j = i * 2; j <= n; j += i)
					prime[j] = false;
			}
		}

		for (int i = 2; i <= n; i++) {
			if (prime[i] == true)
				System.out.print(i + " ");
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("\nExecution time is " + elapsedTime);
	}

	public static int[] gcd(int p, int q) {
		if (q == 0)
			return new int[] { p, 1, 0 };

		int[] values = gcd(q, p % q);
		int d = values[0];
		int a = values[2];
		int b = values[1] - (p / q) * values[2];
		return new int[] { d, a, b };
	}

	public static void main(String[] args) {
		// int[] f=gcd(98*97*95,65);
		 int[] f = { 99, 98, 97, 95 };

		// addCRT(1234,1021,f);
		// System.out.println(f[1]);
		 //int[] f=gcd(99*98*97,95);
		// System.out.println(f[1]);
		mulCRT(1237, 136, f);
		//System.out.println(naive2(23, 3, 6));
	}
}
