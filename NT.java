import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NT {
	/**
	 * calculate (a^b)% m using naive algorithm
	 **/
	public static long naive1(long a, long b, long m) {
		long c = 1;
		for (long i = 0; i < b; i++) {
			c = c * a;
		}
		c = c % m;
		return c;
	}

	/**
	 * calculate (a^b)% m using naive algorithm
	 **/
	public static long naive2(long a, long b, long m) {
		long c = 1;
		for (long i = 0; i < b; i++) {
			c = (c * a) % m;
		}
		c = c % m;
		return c;
	}

	/**
	 * calculate (a^b)% m using iterative Fast Exponentiation algorithm
	 **/
	public static long fastModExpI(long a, long b, long m) {
		long res = 1;
		while (b > 0) {
			if ((b & 1) == 1) {
				res = (res * a) % m;

			}
			a = (a * a) % m;
			b = b >> 1;

		}
		return res;

	}

	/**
	 * calculate (a^b)% m using recursive Fast Exponentiation algorithm
	 **/

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

	/**
	 * calculate (x+y) using Chinese Remainder Theorem 
	 * in domain Zm1 Zm2 . . . . .  Zmn.
	 **/
	public static int addCRT(long x, long y, int[] mods) {
		long[] remsX = new long[mods.length];
		long[] remsY = new long[mods.length];
		long[] remsR = new long[mods.length];
		int prodM = 1;
		int subProd;
		int res = 0;
		for (int i = 0; i < mods.length; i++) {
			remsX[i] =x% mods[i];
			remsY[i] = y% mods[i];
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
		return res % prodM;

	}

	/**
	 * calculate (x+y) in ZM domain
	 **/
	public static long addCRTInM(long x, long y, int[] mods) {
		long prodM = 1;

		for (int i = 0; i < mods.length; i++) {
			prodM = prodM * mods[i];
		}
		return (x + y) % prodM;

	}


	/**
	 * calculate (x*y) using Chinese Remainder Theorem 
	 * in domain Zm1 Zm2 . . . . .  Zmn.
	 **/
	public static int mulCRT(long x, long y, int[] mods) {
		long[] remsX = new long[mods.length];
		long[] remsY = new long[mods.length];
		long[] remsR = new long[mods.length];
		int prodM = 1;
		int subProd;
		int res = 0;
		for (int i = 0; i < mods.length; i++) {
			remsX[i] =x% mods[i];
			remsY[i] = y% mods[i];
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
		return res % prodM;

	}

	/**
	 * calculate (x*y) in ZM domain
	 **/
	public static long mulCRTInM(long x, long y, int[] mods) {
		long prodM = 1;

		for (int i = 0; i < mods.length; i++) {
			prodM = prodM * mods[i];
		}
		return (x * y) % prodM;

	}

	/**
	 * return array {d, a, b} such that d = gcd(p, q), ap + bq = d
	 **/
	public static int[] gcd(int p, int q) {
		if (q == 0)
			return new int[] { p, 1, 0 };

		int[] values = gcd(q, p % q);
		int d = values[0];
		int a = values[2];
		int b = values[1] - (p / q) * values[2];
		return new int[] { d, a, b };
	}

	/**
	 * This function calculates prime numbers below n
	 */
	public static void Primes(int n) {
		long startTime = System.currentTimeMillis();
		// Create a boolean array "prime[0..n]" and initialize all of it as true.
		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i < n; i++)
			prime[i] = true;

		// A value in prime[i] will be false if i isn't prime, else true.
		for (int i = 2; i * i <= n; i++) {
			if (prime[i] == true) {
				for (int j = i * 2; j <= n; j += i)
					prime[j] = false;
			}
		}

		System.out.print("\nThe prime numbers below " + n + " :\n");
		for (int i = 2; i <= n; i++) {
			if (prime[i] == true) {
				System.out.print(i + " ");
			}
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("\nExecution time is " + elapsedTime);
	}

	
}
