package backlog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 *  Note that the test input might supply a huge dictionary, so the program may get TLE error with proper consideration.  
 * @Judge_ID: 10150 "Doublets"
 * 
 */
public class Main10150 {

	public static void print(String s) {
		System.out.println(s);
	}

	static List<String> wordlist = new ArrayList(26000);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while (br.ready() && (line = br.readLine()).equals("") == false) {
			wordlist.add(line);
		}
		int newline = 0;
		while (br.ready() && (line = br.readLine()).equals("") == false) {
			if (newline == 1) {
				print("");
			}
			String[] words = line.split(" ");
			String first = words[0];
			String last = words[1];

			String result;
			queue = new LinkedList<String>();
			parents = new HashMap<String, String>();
			parents.put(first, null);
			result = process(first, last);
			if (result == null)
				print("No solution.");
			else {
				path(result);

			}
			newline = 1;
		}
	}

	static void path(String txt) {
		if (parents.get(txt) == null) {
			print(txt);
			return;
		} else {
			String p = parents.get(txt);
			path(p);
			print(txt);
		}
	}

	static List<String> queue = new LinkedList<String>();

	private static String process(String first, String last) {
		Set<String> keyset = new HashSet<String>();
		for (String word : wordlist) {
			if (doublets(word, first)) {
				keyset.add(word);
			}
		}
		if (keyset.isEmpty())
			return null;

		if (keyset.contains(last)) {
			parents.put(last, first);
			return last;
		}
		for (String key : keyset) {
			if (parents.containsKey(key) == false) {
				parents.put(key, first);
				queue.add(key);
			}
		}
		while (queue.isEmpty() == false) {
			String entry = queue.get(0);
			queue.remove(0);
			String result = process(entry, last);
			if (result != null)
				return result;

		}
		return null;
	}

	private static boolean doublets(String word, String line) {
		if (word.length() != line.length())
			return false;
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != line.charAt(i)) {
				count++;
				if (count > 1) {
					return false;
				}
			}
		}
		return (count == 1);
	}

	static Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	static Map<String, String> parents = new HashMap<String, String>();

}