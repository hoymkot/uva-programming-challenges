package backlog.solved;

// UVA online judge code sample

import java.io.*;
import java.util.*;

class Main10033 {
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
		Main10033 myWork = new Main10033(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	int[] registers = new int[10];
	int[] ram = new int[1000];

	void reset() {
		for (int i = 0; i < 10; i++) {
			registers[i] = 0;
		}
		for (int i = 0; i < 1000; i++) {
			ram[i] = 0;
		}
	}
	// String padding(String result){
	// if (null == result) return null;
	// if (result.length() == 0) {
	// result = "000";
	// }
	// if (result.length() == 1) {
	// result = "00" + result;
	// }
	// if (result.length() == 2) {
	// result = "0" + result;
	// }
	// return result;
	// }

	void Begin() {
		String input;
		StringTokenizer idata;
		input = Main10033.ReadLn(255);
		idata = new StringTokenizer(input);
		int cases = Integer.parseInt(idata.nextToken());
		reset();
		input = Main10033.ReadLn(255);
		while (cases > 0) {
			reset();
			int r = 0;
			while ((input = Main10033.ReadLn(255)) != null && !input.trim().equals("")) {
				idata = new StringTokenizer(input);
				ram[r] = Integer.parseInt(idata.nextToken());
				r++;
			}
			int i = 0;
			int execution = 0;
			while (true) {
				execution++;
				if (100 == ram[i])
					break;
				int action = ram[i] / 100;
				Integer d = ram[i] % 100 / 10;
				Integer n = ram[i] % 10;
				switch (action) {
				case 2:
					registers[d] = n;
					break;
				case 3:
					registers[d] = (registers[d] + n) % 1000;
					break;
				case 4:
					registers[d] = (registers[d] * n) % 1000;
					break;
				case 5:
					registers[d] = registers[n];
					break;
				case 6:
					registers[d] = (registers[d] + registers[n]) % 1000;
					break;
				case 7:
					registers[d] = (registers[d] * registers[n]) % 1000;
					break;
				case 8:
					registers[d] = ram[registers[n]];
					break;
				case 9:
					ram[registers[n]] = registers[d];
					break;
				case 0:
					if (0 != registers[n])
						i = registers[d] - 1;
					break;
				}
				i++;
			}
			// new case, execute, print execution, reset;
			System.out.println(execution);
			cases--;
			if (cases > 0)
				System.out.println("");
		}
	}
}