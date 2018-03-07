package backlog;

import java.io.IOException;
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
 * @Judge_ID: 10181 "15 Puzzle Problem"
 * 
 */
public class Main10181 {


	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static String[] direction = { "R", "L", "D", "U" };
	static int MAX_N = 50;
	static StringBuilder path = new StringBuilder();;
	static int[][] T = new int[15][15];
	static int e_y, e_x;

	// use the sum of manhattan distance of a configuration as a score for
	// heuristic
	static int h() {
		int limit = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (T[y][x] == 0)
					continue;
				int goal_y = (T[y][x] - 1) / 4;
				int goal_x = (T[y][x] - 1) % 4;
				limit += Math.abs(goal_y - y) + Math.abs(goal_x - x);
			}
		}
		return limit;
	}

	static boolean dfs(int current_steps, int prev_direction, int bound) {
		int limit = h();
		if (limit == 0)
			return true;
		if (current_steps + limit > bound)
			return false;
		for (int i = 0; i < 4; i++) {
			if (i == (prev_direction ^ 1))
				continue;

			int ny = e_y + dy[i];
			int nx = e_x + dx[i];
			if (ny < 0 || ny >= 4)
				continue;
			if (nx < 0 || nx >= 4)
				continue;
			
			path.append(direction[i]);
			int temp;
			temp = T[e_y][e_x];T[e_y][e_x] = T[ny][nx];T[ny][nx]= temp;
			temp = e_y; e_y= ny; ny =temp;
			temp = e_x; e_x=nx; nx=temp;
			if (dfs(current_steps + 1, i, bound))
				return true;
			temp = T[e_y][e_x];T[e_y][e_x] = T[ny][nx];T[ny][nx]= temp;
			temp = e_y; e_y= ny; ny =temp;
			temp = e_x; e_x=nx; nx=temp;
			path.deleteCharAt(path.length()-1);
			
		}
		
		return false;
	}

	static boolean ida_star(){
		for (int limit = h(); limit <= MAX_N; ++limit) {
			if (dfs(0,-1, limit)) 
				return true;
		}
		return false;
	}
	
	static boolean solvable(){
		int N = 0;
		int[] puzzle = {0, 0, 0, 0, 
						0, 0, 0, 0, 
						0, 0, 0, 0, 
						0, 0, 0, 0};

		for (int y =0; y <4; y++) {
			for (int x = 0 ; x <4; x++) {
				if (T[y][x] == 0) {
					e_y = y;
					e_x = x;
				}
				puzzle[y*4+x] = T[y][x];
			}
		}
		for (int i = 0; i < 16; i++) {
			for (int j= i+1; j <16; j++) {
				if (puzzle[j] != 0 && puzzle[i] > puzzle[j]) {
					N++;
				}
			}
		}
		
		return ((N + (e_y+1)) & 1) == 0;
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
//		long t1 = System.currentTimeMillis();
		Integer games = in.nextInt();
		for (int g = 0; g < games; g++) {
			path = new StringBuilder();
			for (int y =0; y <4; y++) {
				for (int x = 0 ; x <4; x++) {
					T[y][x] = in.nextInt();
				}
			}

			if (!solvable() || !ida_star()) {
				System.out.println("This puzzle is not solvable.");
			} else {
				System.out.println(path.toString());
			}

		}
//		long t2 = System.currentTimeMillis();
//		System.out.println(t2 - t1);
	}

}