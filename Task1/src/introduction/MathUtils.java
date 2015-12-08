package introduction;

import java.util.Arrays;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) {

		int firstNum = firstNumber;
		int secondNum = secondNumber;

		while (firstNum != secondNum) {
			if (firstNum > secondNum) {
				firstNum -= secondNum;
			} else {
				secondNum -= firstNum;
			}
		}
		return firstNum;
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) {
		int summOfDigits = 0;
		for (int processedNumber = number; processedNumber > 0; processedNumber /= 10) {
			summOfDigits += processedNumber % 10;
		}
		return summOfDigits;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) {
		final int MIN_PRIME = 2;
		if (number < MIN_PRIME) {
			return false;
		} else if (number == MIN_PRIME) {
			return true;
		} else {
			for (int i = MIN_PRIME; i < Math.sqrt(number) + 1; i++) {
				if (number % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		int summ = 0;
		for (int i = 1; i <= n; i++) {
			summ += (i % 2 == 0) ? (-getFactorial(i)) : (getFactorial(i));
		}
		return summ;
	}

	private static int getFactorial(int number) {
		if (number == 0) {
			return 1;
		}
		return number * getFactorial(number - 1);
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) {
		int[] fibArray = new int[length];
		for (int i = 0; i < fibArray.length; i++) {
			fibArray[i] = getFibonacciElem(i + 1);
		}
		return fibArray;
	}

	private static int getFibonacciElem(int n) {
		if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 1;
		} else {
			return getFibonacciElem(n - 1) + getFibonacciElem(n - 2);
		}
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) {
		int[] primeArray = new int[length];
		int number = 2; // 2 - min prime
		for (int i = 0; i < primeArray.length; i++) {
			for (;; number++) {
				if (this.isPrime(number)) {
					primeArray[i] = number++;
					break;
				}
			}
		}
		return primeArray;
	}

	public static void main(String[] args) {
		MathUtils mu = new MathUtils();

		System.out.println("1) Greatest common divider of 21 and 35 is : " + mu.getGreatestCommonDivider(35, 21));
		System.out.println("2) Sum of digits in 111717 = " + mu.getSumOfDigits(111717));
		System.out.println("3) 67 " + (mu.isPrime(67) ? "is a prime." : "is not a prime."));
		System.out.println("4) Summ of row 1! - 2! + 3! - 4! + 5! - ... - 10! = " + mu.getSumOfRow(10));
		System.out.println("5) Row of 10 Fibonacci series members : " + Arrays.toString(mu.getFibonacciSeries(10)));
		System.out.println("6) Row of 10 first primes : " + Arrays.toString(mu.getPrimeSeries(10)));
	}
}
