package backlog;
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
 * @Judge_ID: 10258 "Contest Scoreboard"
 * @author Hou Kot (self)
 * 
 */
class Main10258 {

	Map<Integer, Team> map = new HashMap<Integer, Team>();

	Main10258() {
		reset();
	}

	void reset() {
		map = new HashMap<Integer, Team>();
	}

	class Team implements Comparable {
		public Integer name;
		public Integer time = 0;
		public Set<Integer> pset = new HashSet<Integer>(100); // set of solved
		Map<Integer, Integer> incorrect= new HashMap<Integer, Integer>();
		// problems;

		Team(Integer team) {
			name = team;
		}

		void incorrect(Integer problem) {
			if (false == pset.contains(problem)) {
				Integer count = incorrect.get(problem);
				if (count == null) 
					count = 20;
				else 
					count += 20;
				incorrect.put(problem, count);
			}
		}

		void solved(Integer problem, Integer min) {
			if (pset.contains(problem) == false) {
				time += min;
				this.pset.add(problem);
				Integer count = incorrect.get(problem);
				if (count == null) 
					count = 0;
				time += count;
			}
		}

		@Override
		public int compareTo(Object arg0) {
			Team that = (Team) arg0;
			if (this.pset.size() < that.pset.size()) {
				return 1;
			} else if (this.pset.size() > that.pset.size()) {
				return -1;
			} else {
				if (this.time > that.time) {
					return 1;
				} else if ( this.time < that.time) {
					return -1;
				} else {
					if (this.name < that.name) {
						return -1;
					} else if (this.name > that.name){
						return 1;
					} else {
						return 0;
					}
				}
				
			}
			
		}

	}

	public static void main(String[] abc) throws IOException {
		Main10258 m = new Main10258();
		m.solve(abc);
	}

	public void solve(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer cases = Integer.parseInt(br.readLine()); // cases
		br.readLine();
		try {
			for (Integer c = 0; c < cases; c++) {
				String line = null;
				while (br.ready() && (line = br.readLine()).equals("") == false) {
					String[] inputs = line.split(" ");
					Integer team = Integer.parseInt(inputs[0]);
					Integer problem = Integer.parseInt(inputs[1]);
					Integer time = Integer.parseInt(inputs[2]);
					String L = inputs[3];
					Team t = map.get(team);
					if (t == null) {
						t = new Team(team);
						map.put(team, t);
					}
					if (L.equals("C")) {
						t.solved(problem, time);
					}
					if (L.equals("I")) {
						t.incorrect(problem);
					}
				}

				Set<Team> sorted = new TreeSet<Team>(map.values());
				for (Team t : sorted) {
					System.out.println(t.name + " " + t.pset.size() + " " + t.time);
				}

				reset();
				if (c + 1 < cases) {
					System.out.println("");
				}

			}

		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println(e);

		}
	}

}