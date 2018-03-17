package backlog.solved;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.print.attribute.IntegerSyntax;

/**
 * @Judge_ID: 10044 "Erdo's Numbers"
 * @author Hou Kot (self)
 * 
 */
class Main10044 {

	String[] deck;

	public static void main(String[] abc) throws IOException {
		Main10044 m = new Main10044();
		m.solve(abc);
	}

	public void solve(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer cases = Integer.parseInt(br.readLine()); // cases

		try {
			for (Integer c = 0; c < cases; c++) {
				map = new HashMap<String, Author>();
				// access = new HashMap<String, Boolean>();
				String line = br.readLine();
				if (line.equals("") ) line = br.readLine();
				String[] pn = line.split(" ");
				Integer p = 0, n = 0;
				p = Integer.parseInt(pn[0]);
				n = Integer.parseInt(pn[1]);
				for (Integer i = 0; i < p; i++) {
					line = br.readLine();
					List<String> authors = Arrays.asList(line.split("\\., "));
					for (int j = 0; j < authors.size() - 1; j++) {
						authors.set(j, authors.get(j) + ".");
					}
					String[] last = authors.get(authors.size() - 1).split(":");
					authors.set(authors.size() - 1, last[0]);
					for (String a : authors) {
						if (map.get(a) == null) {
							map.put(a, new Author(a));
						}
						map.get(a).addAssoc(authors);
					}
				}

				// build map;

				map.get("Erdos, P.").setErdo(0);

				System.out.println("Scenario " + (c + 1));
				for (int i = 0; i < n; i++) {
					String a = br.readLine();
					if (map.get(a) == null) {
						System.out.println(a + " infinity");
					} else {
						Author author = map.get(a);

						Integer erdo = author.getErdo();
						if (erdo == null) {
							System.out.println(a + " infinity");
						} else {
							System.out.println(a + " " + erdo);
						}
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
	}

	static Map<String, Author> map = new HashMap<String, Author>();

	public class Author {
		public String name;
		public Set<String> assoc = new HashSet<String>();
		public Integer erdo = null;

		Author(String name) {
			this.name = name;
		}

		public void addAssoc(List<String> authors) {
			for (String a : authors) {
				if (a.equals(name) == false)
					;
				assoc.add(a);
			}
		}

		public void setErdo(int erdo) {
			this.erdo = erdo;
			for (String a : assoc) {
				Author author = map.get(a);
				if (author.getErdo() == null) {
					author.setErdo(erdo + 1);
				}
				if (author.getErdo() > erdo + 1) {
					author.setErdo(erdo + 1);
				}

			}

		}

		public Integer getErdo() {
			return erdo;

		}

	}

}