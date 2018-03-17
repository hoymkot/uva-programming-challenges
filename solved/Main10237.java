package backlog.solved;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Complete with combinatorial solution
 * 
 * @Judge_ID: 10237 "Bishop"
 * 
 */
public class Main10237 {

	static int MAXN = 30;

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
				System.out.println(litter_bishops_by_combinatorics(n, k).toString());
			
				
			}
		}
		long t2 = System.currentTimeMillis();
		// System.out.println(t2 - t1);
	}

	private static BigInteger litter_bishops_by_combinatorics(Integer n, Integer k) {

		// turn the map 45 degree, so we have diamond shape map. 
		BigInteger[] white = new BigInteger[n+1];
		BigInteger[] black = new BigInteger[n+1];
		
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
		BigInteger[][] white_solution = new BigInteger[n+1][2*(n-1)+1];
		BigInteger[][] black_solution = new BigInteger[n+1][2*(n-1)+1];
		
		for (int i =0; i< n+1; i++) {
			for (int j=0; j<2*(n-1)+1; j++){
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


}