package board;

import java.awt.Point;

public class Pawn extends Piece
{	
	public Pawn(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		if (!super.canMove(board, startRow, startCol, endRow, endCol))
		{
			return false;
		}
		
		//Black moves down the board (positive direction), white moves up the board (negative direction)
		int rowDiff = (color == Color.BLACK) ? 1 : -1;
		
		if (board.isEmptySpace(endRow, endCol))
		{
			//Movement must be vertical if space is empty
			//pawns can move 1 space forward, or two if on starting row
			//Can move diagonally to empty space if capturing en passant
			if (isLegalVerticalMove(board, startRow, startCol, endRow, endCol, rowDiff) ||
				isEnPassantMove(board, startRow, startCol, endRow, endCol, rowDiff) )
			{
				return true;
			}
		}
		else if (isDiagonalMoveOneSpaceForward(startRow, startCol, endRow, endCol, rowDiff))
		{
			//space not empty, must be 1 space forward diagonally
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the move is a legal vertical move (one space forward, or two if on starting row).
	 * 
	 * @param board the chessboard
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row (should be two spaces forward)
	 * @param endCol the ending column (should be the same)
	 * @param rowDiff the direction (1 for black, -1 for white)
	 * @return
	 */
	private boolean isLegalVerticalMove(Board board, int startRow, int startCol, int endRow, int endCol, int rowDiff)
	{
		return startCol == endCol
			&& (endRow - startRow == 1 * rowDiff || canMoveTwoSpacesForward(board, startRow, startCol, endRow, endCol));
	}
	
	/**
	 * Pawns can move 2 spaces if at the starting row and no piece is in between.
	 * 
	 * @param board the chessboard
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row (should be two spaces forward)
	 * @param endCol the ending column (should be the same)
	 * @return true if the pawn can move two spaces forward
	 */
	private boolean canMoveTwoSpacesForward(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		return (color == Color.BLACK && 
				startRow == 1 && 
				endRow == 3 && 
				board.isEmptySpace(endRow - 1, endCol))
			|| (color == Color.WHITE && 
				startRow == Board.BOARD_LENGTH - 2 && 
				endRow == startRow - 2 && 
				board.isEmptySpace(startRow - 1,  startCol));
	}
	
	/**
	 * Checks if the move is one space forward diagonally.
	 * 
	 * @param startrow the starting row
	 * @param startCol the starting column
	 * @param endRow the ending row
	 * @param endCol the ending column
	 * @param rowDiff the direction (1 for black, -1 for white)
	 * @return true if the move is one space forward diagonally
	 */
	private boolean isDiagonalMoveOneSpaceForward(int startRow, int startCol, int endRow, int endCol, int rowDiff)
	{
		return Math.abs(startCol - endCol) == 1 && endRow - startRow == 1 * rowDiff;
	}
	
	private boolean isEnPassantMove(Board board, int startRow, int startCol, int endRow, int endCol, int rowDiff)
	{
		return isDiagonalMoveOneSpaceForward(startRow, startCol, endRow, endCol, rowDiff) && 
				board.getEnPassantSpace() != null && 
				board.getEnPassantSpace().equals(new Point(endCol, endRow));
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "P";
	}
}
