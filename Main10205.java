package backlog;
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
 * @Judge_ID: 10205 "Stack 'em Up"
 * @author Hou Kot (self)
 * 
 */
class Main10205 {

	String[] deck;

	public void newDeck() {
		deck = new String[52];
		deck[0] = "2 of Clubs";
		deck[1] = "3 of Clubs";
		deck[2] = "4 of Clubs";
		deck[3] = "5 of Clubs";
		deck[4] = "6 of Clubs";
		deck[5] = "7 of Clubs";
		deck[6] = "8 of Clubs";
		deck[7] = "9 of Clubs";
		deck[8] = "10 of Clubs";
		deck[9] = "Jack of Clubs";
		deck[10] = "Queen of Clubs";
		deck[11] = "King of Clubs";
		deck[12] = "Ace of Clubs";
		deck[13] = "2 of Diamonds";
		deck[14] = "3 of Diamonds";
		deck[15] = "4 of Diamonds";
		deck[16] = "5 of Diamonds";
		deck[17] = "6 of Diamonds";
		deck[18] = "7 of Diamonds";
		deck[19] = "8 of Diamonds";
		deck[20] = "9 of Diamonds";
		deck[21] = "10 of Diamonds";
		deck[22] = "Jack of Diamonds";
		deck[23] = "Queen of Diamonds";
		deck[24] = "King of Diamonds";
		deck[25] = "Ace of Diamonds";
		deck[26] = "2 of Hearts";
		deck[27] = "3 of Hearts";
		deck[28] = "4 of Hearts";
		deck[29] = "5 of Hearts";
		deck[30] = "6 of Hearts";
		deck[31] = "7 of Hearts";
		deck[32] = "8 of Hearts";
		deck[33] = "9 of Hearts";
		deck[34] = "10 of Hearts";
		deck[35] = "Jack of Hearts";
		deck[36] = "Queen of Hearts";
		deck[37] = "King of Hearts";
		deck[38] = "Ace of Hearts";
		deck[39] = "2 of Spades";
		deck[40] = "3 of Spades";
		deck[41] = "4 of Spades";
		deck[42] = "5 of Spades";
		deck[43] = "6 of Spades";
		deck[44] = "7 of Spades";
		deck[45] = "8 of Spades";
		deck[46] = "9 of Spades";
		deck[47] = "10 of Spades";
		deck[48] = "Jack of Spades";
		deck[49] = "Queen of Spades";
		deck[50] = "King of Spades";
		deck[51] = "Ace of Spades";
	}

	public static void main(String[] abc) throws IOException {
		Main10205 m = new Main10205();
		m.solve(abc);
	}

	public void solve(String[] abc) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer cases = Integer.parseInt(br.readLine()); // cases
		br.readLine();
		try {
			for (int c = 0; c < cases; c++) {
				newDeck();
				Integer no = Integer.parseInt(br.readLine());
				String[][] shuffles = new String[no][];
				for (int s = 0; s < no; s++){
					String action = br.readLine();
					while (action.split(" ").length < 52) {
						action = action + " " + br.readLine();
					}
					shuffles[s] = action.split(" ");
				}
				String line;
				while ( br.ready() && ( (line = br.readLine()).equals("") == false)) {
					String[] new_deck = new String[52];
					Integer action = Integer.parseInt(line) -1;
					for(int i = 0 ; i < shuffles[action].length ; i++) {
						new_deck[i] = deck[Integer.parseInt(shuffles[action][i])-1];
					}
					deck = new_deck;
				}
				
				for (int i = 0 ; i < deck.length ; i++ ){
					System.out.println(deck[i]);
				}
				if (c+1 < cases){
					System.out.println("");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
	}

}