package backlog;

// UVA online judge code sample

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.print.attribute.IntegerSyntax;

class Main10142 {

	static String ReadLn(int maxLg) // utility function to read from stdin
	{
		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
		String line = "";

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n'))
					break;
				lin[lg++] += car;
			}
		} catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0))
			return (null); // eof
		return (new String(lin, 0, lg));
	}

	public static void main(String args[]) // entry point from OS
	{
		Main10142 myWork = new Main10142(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {

		String input;
		StringTokenizer idata;
		input = Main10142.ReadLn(255);
		idata = new StringTokenizer(input);
		int T = Integer.parseInt(idata.nextToken());
		input = Main10142.ReadLn(255);
		String endingSeperator = "";

		while ((T--) > 0) {
			input = Main10142.ReadLn(255);
			idata = new StringTokenizer(input);
			int n = Integer.parseInt(idata.nextToken());

			List<String> names = new ArrayList<String>(n);
			List<Boolean> eliminated = new ArrayList<Boolean>(n);
			for (int i = 0; i < n; i++)
				eliminated.add(false);

			for (int i = 0; i < n; i++) {
				input = Main10142.ReadLn(255);
				names.add(input);
			}

			List<List<Integer>> ratings = new ArrayList<List<Integer>>(n);
			while ((input = Main10142.ReadLn(255)) != null && !input.trim().equals("")) {
				idata = new StringTokenizer(input);
				List<Integer> list = new ArrayList<Integer>();
				while (idata.hasMoreTokens()) {
					list.add(Integer.parseInt(idata.nextToken()) - 1);
				}
				ratings.add(list);
			}

			int numRatings = ratings.size();
			List<Integer> posInRatings = new ArrayList<Integer>(numRatings);
			for (int i = 0; i < numRatings; i++)
				posInRatings.add(0);

			int winner = -1;

			List<Integer> count = new ArrayList<Integer>(n);
			for (int i = 0; i < n; i++)
				count.add(0);

			for (int i = 0; i < numRatings; ++i) {
				int idx = ratings.get(i).get(0);
				count.set(idx, count.get(idx) + 1);
			}

			while (winner == -1) {

				for (int i = 0; i < numRatings; ++i) {
					Boolean changed = false;
					while (eliminated.get(ratings.get(i).get(posInRatings.get(i)))) {
						posInRatings.set(i, 1 + posInRatings.get(i));
						changed = true;
					}

					if (changed) {
						int idx = ratings.get(i).get(posInRatings.get(i));
						count.set(idx, count.get(idx) + 1);
					}
				}

				int highest = 0;

				int lowest = 1000;

				for (int i = 0; i < n; ++i) {
					if (eliminated.get(i))
						continue;
					if (count.get(i) > highest)
						highest = count.get(i);

					if (count.get(i) < lowest)
						lowest = count.get(i);
				}

				if (highest == lowest || highest * 2 > numRatings)
					winner = highest;

				else {
					for (int i = 0; i < n; ++i)
						if (count.get(i) == lowest)
							eliminated.set(i, true);
				}
			}

			System.out.print(endingSeperator);
			endingSeperator = "\n";

			for (int i = 0; i < n; ++i)
				if (count.get(i) == winner && !eliminated.get(i))
					System.out.print(names.get(i)+"\n");
		}

	}

}