package backlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * 
 * Programming Challenge: The Programming Contest Training Manual
 * Implement a formula for integer partition counting (p135)
 * 
 */
public class MainIntegerPartition {

	// public static void main(String[] args) throws IOException {
	// Scanner in = new Scanner(System.in);
	//
	// while (in.hasNext()) {
	//
	// BigInteger a = in.nextBigInteger();
	// BigInteger b = in.nextBigInteger();
	//
	// if (b.equals(BigInteger.ZERO)){
	// System.exit(0);
	// }
	//
	// int count = 0;
	// BigInteger f_i_m_1 = BigInteger.valueOf(2);
	// BigInteger f_i_m_2 = BigInteger.valueOf(1);
	// if (f_i_m_1 .compareTo(a) >= 0 && f_i_m_1 .compareTo(b) <= 0) {
	// count++;
	// }
	// if (f_i_m_2 .compareTo(a) >= 0 && f_i_m_2 .compareTo(b) <= 0) {
	// count++;
	// }
	// while (true) {
	// BigInteger f_i = f_i_m_1.add(f_i_m_2);
	// f_i_m_2 = f_i_m_1;
	// f_i_m_1 = f_i;
	// if (f_i.compareTo(a) >= 0 && f_i.compareTo(b) <= 0) {
	// count++;
	// }
	// if (f_i.compareTo(b) > 0) {
	// break;
	// }
	// }
	//// System.out.print(a.toString() + " " + b.toString() + " ");
	// System.out.println(count);
	// }
	//
	// }

	static Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	static int[][] pkm = new int[100][100];
	private static final int n = 0;

	public static void main(String[] args) throws IOException {
		for (int i = 0 ; i < 100 ; i++){
			for (int j = 0; j < 100; j++){
				pkm[i][j] = -1;
			}
		}
		int r = partition(9);
		System.out.println(r);
	}

	private static int partition(int n) {
		if (m.containsKey(n)) {
			return m.get(n);
		}
		int r = 0;
		if (n == 0 || n == 1)
			r = 1;
		else {
			for (int i = 1; i <= n; i++) {
				r += pk(n, i);
			}
		}
		m.put(n, r);
		System.out.println(n + " " + r);

		return r;

	} 
	
	private static int pk (int n, int k) {
		if (pkm[n][k] > -1)
			return pkm[n][k];
		int r = 0;
		if (k ==1 || k==0 || k == n ) {
			r= 1;
		} else if (k > n ) {
			r = 0;
		} else {
			r = pk(n-k, k) + pk(n-1, k-1);
		}
		pkm[n][k] = r;
		return r;
	}

}