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
 * @Judge_ID: 10188 "Automated Judge Script"
 * 
 */
public class Main10188 {

	static Map<Character, Integer> map = new HashMap<Character, Integer>();

	static void init() {
	}

	static String plain = "the quick brown fox jumps over the lazy dog";
	static String plain_pattern = "";
	static List<String> result = new ArrayList<String>();
	Map<Character, Character> dict = new HashMap<Character, Character>();

	static List<String> inputs = new ArrayList<String>();
	static List<String> outputs = new ArrayList<String>();
	static List<Map<Character, Character>> dict_list = new ArrayList<Map<Character, Character>>();

	static int run = 1;

	public static void print(String s) {
		System.out.println("Run #" + run + ": " + s);
		run++;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		while (true) {
			int nline = in.nextInt();
			if (nline == 0) {
				System.exit(0);
			}
			in.nextLine();
			inputs = new ArrayList<String>();
			outputs = new ArrayList<String>();
			for (int i = 0; i < nline; i++) {
				inputs.add(in.nextLine());
			}
			int mline = in.nextInt();
			in.nextLine();

			for (int i = 0; i < mline; i++) {
				outputs.add(in.nextLine());
			}

			process(inputs, outputs);

		}
	}

	private static void process(List<String> inputs, List<String> outputs) {
		boolean perfect = true;
		if (inputs.size() == outputs.size()) {
			for (int i = 0; i < inputs.size(); i++) {
				if (inputs.get(i).equals(outputs.get(i)) == false) {
					perfect = false;
					break;
				}
			}
		} else {
			perfect = false;
		}
		if (perfect) {
			print("Accepted");
			return;
		} else {
			String in = "";
			String out = "";
			for (int i = 0; i < inputs.size(); i++) {
				in += inputs.get(i);
			}
			for (int i = 0; i < outputs.size(); i++) {
				out += outputs.get(i);
			}
			in = number(in);
			out = number(out);
			if (in.equals(out)) {
				print("Presentation Error");

			} else {
				print("Wrong Answer");
			}
		}

	}

	private static String number(String string) {
		String num = "";
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c >= '0' && c <= '9') {
				num += c;
			}
		}
		return num;
	}

}