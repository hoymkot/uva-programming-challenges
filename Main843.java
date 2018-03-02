package backlog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.print.attribute.IntegerSyntax;

/**
 * @Judge_ID: 843 "Crypt Kicker"
 * @author Hou Kot (coding strife)
 * 
 */
class Main843 {

	public int asci_start = 0;
	public int asci_end = 26;
	public int char_available=26;
	public String[] dict = null;
	public char[][] Mapping;

	public Main843() {
		Mapping = new char[2][];
		for (int j = 0; j < 2; j++) {
			Mapping[j] = new char[char_available];
		}
	}

	public static void main(String[] abc) throws IOException {
		Main843 m = new Main843();
		m.solve(abc);
	}

	public void solve(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer words = Integer.parseInt(br.readLine()); // words available
		dict = new String[words];
		try {
			for (Integer i = 0; i < words; i++) {
				dict[i] = br.readLine();
			}

			while (br.ready()) {
				String text = br.readLine();
				initMapping();
				String[] tokens = text.split(" ");
				if (tokens.length == 0) {
					System.out.println("");
					continue;
				}
				match(tokens, 0);
				outputModified(tokens);
				System.out.print("\n");
				
			}

		} catch (IOException e) {
			
		}
		finally {

		}
	}

	private void outputModified(String[] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			for (int j = 0; j < tokens[i].length(); j++) {
				System.out.print(Mapping[1][tokens[i].charAt(j)- 'a']);
			}
			if (i != (tokens.length - 1))
				System.out.print(" ");
		}
	}

	private boolean match(String[] tokens, int start) {
		char[][] OriginalMapping = new char[2][];
		for (int j = 0; j < 2; j++) {
			OriginalMapping[j] = new char[char_available];
			for (int i = asci_start; i < asci_end; i++)
				OriginalMapping[j][i] = Mapping[j][i];
		}
		for (int i = 0; i < dict.length; i++) {
			if (tokens[start].length() == dict[i].length()) {
				if (mapWorld(dict[i], tokens[start])) {
					if (start == (tokens.length - 1))
						return true;
					if (match(tokens, start + 1))
						return true;
					else {
						for (int j = 0; j < 2; j++) {
							for (int d = asci_start; d < asci_end; d++)
								Mapping[j][d] = OriginalMapping[j][d];

						}
					}
				}
			}
		}
		return false;
	}

	private boolean mapWorld(String dWord, String tWord) {
		char[][] OriginalMapping = new char[2][];
		for (int j = 0; j < 2; j++) {
			OriginalMapping[j] = new char[char_available];
			for (int i = asci_start; i < asci_end; i++)
				OriginalMapping[j][i] = Mapping[j][i];
		}
		for (int i = 0; i < dWord.length(); i++) {
			if ((Mapping[0][dWord.charAt(i) - 'a'] == '*' && Mapping[1][tWord.charAt(i)-'a'] == '*')
					|| (Mapping[0][dWord.charAt(i)-'a'] == tWord.charAt(i)
							&& Mapping[1][tWord.charAt(i) -'a'] == dWord.charAt(i))) {
				Mapping[0][dWord.charAt(i)-'a'] = tWord.charAt(i);
				Mapping[1][tWord.charAt(i)-'a'] = dWord.charAt(i);
			} else {
				// collision , revert mapping;
				Mapping = OriginalMapping;
				return false;
			}
		}
		return true;
	}

	private void initMapping() {
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 26; i++)
				Mapping[j][i] = '*';
		}
	}
}