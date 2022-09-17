package model;

import java.awt.Color;
import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {
	private Random random;
	private int score = 0;

	public ClearCellGame(int maxRows, int maxCols, java.util.Random random,
			int strategy) {
		super(maxRows, maxCols);
		this.random = random;
	}

	// check if a row is an empty row or not
	private boolean emptyRow(int rowIndex) {
		boolean empty = true;
		
		for (int col = 0; col < board[0].length; col++) {
			if (board[rowIndex][col] != BoardCell.EMPTY) {
				empty = false;
			}
		}
		return empty;
	}

	public boolean isGameOver() {
		boolean lastRowEmpty = emptyRow(board.length - 1);
		
		if (lastRowEmpty) {
			return false;
		} else {
			return true;
		}
	}

	public int getScore() {
		return score;
	}

	public void nextAnimationStep() {
		if (!isGameOver()) {
			for (int row = board.length - 1; row > 0; row--) {
				for (int col = 0; col < board[0].length; col++) {
					board[row][col] = board[row - 1][col];
				}
			}
			for (int col = 0; col < board[0].length; col++) {
				board[0][col] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
		}
	}

	// clear adjacent cells continuously as long as cells have the same color as
	// the selected cell
	// the direction is presented by the rowIncrement and colIncrement
	// e.g., rowIncrement=1 and colIncrement=-1 is checking down-left cells
	private void checkAdjacent(int row, int col, int rowIncrement,
			int colIncrement, BoardCell selected) {
		int rowIndex = row + rowIncrement;
		int colIndex = col + colIncrement;

		while (rowIndex >= 0 && rowIndex < board.length && colIndex >= 0
				&& colIndex < board[0].length
				&& board[rowIndex][colIndex] == selected) {
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			rowIndex += rowIncrement;
			colIndex += colIncrement;
		}
	}

	// return a new board after moving non-empty rows upward
	private BoardCell[][] collapse() {
		BoardCell[][] after = new BoardCell[board.length][board[0].length];
		for (int row = 0; row < after.length; row++) {
			for (int col = 0; col < after[0].length; col++) {
				after[row][col] = BoardCell.EMPTY;
			}
		}
		
		int afterRow = 0;
		for (int row = 0; row < board.length; row++) {
			if (!emptyRow(row)) {
				for (int col = 0; col < after[0].length; col++) {
					after[afterRow][col] = board[row][col];
				}
				afterRow++;
			}
		}
		return after;
	}

	public void processCell(int rowIndex, int colIndex) {

		BoardCell selected = board[rowIndex][colIndex];

		checkAdjacent(rowIndex, colIndex, -1, 0, selected);
		checkAdjacent(rowIndex, colIndex, 1, 0, selected);
		checkAdjacent(rowIndex, colIndex, 0, -1, selected);
		checkAdjacent(rowIndex, colIndex, 0, 1, selected);
		checkAdjacent(rowIndex, colIndex, -1, -1, selected);
		checkAdjacent(rowIndex, colIndex, -1, 1, selected);
		checkAdjacent(rowIndex, colIndex, 1, -1, selected);
		checkAdjacent(rowIndex, colIndex, 1, 1, selected);

		board[rowIndex][colIndex] = BoardCell.EMPTY;
		score++;

		board = collapse();

	}
}