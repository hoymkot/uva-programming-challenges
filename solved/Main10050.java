package backlog.solved;
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
 * @Judge_ID: 10050 "Hartal"
 * @author Hou Kot
 * 
 */
class Main10050 {

	public static void main(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer cases = Integer.parseInt(br.readLine()); // number of test cases
		try {
			for (Integer c = 0; c < cases; c++) {
				Integer days = Integer.parseInt(br.readLine()); // number of
																// days;
				Map<Integer, Integer> day_map = new HashMap<Integer, Integer>();
				Integer parties = Integer.parseInt(br.readLine()); // number of
																	// parties;

				List<Integer> hartals = new ArrayList<Integer>(parties);
				for (Integer p = 0; p < parties; p++) {
					Integer h = Integer.parseInt(br.readLine());
					hartals.add(h);
				}

				// solve
				for (Integer h : hartals) {
					if ( h % 7 == 0)
						continue;
					int rounds = days / h;
					for (int r = 0; r < rounds; r++) {
						if ((h*r + h) % 7 == 6) continue;
						if ((h*r + h) % 7 == 0) continue;
						day_map.put(h * r + h, 1);
					}
				}
				int count = day_map.keySet().size();
				System.out.println(count);
			}
		} catch (Exception e) {

		}
	}
}