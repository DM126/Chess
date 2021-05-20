package board;

public class Board
{
	//Begins at (0, 0) with black rook, ends at (7, 7) with white rook.
	//Note: rows are backwards from official chess notation, so space 6E will
	//be represented as [1][4], 1A will be [7][0], etc. (columns are the same)
	private Piece[][] board;
	
	//number of rows/columns on the board
	public static final int BOARD_LENGTH = 8;
	
	public Board()
	{
		board = new Piece[BOARD_LENGTH][BOARD_LENGTH];
		board[0][0] = new Rook(Color.BLACK);
		board[0][1] = new Knight(Color.BLACK);
		board[0][2] = new Bishop(Color.BLACK);
		board[0][3] = new Queen(Color.BLACK);
		board[0][4] = new King(Color.BLACK);
		board[0][5] = new Bishop(Color.BLACK);
		board[0][6] = new Knight(Color.BLACK);
		board[0][7] = new Rook(Color.BLACK);
		for (int i = 0; i < BOARD_LENGTH; i++)
		{
			board[1][i] = new Pawn(Color.BLACK);
		}
		
		for (int i = 0; i < BOARD_LENGTH; i++)
		{
			board[6][i] = new Pawn(Color.WHITE);
		}
		board[7][0] = new Rook(Color.WHITE);
		board[7][1] = new Knight(Color.WHITE);
		board[7][2] = new Bishop(Color.WHITE);
		board[7][3] = new Queen(Color.WHITE);
		board[7][4] = new King(Color.WHITE);
		board[7][5] = new Bishop(Color.WHITE);
		board[7][6] = new Knight(Color.WHITE);
		board[7][7] = new Rook(Color.WHITE);
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
		board[endRow][endCol] = board[startRow][startCol];
		board[startRow][startCol] = null;
	}
}
