package backlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * complete with ida*
 * 
 * @Judge_ID: 10128 "Queue"
 * 
 */
public class Main10128 {

	static long[][][] dp = null;

	public static void main(String[] args) throws IOException {
		dp = new long[14][14][14];
		for (int x = 0 ; x < 14 ; x++) 
			for (int y = 0 ; y < 14 ; y++) 
				for (int z = 0 ; z < 14 ; z++) 
					dp[x][y][z] = -1;

		dp[1][1][1] = 1;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer games = Integer.parseInt(br.readLine()); // number of test cases
		char c = 1;
		while(br.ready()){
		
			String line = br.readLine();
			String[] token = line.split(" ");
			int N = Integer.parseInt(token[0]);
			int P = Integer.parseInt(token[1]);
			int R = Integer.parseInt(token[2]);
			System.out.println(calc(N, P, R));

		}

	}

	public static long calc(int N, int P, int R) {
		 if (P <1 || R <1 || N < 1)
			 return 0;
		 if (P > 13 || R > 13 || N >13 )
			 return 0;
		 
		
		 if (dp[N][P][R] > -1)
			 return dp[N][P][R];
		
		 dp[N][P][R] = calc(N - 1, P - 1, R) + calc(N - 1, P, R) * (N - 2) +
		 calc(N - 1, P, R - 1);

		return dp[N][P][R];

	}

}