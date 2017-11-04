package backlog;

// @JUDGE_ID:  1000AA  100  Java  "Easy algorithm"

import java.io.*;
import java.util.*;

class Main706 {
	static void PrintDash(int size) {
		System.out.print(" ");
		for (int row = 0; row < size; row++) {
			System.out.print("-");
		}
		System.out.print(" ");
	}

	static void LeftLine(int size) {
		System.out.print("|");
		for (int z = 0; z < size; z++) {
			System.out.print(" ");
		}
		System.out.print(" ");
	}

	static void RightLine(int size) {
		System.out.print(" ");
		for (int z = 0; z < size; z++) {
			System.out.print(" ");
		}
		System.out.print("|");
		// System.out.print(" ");
	}

	static void BothLine(int size) {
		System.out.print("|");
		for (int z = 0; z < size; z++) {
			System.out.print(" ");
		}
		System.out.print("|");
		// System.out.print(" ");
	}

	static void PrintSpace(int size) {
		System.out.print(" ");
		for (int row = 0; row < size; row++) {
			System.out.print(" ");
		}
		System.out.print(" ");
	}

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
		Main706 myWork = new Main706(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {
		String input;
		StringTokenizer idata;

		while ((input = Main706.ReadLn(255)) != null) {
			idata = new StringTokenizer(input);
			int size = Integer.parseInt(idata.nextToken());
			String number = idata.nextToken();
			if (size == 0 && number.equals("0"))
				break;
			for (int line = 0; line < 5; line++) {
				switch (line) {
				case 0:
					for (int idx = 0; idx < number.length(); idx++) {
						if (idx != 0)
							System.out.print(" ");
						char token = number.charAt(idx);
						switch (token) {
						case '0':
							Main706.PrintDash(size);
							break;
						case '1':
							Main706.PrintSpace(size);
							break;
						case '2':
							Main706.PrintDash(size);
							break;
						case '3':
							Main706.PrintDash(size);
							break;
						case '4':
							Main706.PrintSpace(size);
							break;
						case '5':
							Main706.PrintDash(size);
							break;
						case '6':
							Main706.PrintDash(size);
							break;
						case '7':
							Main706.PrintDash(size);
							break;
						case '8':
							Main706.PrintDash(size);
							break;
						case '9':
							Main706.PrintDash(size);
							break;
						}
					}
					System.out.println("");
					break;
				case 1:
					for (int s = 0; s < size; s++) {
						for (int idx = 0; idx < number.length(); idx++) {
							if (idx != 0)
								System.out.print(" ");
							char token = number.charAt(idx);
							switch (token) {
							case '0':
								Main706.BothLine(size);
								break;
							case '1':
								Main706.RightLine(size);
								break;
							case '2':
								Main706.RightLine(size);
								break;
							case '3':
								Main706.RightLine(size);
								break;
							case '4':
								Main706.BothLine(size);
								break;
							case '5':
								Main706.LeftLine(size);
								break;
							case '6':
								Main706.LeftLine(size);
								break;
							case '7':
								Main706.RightLine(size);
								break;
							case '8':
								Main706.BothLine(size);
								break;
							case '9':
								Main706.BothLine(size);
								break;
							}

						}
						System.out.println("");
					}
					break;
				case 2:

					for (int idx = 0; idx < number.length(); idx++) {
						if (idx != 0)
							System.out.print(" ");
						char token = number.charAt(idx);
						switch (token) {
						case '0':
							Main706.PrintSpace(size);
							break;
						case '1':
							Main706.PrintSpace(size);
							break;
						case '2':
							Main706.PrintDash(size);
							break;
						case '3':
							Main706.PrintDash(size);
							break;
						case '4':
							Main706.PrintDash(size);
							break;
						case '5':
							Main706.PrintDash(size);
							break;
						case '6':
							Main706.PrintDash(size);
							break;
						case '7':
							Main706.PrintSpace(size);
							break;
						case '8':
							Main706.PrintDash(size);
							break;
						case '9':
							Main706.PrintDash(size);
							break;
						}

					}
					System.out.println("");
					break;
				case 3:
					for (int s = 0; s < size; s++) {
						for (int idx = 0; idx < number.length(); idx++) {
							if (idx != 0)
								System.out.print(" ");
							char token = number.charAt(idx);
							switch (token) {
							case '0':
								Main706.BothLine(size);
								break;
							case '1':
								Main706.RightLine(size);
								break;
							case '2':
								Main706.LeftLine(size);
								break;
							case '3':
								Main706.RightLine(size);
								break;
							case '4':
								Main706.RightLine(size);
								break;
							case '5':
								Main706.RightLine(size);
								break;
							case '6':
								Main706.BothLine(size);
								break;
							case '7':
								Main706.RightLine(size);
								break;
							case '8':
								Main706.BothLine(size);
								break;
							case '9':
								Main706.RightLine(size);
								break;
							}

						}
						System.out.println("");
					}
					break;
				case 4:
					for (int idx = 0; idx < number.length(); idx++) {
						if (idx != 0)
							System.out.print(" ");
						char token = number.charAt(idx);
						switch (token) {
						case '0':
							Main706.PrintDash(size);
							break;
						case '1':
							Main706.PrintSpace(size);
							break;
						case '2':
							Main706.PrintDash(size);
							break;
						case '3':
							Main706.PrintDash(size);
							break;
						case '4':
							Main706.PrintSpace(size);
							break;
						case '5':
							Main706.PrintDash(size);
							break;
						case '6':
							Main706.PrintDash(size);
							break;
						case '7':
							Main706.PrintSpace(size);
							break;
						case '8':
							Main706.PrintDash(size);
							break;
						case '9':
							Main706.PrintDash(size);
							break;
						}
					}
					System.out.println("");
					break;
				}

			}
			System.out.println("");
		}
	}

}