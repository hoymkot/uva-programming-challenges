package backlog;

import java.io.*;
import java.util.*;

class Main10196 {
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
		Main10196 myWork = new Main10196(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	boolean empty(char[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ('.' != board[i][j])
					return false;
			}
		}
		return true;
	}

	char[][] read() {
		char[][] board = new char[8][8];
		String input;
		int i = 0;
		while ((input = Main10196.ReadLn(255)) != null && !(input.trim().equals(""))) {
			try {
			board[i] = new char[8];
			} catch (Throwable e){
				System.out.println(e.toString());
			}
			for (int j = 0; j < 8; j++) {
				board[i][j] = input.charAt(j);
			}
			i++;
		}
		return board;
	}

	void Begin() {
		Integer c = 0;
		while (true) {
			c++;
			char[][] board = read();
			if (empty(board))
				return;
			String king = check(board);
			System.out.println("Game #" + c + ": " + king + " king is in check.");

		}

	}

	String white = "white";
	String black = "black";
	String no = "no";

	String rook(char[][] board, int i, int j, char king, String msg) {
		for (int v = i - 1; v > -1; v--) {
			if (king == board[v][j])
				return msg;
			if ('.' != board[v][j])
				break;
		} // up
		for (int v = i + 1; v < 8; v++) {
			if (king == board[v][j])
				return msg;
			if ('.' != board[v][j])
				break;
		} // down
		for (int h = j - 1; h > -1; h--) {
			if (king == board[i][h])
				return msg;
			if ('.' != board[i][h])
				break;
		} // left
		for (int h = j + 1; h < 8; h++) {
			if (king == board[i][h])
				return msg;
			if ('.' != board[i][h])
				break;
		} // right
		return null;
	}

	private String check(char[][] board) {
		String result = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (board[i][j]) {
				case 'p':
					// i+1,j-1;i+1,j+1
					if (i + 1 < 8 && j > 0 && 'K' == board[i + 1][j - 1])
						return white;
					if (i + 1 < 8 && j + 1 < 8 && 'K' == board[i + 1][j + 1])
						return white;
					break;
				case 'P':
					// i-1,j-1;i-1,j+1
					if (i > 0 && j > 0 && 'k' == board[i - 1][j - 1])
						return black;
					if (i > 0 && j + 1 < 8 && 'k' == board[i - 1][j + 1])
						return black;
					break;
				case 'r':
					result = rook(board, i, j, 'K', white);
					if (null != result)
						return result;
					break;
				case 'R':
					result = rook(board, i, j, 'k', black);
					if (null != result)
						return result;
					break;
				case 'b':
					result = bishop(board, i, j, 'K', white);
					if (null != result)
						return result;
					break;
				case 'B':
					result = bishop(board, i, j, 'k', black);
					if (null != result)
						return result;
					break;
				case 'q':
					result = bishop(board, i, j, 'K', white);
					if (null != result)
						return result;
					result = rook(board, i, j, 'K', white);
					if (null != result)
						return result;
					break;
				case 'Q':
					result = bishop(board, i, j, 'k', black);
					if (null != result)
						return result;
					result = rook(board, i, j, 'k', black);
					if (null != result)
						return result;
					break;
				case 'k':
					result = king(board, i, j, 'K', white);
					if (null != result)
						return result;
					break;
				case 'K':
					result = king(board, i, j, 'k', black);
					if (null != result)
						return result;
					break;
				case 'n':
					result = knight(board, i, j, 'K', white);
					if (null != result)
						return result;
					break;
				case 'N':
					result = knight(board, i, j, 'k', black);
					if (null != result)
						return result;
					break;
				}
			}
		}
		return no;
	}

	private String knight(char[][] board, int i, int j, char king, String msg) {
		if (i - 1 > -1 && j - 2 > -1 && king == board[i - 1][j - 2])
			return msg; // upper left 1
		if (i - 2 > -1 && j - 1 > -1 && king == board[i - 2][j - 1])
			return msg; // upper left 2
		if (i - 2 > -1 && j + 1 < 8 && king == board[i - 2][j + 1])
			return msg; // upper right 1
		if (i - 1 > -1 && j + 2 < 8 && king == board[i - 1][j + 2])
			return msg; // upper right 2
		if (i + 1 < 8 && j + 2 < 8 && king == board[i + 1][j + 2])
			return msg; // lower right 1
		if (i + 2 < 8 && j + 1 < 8 && king == board[i + 2][j + 1])
			return msg; // lower right 2
		if (i + 2 < 8 && j - 1 > -1 && king == board[i + 2][j - 1])
			return msg; // lower left 1
		if (i + 1 < 8 && j - 2 > -1 && king == board[i + 1][j - 2])
			return msg; // lower left 2

		return null;
	}

	private String king(char[][] board, int i, int j, char king, String msg) {
		if (i > 0 && j > 0 && king == board[i - 1][j - 1])
			return msg; // upper left
		if (i > 0 && king == board[i - 1][j])
			return msg; // up
		if (i > 0 && j + 1 < 8 && king == board[i - 1][j + 1])
			return msg; // upper right
		if (j > 0 && king == board[i][j - 1])
			return msg; // left
		if (j + 1 < 8 && king == board[i][j + 1])
			return msg; // right
		if (i + 1 < 8 && j > 0 && king == board[i + 1][j - 1])
			return msg; // lower left
		if (i + 1 < 8 && king == board[i + 1][j])
			return msg; // down
		if (i + 1 < 8 && j + 1 < 8 && king == board[i + 1][j + 1])
			return msg; // lower right
		return null;
	}

	private String bishop(char[][] board, int i, int j, char king, String msg) {
		
		for (int v = i - 1, h=j-1; v>-1 && h>-1; v--, h--) {
			if (king == board[v][h])
				return msg;
			if ('.' != board[v][h])
				break;
		} // upper left

		for (int v = i - 1,h=j+1; v > -1&& h<8; v--, h++) {
			if (king == board[v][h])
				return msg;
			if ('.' != board[v][h])
				break;
		} // upper right

		for (int v = i + 1,h=j-1; v < 8 && h>-1; v++,h--) {
			if (king == board[v][h])
				return msg;
			if ('.' != board[v][h])
				break;
		} // lower left

		for (int v = i + 1,h=j+1; v < 8&&h<8; v++,h++) {
			if (king == board[v][h])
				return msg;
			if ('.' != board[v][h])
				break;
		} // lower right

		return null;
	}
}