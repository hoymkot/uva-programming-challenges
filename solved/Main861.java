package backlog.solved;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Complete with backtracking
 * 
 * @Judge_ID: 861 "Little Bishop"
 * 
 */
public class Main861 {

	static int MAXN = 8;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		long t1 = System.currentTimeMillis();
		while (in.hasNextLine()) {
			Integer n = in.nextInt();
			Integer k = in.nextInt();

			if (n == 0 && k == 0)
				break;
			// if no bishop, only one solution (empty chessboard)
			if (k == 0) {
				System.out.println("1");
			} else if (n == 1) {
				// if only one square, the result depends on k (0 or 1)
				System.out.println(k);
			} else if (k > 2 * (n - 1)) {
				// the maximum number of mutually exclusive bishop is 2(n-1)
				System.out.println("0");
			} else {
//				System.out.println(litter_bishops_by_backtrack(n, k));
				System.out.println(litter_bishops_by_combinatorics(n, k).toString());
			
				
			}
		}
		long t2 = System.currentTimeMillis();
		// System.out.println(t2 - t1);
	}

	private static BigInteger litter_bishops_by_combinatorics(Integer n, Integer k) {

		// turn the map 45 degree, so we have diamond shape map. 
		BigInteger[] white = new BigInteger[9];
		BigInteger[] black = new BigInteger[9];
		
		// the topology of white square on a 8*8 chess board is: 1 3 5 7 7 5 3 1
		// the topology of white square on a 7*7 chess board is: 1 3 5 7 5 3 1
		for (int i = 1; i<= n;i++) {
			white[i] = ( ( i % 2 > 0) ? BigInteger.valueOf(i) : white[i-1]);
		}
		// the topology of black square on a 8*8 chess board is: 2 4 6 8 6 4 2
		// the topology of white square on a 8*8 chess board is: 2 4 6 6 4 2
		for (int i = 1; i<= n-1;i++) {
			black[i] = ( ( i % 2 > 0) ? BigInteger.valueOf(i+1) : black[i-1]);
		}
		
		// 8 maximum number of lines
		// 2*(8-1) = 14 maximum bishop pieces allowed
		BigInteger[][] white_solution = new BigInteger[9][15];
		BigInteger[][] black_solution = new BigInteger[9][15];
		
		for (int i =0; i< 9; i++) {
			for (int j=0; j<15; j++){
				white_solution[i][j] = BigInteger.valueOf(0);
				black_solution[i][j] = BigInteger.valueOf(0);
			}
		}
		
		// n = n , k = 0 , only one solution, empty chess board
		for (int i = 0 ; i <= n;i++){
			white_solution[i][0] = BigInteger.valueOf(1);
		}
		
		// n = 0, no solution, no chess board
		for (int j = 1; j <= k ; j++) {
			white_solution[0][j] = BigInteger.valueOf(0);
		}
		
		for (int i = 1; i <= n; i++){
			for (int j =1; j<=k && j<=i; j++) {
				white_solution[i][j] = white_solution[i-1][j].add(white_solution[i-1][j-1].multiply((white[i].subtract(BigInteger.valueOf(j-1)))));
			}
		}
		
		// n = n , k = 0 , only one solution, empty chess board
		for (int i = 0 ; i <= n-1;i++){
			black_solution[i][0] = BigInteger.valueOf(1);
		}
		
		// n = 0, no solution, no chess board
		for (int j = 1; j <= k ; j++) {
			black_solution[0][j] = BigInteger.valueOf(0);
		}
		
		for (int i = 1; i <= n-1; i++){
			for (int j =1; j<=k && j<=i; j++) {
				black_solution[i][j] = black_solution[i-1][j].add(black_solution[i-1][j-1].multiply((black[i].subtract(BigInteger.valueOf(j-1)))));
				
			}
		}		

		BigInteger total = BigInteger.valueOf(0);
		for (int i =0; i <=k ; i++) 
			total = total.add(white_solution[n][i].multiply(black_solution[n-1][k-i]));
		return total;
	}
	

	static long solution = 0;
	static boolean finished = false;

	/**
	 * 
	 * @param bishops
	 * @param c
	 * @param n
	 * @param k
	 */
	public static void backtrack(int bishops[], int c, int n, int k) {
		if (c == k)
			solution++;
		else {
			int ncandidates = 0;
			// representation of chessboard by a single dimension array			
			int[] candidates = new int[MAXN * MAXN]; 

			ncandidates = construct_candidates(bishops, c, n, candidates);

			for (int i = 0; i < ncandidates; i++) {
				// start backtrack recursion, note that bishops[c] changes in
				// each iteration thus lead to different trees;
				bishops[c] = candidates[i];
				backtrack(bishops, c + 1, n, k);
			}
		}

	}

	public static long[][] map = new long[9][65];
	
	/**
	 * 
	 * @param n chess board dimension n*n
	 * @param k pieces of bishop 
	 * @return number of solution available
	 */
	public static long litter_bishops_by_backtrack(int n, int k) {
		// per mathmatical proof, the maximum number of mutually exclusive bishop on a chess board is 2(n-1)
		int[] bishops = new int[2 * (MAXN - 1) + 1]; 
		solution = 0;
		backtrack(bishops, 0, n, k);
		return solution;
	}

	/**
	 * 
	 * @param bishops
	 *            this array hold bishop position, as a precondition for future
	 *            tree development
	 * @param c
	 *            the number of bishop on the hypothetical chessboard, it is
	 *            also a "step" indicator.
	 * @param n
	 *            chess board dimension
	 * @param candidates
	 * @return
	 */
	public static int construct_candidates(int[] bishops, int c, int n, int[] candidates) {
		boolean legal_move;

		int start = 0;
		if (c != 0) {
			// we work from lower dimension position to higher.
			// we find the position to start with here.
			start = bishops[c - 1];
		}

		int ncandidates = 0;
		// every square on a diagonal line is a meaningful step, and grow to a
		// different branch of tree.
		for (int p = start; p < n * n; p++) {
			legal_move = true;

			// this part check whether the new position conflict with previously
			// determined pieces, if so, ignore this position.
			// p/n is the y-coordinate, p%n is the x-coordinate on the chess
			// board.
			// is diagonal, if difference in x-coordinate equals difference in
			// y-coordinate
			for (int j = 0; j < c; j++) {
				if (Math.abs(bishops[j] / n - p / n) == Math.abs(bishops[j] % n - p % n)) {
					legal_move = false;
					break;
				}
			}

			if (legal_move == true) {
				candidates[(ncandidates)++] = p;
			}
		}

		return ncandidates;

	}

}