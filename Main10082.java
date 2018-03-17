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
 * @Judge_ID: 10082 "WERTYU"
 * 
 */
public class Main10082 {

	
	static Map<String, String> map = new HashMap<String, String>();
	static void init(){
		map.put("1", "`");
		map.put("2", "1");
		map.put("3", "2");
		map.put("4", "3");
		map.put("5", "4");
		map.put("6", "5");
		map.put("7", "6");
		map.put("8", "7");
		map.put("9", "8");
		map.put("0", "9");
		map.put("-", "0");
		map.put("=", "-");
		map.put("W", "Q");
		map.put("E", "W");
		map.put("R", "E");
		map.put("T", "R");
		map.put("Y", "T");
		map.put("U", "Y");
		map.put("I", "U");
		map.put("O", "I");
		map.put("P", "O");
		map.put("[", "P");
		map.put("]", "[");
		map.put("\\", "]");
		map.put("S", "A");
		map.put("D", "S");
		map.put("F", "D");
		map.put("G", "F");
		map.put("H", "G");
		map.put("J", "H");
		map.put("K", "J");
		map.put("L", "K");
		map.put(";", "L");
		map.put("'", ";");
		map.put("'", ";");
		map.put("X", "Z");
		map.put("C", "X");
		map.put("V", "C");
		map.put("B", "V");
		map.put("N", "B");
		map.put("M", "N");
		map.put(",", "M");
		map.put(".", ",");
		map.put("/", ".");
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		init();
		while (in.hasNext()) {

			String line = in.nextLine();
			StringBuffer b = new StringBuffer(line);
			for (int i = 0 ; i < b.length(); i++){
				if (b.charAt(i) != ' ') {
					b.replace(i, i+1, map.get(b.substring(i, i+1)));
				}
			}
			System.out.println(b);

		}

	}

}