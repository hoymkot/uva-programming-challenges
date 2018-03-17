package backlog.solved;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * use bit mask and dp to solve this problem
 * @Judge_ID: 10149 "Yahtzee"
 * 
 */
public class Main10149 {

	static final int MAX_BONUS_SUM = 115;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		long t1 = System.currentTimeMillis();
		while (in.hasNextLine()) {
			int[][] round = new int[13][5];
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 5; j++) {
					round[i][j] = in.nextInt();
				}
			}
			in.nextLine();
			int[][] point = new int[13][13];
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 13; j++) {
					point[i][j] = getPoint(round[i], j);
				}
			}

			int[][] state = new int[1 << 13][MAX_BONUS_SUM + 1];
			int[][] newState = new int[1 << 13][MAX_BONUS_SUM + 1];
			for (int j = 0; j < (1 << 13); j++) {
				Arrays.fill(state[j], -1);
				Arrays.fill(newState[j], -1);
			}
			state[0][0] = 0;
			int[][][] choice = new int[13][1 << 13][MAX_BONUS_SUM + 1]; // looks like the score map of various combination of category and round
			for (int i = 0; i < 13; i++) { // i is round number, note that we build the memory map from former to latter, and retrieve value from latter to former.
				// find all j such that the number of bit set is the same as round number (i)
				for (int j = 0; j < (1 << 13); j++) {
					int usedSlot = 0;
					for (int b = 0; b < 13; b++) { 
						if (((1 << b) & j) != 0) {
							usedSlot++;
						}
					}
					if (usedSlot != i + 1) { // j should has the same number of bits as the current category (i)
						continue;
					}
					for (int b = 0; b < 13; b++) { // b is category number
						if (((1 << b) & j) != 0) { // find whether current category bit is set in j
							int j2 = (~(1 << b) & j); // keep every bit of j except the bit currently set by 1 << b 
							// look like s stands for score, get by trial and error
							// note that s is for check bonus for the first six categories only
							for (int s = 0; s <= MAX_BONUS_SUM; s++) { 
								int oldSum; // reset every time
								if (b < 6) { 
									// since sum is never less than individual, the purpose is to 
									// skip s to individual values to reduce computation
									if (s < point[i][b]) {
										s = point[i][b] - 1; // s will add 1 to equal to point[i][b] in next iteration (s++)
										continue;
									}
									// find the old sum value set in previous iteration ( from a long time ago)
									// find the s such that s - point[i][b] = oldSum where oldSum is set in previous iteration
									oldSum = s - point[i][b]; 
								} else { 
									oldSum = s; // sum of this six is unchanged at this point because category (b) is greater than 6 
								}
								if (state[j2][oldSum] < 0) { 
									// oldsum == s == 0 if b is not in the first six category and j has only one bit set.
									// if old sum is set in previous iteration state[j2][oldSum] has value;
									// if this old sum slot is not set in previous iteration or in initialization. the loop always skip
									continue;
								}
								int newPoint;
								// if previously accumulated sum + current sum value is 63. note that only scores from the first category will add to this "sum"
								if (s >= 63 && oldSum < 63) {  
									newPoint = 35 + state[j2][oldSum] + point[i][b];
								} else {
									newPoint = state[j2][oldSum] + point[i][b];
								}
								// that newState[j][s] is reset for every round of iteration. so it has values only related to this round
								// set the optimal category to new state, which category would yield the highest new point
								// find the best category for this round, give the categories used. 
								// note that the optimal solution is found, given the current round and category selected
								if (newPoint > newState[j][s]) {
									choice[i][j][s] = b;
									newState[j][s] = newPoint; // setting oldSum checker here for iteration afterward
								}
							}
						}
					}
				}

				// put result from current iteration to state[i][j] for use in next iteration;
				for (int j = 0; j < (1 << 13); j++) {
					for (int s = 0; s <= MAX_BONUS_SUM; s++) {
						state[j][s] = newState[j][s];
					}
					Arrays.fill(newState[j], -1);
				}
			}

			int index = (1 << 13) - 1; // 8192-1 = 8191 (this has 13 bit all set);
			int maxPoint = -1;
			int sum = 0;
			// all 13 bit set mean, all categories are applied, find the one with the real maxPoint, 
			// note that s is the sum of the first six category, we find both at the same time 
			for (int s = 0; s <= MAX_BONUS_SUM; s++) {
				if (state[index][s] > maxPoint) {
					maxPoint = state[index][s];
					sum = s;
				}
			}

			// obviously, sum is related to the total of the first six categories
			boolean bonus = (sum >= 63);
			int[] mapping = new int[13];
			// for all rounds, from latter to former, note that we build memory map from former to latter
			for (int i = 12; i >= 0; i--) { 
				// choice[i][index][sum] find category (optimal), i is round
				mapping[choice[i][index][sum]] = i;
				// note if category (choice[i][index][sum]) is not within 1-6, p is always 0;
				int p = 0;
				// reduce sum when category reach 6 to 1; before that sum is unchanged
				if (choice[i][index][sum] < 6) { 
					// if current round should apply a category within (1-6)
					p = point[i][choice[i][index][sum]];
				}
				
				// ok, this is backtrack operation. + dynamic programming
				// turn off the bit for current category( choice[i][index][sum] )
				// keep doing until all 13 bit are unset
				index = (~(1 << choice[i][index][sum]) & index); 
				sum -= p;
			}

			// i = category , mapping find round by category(i); point find point by round, category
			for (int i = 0; i < 13; i++) {
				System.out.print(point[mapping[i]][i] + " ");
			}
			if (bonus) {
				System.out.print("35 ");
			} else {
				System.out.print("0 ");
			}
			System.out.println(maxPoint);
		}
		long t2 = System.currentTimeMillis();
		// System.out.println(t2 - t1);
	}

	static int getPoint(int[] round, int category) {
		if (category < 6) {
			int sum = 0;
			for (int i = 0; i < round.length; i++) {
				if (round[i] == category + 1) {
					sum += category + 1;
				}
			}
			return sum;
		}
		int sum = 0;
		int[] count = new int[7];
		for (int i = 0; i < round.length; i++) {
			sum += round[i];
			count[round[i]]++;
		}
		if (category == 6) {
			return sum;
		} else if (category == 7) {
			for (int i = 1; i <= 6; i++) {
				if (count[i] >= 3) {
					return sum;
				}
			}
		} else if (category == 8) {
			for (int i = 1; i <= 6; i++) {
				if (count[i] >= 4) {
					return sum;
				}
			}
		} else if (category == 9) {
			for (int i = 1; i <= 6; i++) {
				if (count[i] >= 5) {
					return 50;
				}
			}
		} else if (category == 10) {
			for (int i = 1; i <= 3; i++) {
				if (isStraight(count, i, 4)) {
					return 25;
				}
			}
		} else if (category == 11) {
			for (int i = 1; i <= 2; i++) {
				if (isStraight(count, i, 5)) {
					return 35;
				}
			}
		} else if (category == 12) {
			for (int i = 1; i <= 6; i++) {
				if (count[i] >= 5) {
					return 40;
				}
			}
			for (int i = 1; i <= 6; i++) {
				for (int j = 1; j <= 6; j++) {
					if (i != j && count[i] == 3 && count[j] == 2) {
						return 40;
					}
				}
			}
		}
		return 0;
	}

	static boolean isStraight(int[] count, int start, int num) {
		for (int i = start; i < start + num; i++) {
			if (count[i] == 0) {
				return false;
			}
		}
		return true;
	}
}