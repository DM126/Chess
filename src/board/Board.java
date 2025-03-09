package board;

import java.awt.Point;

public class Board
{
	//Begins at (0, 0) with black rook, ends at (7, 7) with white rook.
	//Note: rows are backwards from official chess notation, so space 6E will
	//be represented as [1][4], 1A will be [7][0], etc. (columns are the same)
	private Piece[][] board;
	
	//LKeep track of white and black kings, used for determining if in check
	private Piece blackKing;
	private Piece whiteKing;
	
	//number of rows/columns on the board
	public static final int BOARD_LENGTH = 8;
	
	public Board()
	{
		//Create the black pieces
		board = new Piece[BOARD_LENGTH][BOARD_LENGTH];
		board[0][0] = new Rook(Color.BLACK);
		board[0][1] = new Knight(Color.BLACK);
		board[0][2] = new Bishop(Color.BLACK);
		board[0][3] = new Queen(Color.BLACK);
		blackKing = new King(Color.BLACK);
		board[0][4] = blackKing;
		board[0][5] = new Bishop(Color.BLACK);
		board[0][6] = new Knight(Color.BLACK);
		board[0][7] = new Rook(Color.BLACK);
		for (int i = 0; i < BOARD_LENGTH; i++)
		{
			board[1][i] = new Pawn(Color.BLACK);
		}
		
		//Create the white pieces
		for (int i = 0; i < BOARD_LENGTH; i++)
		{
			board[6][i] = new Pawn(Color.WHITE);
		}
		board[7][0] = new Rook(Color.WHITE);
		board[7][1] = new Knight(Color.WHITE);
		board[7][2] = new Bishop(Color.WHITE);
		board[7][3] = new Queen(Color.WHITE);
		whiteKing = new King(Color.WHITE);
		board[7][4] = whiteKing;
		board[7][5] = new Bishop(Color.WHITE);
		board[7][6] = new Knight(Color.WHITE);
		board[7][7] = new Rook(Color.WHITE);
		
		//Set the locations of the pieces
		for (int r = 0; r < BOARD_LENGTH; r++)
		{
			for (int c = 0; c < BOARD_LENGTH; c++)
			{
				if (board[r][c] != null)
				{
					board[r][c].setLocation(r, c);
				}
			}
		}
	}
	
	/**
	 * Determines if a space on the board is empty
	 * 
	 * @param row the row of the space
	 * @param column the column of the space
	 * @return true if there is no piece at the space
	 */
	public boolean isEmptySpace(int row, int column)
	{
		return board[row][column] == null;
	}
	
	/**
	 * Returns a piece at a given space. will return null if empty.
	 * 
	 * @param row the row of the space
	 * @param column the column of the space
	 * @return the piece at the space
	 */
	public Piece getPiece(int row, int column)
	{
		return board[row][column];
	}
	
	/**
	 * Sets the piece at a specific space
	 * 
	 * @param piece the piece to place, null if empty
	 * @param row the row of the space
	 * @param column the column of the space
	 */
	public void setPiece(Piece piece, int row, int column)
	{
		board[row][column] = piece;
		if (piece != null)
		{
			piece.setLocation(row, column);
		}
	}
	
	/**
	 * Moves a piece from one space to another, empties the starting space.
	 * Does not check if spaces are empty or filled, only changes values!
	 * 
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row
	 * @param endCol the ending column
	 */
	public void movePiece(int startRow, int startCol, int endRow, int endCol)
	{
		setPiece(board[startRow][startCol], endRow, endCol);
		board[startRow][startCol] = null;
	}
	
	/**
	 * @return the location of the white king
	 */
	public Point getWhiteKingSpace()
	{
		return whiteKing.getLocation();
	}
	
	/**
	 * @return the location of the black king
	 */
	public Point getBlackKingSpace()
	{
		return blackKing.getLocation();
	}
}
