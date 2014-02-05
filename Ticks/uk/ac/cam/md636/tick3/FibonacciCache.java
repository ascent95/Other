package uk.ac.cam.md636.tick3;

public class FibonacciCache {
	 public static long[] fib = new long[20];

	 public static void store() {
		 for (int i=0; i < fib.length; i++) {
			 if (i < 2) {
				 fib[i] = 1;
			 }
			 else {
				 fib[i] = fib[i-1] + fib[i-2];
			 }

		 }
	 }

	 public static void reset() {
		 for (int i=0; i < fib.length; i++) {
			 fib[i] = 0;
		 }
	 }
	 
	 public static long get(int i) {
		 if (i < fib.length && i > -1) {
			 return fib[i];
		 }
		 else {
			 return -1L;
		 }
	 }
	 public static void main(String[] args) {
		 store();
		 System.out.println(fib.length);
	 }
	}
