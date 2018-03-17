package backlog.solved;
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
 * @Judge_ID: 10198 "Counting"
 * 
 */
public class Main10198 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		BigInteger[] m = new BigInteger[1001];
		m[1] = BigInteger.valueOf(2);
		m[2] = BigInteger.valueOf(5);
		m[3] = BigInteger.valueOf(13);
		
		for (int i = 4 ; i< 1001 ; i++) {
			m[i] = m[i-1].multiply(BigInteger.valueOf(2));
			m[i] = m[i].add(m[i-2]).add(m[i-3]);
		}
		while (in.hasNext()) {

			int n = in.nextInt();
			System.out.println(m[n]);

		}

	}

}