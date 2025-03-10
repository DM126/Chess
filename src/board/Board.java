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
	
	//Keeps track of a space where an en passant capture can be made.
	//Will be between where the pawn making the capture will end up.
	//Will be set after a pawn moves two spaces, and will be set to null after any other move.
	private Point enPassantSpace;
	
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
		
		enPassantSpace = null;
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
	 * Simulates a single move without changing any other information
	 * being tracked, like number of moves or en passant spaces. Empties
	 * the starting space.
	 * 
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row
	 * @param endCol the ending column
	 * @return the move taken
	 */
	public Move simulateMove(int startRow, int startCol, int endRow, int endCol)
	{
		Move move = new Move(startRow, startCol, endRow, endCol, board[endRow][endCol]);
		
		//TODO refactor the logic for this to be part of the Move class and remove boolean variable.
		//Check if move is en passant
		if (enPassantSpace != null &&
			board[startRow][startCol] instanceof Pawn &&
			enPassantSpace.equals(new Point(endCol, endRow)))
		{
//			System.out.println("simulating en passant");
			move.setWasEnPassant(true);
			move.setCaptured(board[startRow][endCol]);
			board[startRow][endCol] = null;
		}
		
		setPiece(board[startRow][startCol], endRow, endCol);
		board[startRow][startCol] = null;
		
		return move;
	}
	
	/**
	 * Moves a piece from one space to another, empties the starting space.
	 * Does not check if spaces are empty or filled, only changes values!
	 * This move is not a simulated move and will change the board state.
	 * 
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row
	 * @param endCol the ending column
	 */
	public Move movePiece(int startRow, int startCol, int endRow, int endCol)
	{
		Move move = simulateMove(startRow, startCol, endRow, endCol);
		
		//Check if en passant is possible after the move
		setEnpassant(startRow, endRow, endCol);
		
		return move;
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
	
	/**
	 * Gets the location of a possible en passant space. Will be null if no 
	 * en passant is possible.
	 * 
	 * @return the point on the board where an en passant capture can be made
	 */
	public Point getEnPassantSpace()
	{
		return enPassantSpace;
	}
	
	/**
	 * Checks if en passant is possible after a move.
	 * 
	 * @param startRow the starting row of the move
	 * @param startCol the starting column of the move
	 * @param endRow the ending row of the move
	 * @param endCol the ending column of the move
	 */
	private void setEnpassant(int startRow, int endRow, int endCol)
	{
		if (Math.abs(startRow - endRow) == 2 && board[endRow][endCol] instanceof Pawn)
		{
			enPassantSpace = new Point(endCol, startRow == 1 ? endRow - 1 : endRow + 1);
			System.out.println("En passant possible at " + enPassantSpace);
		}
		else
		{
			enPassantSpace = null;
		}
	}
	
	/**
	 * Reverts the board to the state before a move was done.
	 */
	public void undoMove(Move move)
	{
		simulateMove(move.getEnd().y, move.getEnd().x, move.getStart().y, move.getStart().x);
		Piece captured = move.getCaptured();
		if (captured != null)
		{
			setPiece(captured, captured.getLocation().y, captured.getLocation().x);
		}
	}
}
