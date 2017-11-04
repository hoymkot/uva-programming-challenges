package backlog;
import java.io.*;
import java.util.*;

class Main10137 {
	static String ReadLn(int maxLg) // utility function to read from stdin
	{
		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
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
		Main10137 myWork = new Main10137(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {

		String number_of_students = (new StringTokenizer(Main10137.ReadLn(255))).nextToken();
		while (!number_of_students.equals("0")) {
			int number = Integer.parseInt(number_of_students);
			double[] students = new double[number];
			double total = 0, avg = 0, respPos = 0, respNeg = 0;
			for (int i = 0; i < number; i++) {
				String amount_spent = (new StringTokenizer(Main10137.ReadLn(255))).nextToken();
				students[i] = Double.parseDouble(amount_spent);
				total += students[i];

			}
			avg = total / number;

			for (int i = 0; i < number; i++) {

				double dif = (double) (long) ((students[i] - avg) * 100.0) / 100.0;
				if (dif > 0)
					respPos += dif;
				else
					respNeg += dif;
			}
		    double resp = (-respNeg > respPos) ? -respNeg : respPos;
		    resp = (resp<0)?-resp:resp;
			 System.out.printf("$%.2f\n" , resp);
			number_of_students = (new StringTokenizer(Main10137.ReadLn(255))).nextToken();
		}

	}
}