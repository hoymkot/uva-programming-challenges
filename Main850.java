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
 * @Judge_ID: 850 "Crypt Kicker II"
 * 
 */
public class Main850 {

	static Map<Character, Integer> map = new HashMap<Character, Integer>();

	static void init() {
	}

	static String plain = "the quick brown fox jumps over the lazy dog";
	static String plain_pattern = "";
	static List<String> result = new ArrayList<String>();
	Map<Character, Character> dict = new HashMap<Character, Character>();

	static List<Map<Character, Character>> dict_list = new ArrayList<Map<Character, Character>>();

	static String pattern(String text) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		String str = "";
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c != ' ') {
				if (!map.containsKey(c)) {
					map.put(c, (char) ('A' + count));
					count++;
				}
				str += map.get(c);
			} else {
				str += ' ';
			}
			
		}
		return str;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		init();
		plain_pattern = pattern(plain);
		int g = in.nextInt();
		String a = in.nextLine();
		a = in.nextLine();
		int i = 0;
		while (i <g) {
			dict_list = new ArrayList<Map<Character, Character>>();
			List<String> list = new ArrayList<String>();
			result = new ArrayList<String>();

			while (in.hasNext()) {
				a = in.nextLine();
				if (a.equals(""))
					break;
				list.add(a);
				String pattern = pattern(a);
				if (pattern.equals(plain_pattern)) {
					build_dict(a);
				}

			}
			if (dict_list.size() == 0) {
				System.out.println("No solution.");
			} else {
				decrypt(list);
			}
			i++;
			if (i < g)
				System.out.println("");
		}

	}

	private static void build_dict(String a) {
		Map<Character, Character> dict = new HashMap<Character, Character>();
		for (int i = 0; i < a.length(); i++) {
			dict.put(a.charAt(i), plain.charAt(i));
		}
		dict_list.add(dict);
	}

	public static void decrypt(List<String> list) {
		for (Map<Character, Character>  dict : dict_list) {
			if (translate(list, dict)) {
				for (String l : result) {
					System.out.println(l);
				}
				return;
			}
		}
		System.out.println("No solution.");
	}

	private static boolean translate(List<String> list, Map<Character, Character> dict) {
		for(String l : list) {
			String de = "";
			for (int i = 0; i < l.length(); i++) {
				char c = l.charAt(i);
				if (!dict.containsKey(c)) {
					result = new ArrayList<String>();
					return false;
				} else {
					de += dict.get(c);
				}
			}		
			result.add(de);
		}
		return true;
		
	}
}