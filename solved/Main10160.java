package backlog.solved;

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
 * 
 * 
 * @Judge_ID: 10160 "Servicing Station"
 * 
 */
public class Main10160 {

	static int n = 0, m = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			long t1 = System.currentTimeMillis();

			n = in.nextInt();
			if (n == 0)
				break;
			m = in.nextInt();
			if (n == 0 && m == 0)
				break;

			init();
			for (int i = 0; i < m; i++) {
				int u = in.nextInt();
				int v = in.nextInt();
				graph[u][v] = true;
				graph[v][u] = true;
			}
			dfs(0);
			long t2 = System.currentTimeMillis();
			System.out.println(minstations);
		}

	}

	static boolean[][] graph = new boolean[36][36]; // adjacent matrix
//	static int[] stations = new int[36]; // indicator whether a station is set
	static int nstations = 0;
	static int[] inservice = new int[36];
	static int minstations = 40;

	public static void init() {
		minstations = 100;
		nstations = 0;
		for (int i = 1; i < 36; i++) {
			inservice[i] = 0;
			for (int j = 1; j < 36; j++) {
				graph[i][j] = false;
				if (i == j) {
					graph[i][j] = true;
				}

			}
		}
	}

	public static int dfs(int depth) {
		if (depth > n) {
			return 0;
		}

		if (nstations > minstations) {
			return 0;
		}


		int a = get_active();
		if (a == n) {
			if (nstations < minstations) {
				minstations = nstations;
			}
			return minstations;
		}


		if (depth + 1 > n) {
			return 0;
		}
		
		for (int i = 1; i < depth + 1; i++) {
			if (inservice[i] == 0) {
				int j;
				for (j = depth + 1; j < n + 1; j++) {
					if (graph[i][j] == true) {
						break;
					}
				}
				if (j > n) {
					return 0;
				}
			}
		}

		nstations++;
		for (int j = 1; j < n + 1; j++) {
			if (graph[depth + 1][j] == true) {
				inservice[j]++;
			}
		}
		int n_a = get_active();
		if (n_a > a)
			dfs(depth + 1);
		nstations--;
		for (int j = 1; j < n + 1; j++) {
			if (graph[depth + 1][j] == true) {
				inservice[j]--;
			}
		}
		dfs(depth + 1);

		return 0;

	}

	public static int get_active() {
		int count = 0;
		for (int i = 1; i < n + 1; i++) {
			if (inservice[i] > 0)
				count++;
		}
		return count;
	}

}