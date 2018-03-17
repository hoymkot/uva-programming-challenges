package backlog.solved;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Judge_ID: 10315 "Poker Hands"
 * @author Hou Kot
 * 
 *         Evaluate poker hands from highest ("Straight Flush") to lowest (
 *         "High Card") This program treat suit indifferently
 *
 */
class Main10315 {

	public static int s2n(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c == 'A')
			return 14;
		if (c == 'T')
			return 10;
		if (c == 'J')
			return 11;
		if (c == 'Q')
			return 12;
		if (c == 'K')
			return 13;
		return -1;
	}

	static int[] b = new int[5]; // black hand number
	static int[] w = new int[5]; // white hand number
	static char[] fb = new char[5]; // black hand suit
	static char[] fw = new char[5]; // white hand suit

	public static int solve() {
		Arrays.sort(b);
		Arrays.sort(w);
		Arrays.sort(fb);
		Arrays.sort(fw);

		int BB = 0;
		int WW = 0;

		int a1 = 0, a2 = 0, b1 = 0, b2 = 0, c1 = 0, c2 = 0, d1 = 0, d2 = 0;

		// straight flush
		if (fb[0] == fb[4] && fb[0] == fb[3] && fb[0] == fb[2] && fb[0] == fb[1] && b[0] + 4 == b[4] && b[0] + 3 == b[3] && b[0] + 2 == b[2] && b[0] + 1 == b[1] )
			BB = b[4];
		if (fw[0] == fw[4] && fw[0] == fw[3] && fw[0] == fw[2] && fw[0] == fw[1] && w[0] + 4 == w[4] && w[0] + 3 == w[3] && w[0] + 2 == w[2] && w[0] + 1 == w[1] )
			WW = w[4];
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0)
			return 0;

		// four of a kind
		if (b[0] == b[3] || b[1] == b[4]) {
			BB = b[1];
		}
		if (w[0] == w[3] || w[1] == w[4]) {
			WW = w[1];
		}
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0)
			return 0;

		// full house
		if (b[0] == b[2] && b[3] == b[4])
			BB = b[0];
		if (b[0] == b[1] && b[2] == b[4])
			BB = b[2];
		if (w[0] == w[2] && w[3] == w[4])
			WW = w[0];
		if (w[0] == w[1] && w[2] == w[4])
			WW = w[2];

		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0)
			return 0;

		// flush
		if (fb[0] == fb[4]) {
			BB = 1;
		}
		if (fw[0] == fw[4]) {
			WW = 1;
		}

		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0) {
			for (int i = 4; i >= 0; i--) {
				if (b[i] > w[i])
					return 1;
				if (w[i] > b[i])
					return -1;
			}
			return 0;
		}

		// straight
		if (b[0] + 4 == b[4] && b[0] + 3 == b[3] && b[0] + 2 == b[2] && b[0] + 1 == b[1] )
			BB = b[4];
		if (w[0] + 4 == w[4] && w[0] + 3 == w[3] && w[0] + 2 == w[2] && w[0] + 1 == w[1] )
			WW = w[4];
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0)
			return 0;

		// three of a kind
		for (int i = 0; i < 3; i++) {
			if (b[i] == b[i + 2])
				BB = b[i];
		}
		for (int i = 0; i < 3; i++) {
			if (w[i] == w[i + 2])
				WW = w[i];
		}
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0)
			return 0;

		// two pair
		if (b[0] == b[1] && b[2] == b[3]) {
			a1 = b[0];
			b1 = b[2];
			c1 = b[4];
			BB = 1;
		}
		if (b[0] == b[1] && b[3] == b[4]) {
			a1 = b[0];
			b1 = b[3];
			c1 = b[2];
			BB = 1;
		}
		if (b[1] == b[2] && b[3] == b[4]) {
			a1 = b[1];
			b1 = b[3];
			c1 = b[0];
			BB = 1;
		}
		if (w[0] == w[1] && w[2] == w[3]) {
			a2 = w[0];
			b2 = w[2];
			c2 = w[4];
			WW = 1;
		}
		if (w[0] == w[1] && w[3] == w[4]) {
			a2 = w[0];
			b2 = w[3];
			c2 = w[2];
			WW = 1;
		}
		if (w[1] == w[2] && w[3] == w[4]) {
			a2 = w[1];
			b2 = w[3];
			c2 = w[0];
			WW = 1;
		}
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0) {
			if (b1 != b2)
				return b1 > b2 ? 1 : -1;
			if (a1 != a2)
				return a1 > a2 ? 1 : -1;
			if (c1 > c2)
				return 1;
			if (c1 < c2)
				return -1;
			return 0;
		}

		// one pair
		if (b[0] == b[1]) {
			a1 = b[0];
			b1 = b[2];
			c1 = b[3];
			d1 = b[4];
			BB = 1;
		}
		if (b[1] == b[2]) {
			a1 = b[1];
			b1 = b[0];
			c1 = b[3];
			d1 = b[4];
			BB = 1;
		}
		if (b[2] == b[3]) {
			a1 = b[2];
			b1 = b[0];
			c1 = b[1];
			d1 = b[4];
			BB = 1;
		}
		if (b[3] == b[4]) {
			a1 = b[3];
			b1 = b[0];
			c1 = b[1];
			d1 = b[2];
			BB = 1;
		}
		if (w[0] == w[1]) {
			a2 = w[0];
			b2 = w[2];
			c2 = w[3];
			d2 = w[4];
			WW = 1;
		}
		if (w[1] == w[2]) {
			a2 = w[1];
			b2 = w[0];
			c2 = w[3];
			d2 = w[4];
			WW = 1;
		}
		if (w[2] == w[3]) {
			a2 = w[2];
			b2 = w[0];
			c2 = w[1];
			d2 = w[4];
			WW = 1;
		}
		if (w[3] == w[4]) {
			a2 = w[3];
			b2 = w[0];
			c2 = w[1];
			d2 = w[2];
			WW = 1;
		}
		if (BB > WW)
			return 1;
		if (BB < WW)
			return -1;
		if (BB > 0 && WW > 0) {
			if (a1 != a2) {
				return a1 > a2 ? 1 : -1;
			}
			if (d1 != d2) {
				return d1 > d2 ? 1 : -1;
			}
			if (c1 != c2) {
				return c1 > c2 ? 1 : -1;
			}
			if (b1 != b2) {
				return b1 > b2 ? 1 : -1;
			}

			return 0;
		}

		// high card
		for (int i = 4; i >= 0; i--) {
			if (b[i] > w[i])
				return 1;
			if (w[i] > b[i])
				return -1;
		}
		return 0;
	}

	public static void main(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;

		while ((s = br.readLine()) != null) {
			String[] cards = s.split(" ");
			if (cards.length != 10)
				break;
			for (int i = 0; i < 5; i++) {
				b[i] = s2n(cards[i].charAt(0));
				fb[i] = cards[i].charAt(1);
			}
			for (int i = 5; i < 10; i++) {
				w[i - 5] = s2n(cards[i].charAt(0));
				fw[i - 5] = cards[i].charAt(1);
			}

			int flag = solve();
			if (flag == 1)
				System.out.println("Black wins.");
			else if (flag == -1)
				System.out.println("White wins.");
			else
				System.out.println("Tie.");
		}
	}
}