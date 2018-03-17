package backlog.solved;

/* @JUDGE_ID 30411 10267 JAVA */
/* @BEGIN_OF_SOURCE_CODE */
import java.io.*;
import java.util.*;

class Main10267 {
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
		Main10267 myWork = new Main10267(); // create a dinamic instance
		try {
			myWork.Begin(); // the true entry point
		} catch (Throwable e) {
			throw e;
		}
	}

	void Begin() {
		String input, color = "O";
		StringTokenizer idata;
		Integer m = 0, n = 0, x, y, x1, x2, y1, y2;

		char[][] map = null;
		boolean[][] marked = null;
		while ((input = Main10267.ReadLn(255)) != null) {
			idata = new StringTokenizer(input);
			String command = idata.nextToken();
			switch (command) {
			case "I":
				m = Integer.parseInt(idata.nextToken());
				n = Integer.parseInt(idata.nextToken());
				map = new char[n][];
				for (int r = 0; r < n; r++) {
					map[r] = new char[m];
					for (int c = 0; c < m; c++) {
						map[r][c] = 'O';
					}
				}
				break;
			case "C":
				map = new char[n][];
				for (int r = 0; r < n; r++) {
					map[r] = new char[m];
					for (int c = 0; c < m; c++) {
						map[r][c] = 'O';
					}
				}
				break;
			case "L":
				x = Integer.parseInt(idata.nextToken());
				y = Integer.parseInt(idata.nextToken());
				color = idata.nextToken();
				map[y - 1][x - 1] = color.charAt(0);
				break;
			case "V":
				x = Integer.parseInt(idata.nextToken());
				y1 = Integer.parseInt(idata.nextToken());
				y2 = Integer.parseInt(idata.nextToken());
				if (y1 > y2) {
					int tmp = y2;
					y2 = y1;
					y1 = tmp;
				}
				color = idata.nextToken();
				for (int r = y1 - 1; r < y2; r++) {
					map[r][x - 1] = color.charAt(0);
				}
				break;
			case "H":
				x1 = Integer.parseInt(idata.nextToken());
				x2 = Integer.parseInt(idata.nextToken());
				y = Integer.parseInt(idata.nextToken());
				color = idata.nextToken();
				if (x1 > x2) {
					int tmp = x2;
					x2 = x1;
					x1 = tmp;
				}
				for (int c = x1 - 1; c < x2; c++) {
					map[y - 1][c] = color.charAt(0);
				}
				break;
			case "K":
				x1 = Integer.parseInt(idata.nextToken());
				y1 = Integer.parseInt(idata.nextToken());
				x2 = Integer.parseInt(idata.nextToken());
				y2 = Integer.parseInt(idata.nextToken());
				color = idata.nextToken();
				if (y1 > y2) {
					int tmp = y2;
					y2 = y1;
					y1 = tmp;
				}
				if (x1 > x2) {
					int tmp = x2;
					x2 = x1;
					x1 = tmp;
				}
				for (int r = y1 - 1; r < y2; r++) {
					for (int c = x1 - 1; c < x2; c++) {
						map[r][c] = color.charAt(0);
					}
				}
				break;
			case "F":
				x = Integer.parseInt(idata.nextToken());
				y = Integer.parseInt(idata.nextToken());
				color = idata.nextToken();
				try {
					FillRegion(x - 1, y - 1, color, map);
				} catch (Exception e) {
					return;
				}
				break;
			case "S":
				String filename = idata.nextToken();
				System.out.println(filename);
				PrintMap(map, m, n);
				break;

			case "X":
				return;

			}

		}
	}

	private void PrintMap(char[][] map, Integer m, Integer n) {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println("");
		}

	}

	private void FillRegion(Integer x, Integer y, String color, char[][] map) {
		if (x < 0 || y < 0 || y >= map.length || x >= map[y].length)
			return;
		// System.out.println(Integer.toString(x) + " " + Integer.toString(y));

		char c = color.charAt(0);
		char o = map[y][x];
		if (o == c)
			return;
		map[y][x] = c;

		// left
		if (x > 0 && o == map[y][x - 1])
			FillRegion(x - 1, y, color, map);
		// up
		if (y > 0 && o == map[y - 1][x])
			FillRegion(x, y - 1, color, map);
		// right
		try {
			if (map[y].length - 1 > x && o == map[y][x + 1])
				FillRegion(x + 1, y, color, map);
		} catch (Throwable e) {
			return;
		}
		// // down
		if (map.length - 1 > y && o == map[y + 1][x])
			FillRegion(x, y + 1, color, map);

	}
}
/* @END_OF_SOURCE_CODE */