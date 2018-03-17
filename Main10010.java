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
 * @Judge_ID: 10010 "Where's Waldorf"
 * 
 */
public class Main10010 {

	static void print(String str){
		System.out.print(str);
	}
	static Map<String, String> map = new HashMap<String, String>();

	static void init() {

	}

	static Character[][] grid;
	static List<String> dict = new ArrayList<String>();
	static int m, n;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		init();
		int game = in.nextInt();
		String g;
		while (game > 0) {
			m = in.nextInt();
			n = in.nextInt();
			grid = new Character[m + 2][n + 2];
			for (int row = 0; row < m + 2; row++) {
				Arrays.fill(grid[row], '-');
			}
			in.nextLine();
			for (int i = 1; i < m + 1; i++) {
				g = in.nextLine();
				for (int j = 1; j < n + 1; j++) {
					grid[i][j] = g.charAt(j - 1);
				}
			}
			
//			for (int i = 0; i < m + 2; i++) {
//				for (int j = 0; j < n + 2; j++) {
//					System.out.print(grid[i][j]);
//				}
//				System.out.println("");
//			}
//			
			
			int words = in.nextInt();
			dict = new ArrayList<String>(words);
			g = in.nextLine();
			for (int i = 0; i < words; i++) {
				g = in.nextLine();
				dict.add(g);
			}
			process();
			game--;
			if (game > 0) {
				System.out.println("");
			}
		}

	}

	private static void process() {

		for (String word : dict) {
			for (int i = 1; i < m + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					if ( Character.toUpperCase(grid[i][j]) == 
							Character.toUpperCase(word.charAt(0)) ){
						boolean match = right(i,j,word)
								|| lower_right(i,j,word)
								|| down(i,j,word)
								|| lower_left(i,j,word)
								|| left(i,j,word)
								|| upper_left(i,j,word)
								|| up(i,j,word)
								|| upper_right(i,j,word);
 
						if (match){
							System.out.print(i);
							System.out.print(" ");
							System.out.println(j);
							i = m+1;
							j = n+1;
							break;
						}
					}
				}
			}
		}
	}

	public static boolean right(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i][j+a]) != Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean lower_right(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i+a][j+a] )!= Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean down(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i+a][j]) != Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean lower_left(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i+a][j-a]) != Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean left(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i][j-a]) != Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean upper_left(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i-a][j-a] )!= Character.toUpperCase(word.charAt(a)) ){
				return false;
			}
		}
		return true;
	}
	public static boolean up(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i-a][j] )!= Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
	public static boolean upper_right(int i, int j, String word){
		for (int a = 0; a< word.length(); a++) {
			if (Character.toUpperCase(grid[i-a][j+a]) != Character.toUpperCase(word.charAt(a))) {
				return false;
			}
		}
		return true;
	}
}