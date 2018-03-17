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
 * 
 * 
 * Utility Function
 * 
 */
public class MainUtility {

	public static void main(String[] args) throws IOException {
	}

	static Map<BigInteger, BigInteger> map = new HashMap<BigInteger, BigInteger>();
	static Map<BigInteger, Map<Integer, BigInteger>> bmap = new HashMap<BigInteger, Map<Integer, BigInteger>>();

	static public BigInteger fac(BigInteger n) {

		BigInteger r = BigInteger.ONE;

		for (BigInteger i = n; i.compareTo(BigInteger.ONE) > 0; i = i.subtract(BigInteger.ONE)) {
			r = r.multiply(i);
		}

		return r;

	}

	static public BigInteger bin(BigInteger n, int c) {
		if (bmap.containsKey(n)) {
			if (bmap.get(n).containsKey(c)) {
				return bmap.get(n).get(c);
			}
		} else {
			bmap.put(n, new HashMap<Integer, BigInteger>());
		}

		bmap.get(BigInteger.ONE).put(1, BigInteger.ONE);
		for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
			if (!bmap.containsKey(i)) {
				bmap.put(i, new HashMap<Integer, BigInteger>());
			}
			for (int j = 0; j < 5; j++) {
				if (!bmap.get(i).containsKey(j)) {
					if (j == 0) {
						bmap.get(i).put(j, BigInteger.ONE);
					} else if (j == 1) {
						bmap.get(i).put(j, i);
					} else if (i.equals(BigInteger.valueOf(j))) {
						bmap.get(i).put(j, BigInteger.ONE);
					} else if (n.compareTo(BigInteger.valueOf(c)) < 0) {
						bmap.get(i).put(j, BigInteger.ZERO);
					} else {
						BigInteger op1 = bmap.get(i.subtract(BigInteger.ONE)).get(j - 1);
						BigInteger op2 = bmap.get(i.subtract(BigInteger.ONE)).get(j);
						bmap.get(i).put(j, op1.add(op2));
					}
				}
			}
		}

		return bmap.get(n).get(c);
	}

}