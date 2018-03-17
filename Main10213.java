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
 * @Judge_ID: 10213 "How Many Pieces of Land?"
 * 
 */
public class Main10213 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int game = in.nextInt();

		for (int i = 0; i < game; i++) {

			BigInteger n = in.nextBigInteger();
			BigInteger op1 = n.multiply(n.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
			BigInteger op2 = n.multiply(n.subtract(BigInteger.ONE)).multiply(n.subtract(BigInteger.valueOf(2))).multiply(n.subtract(BigInteger.valueOf(3))).divide(BigInteger.valueOf(24));

			System.out.println(op1.add(op2).add(BigInteger.ONE));
		}

	}

	static Map<BigInteger, BigInteger> map = new HashMap<BigInteger, BigInteger>();
	static Map<BigInteger, Map<Integer, BigInteger>> bmap = new HashMap<BigInteger, Map<Integer, BigInteger>>();

}