package board;

import java.awt.Point;

/**
 * Represents a chess move.
 */
public class Move
{
	private Point start;
	private Point end;
	private Piece captured;
	private boolean wasEnPassant;
	
	public Move(int startRow, int startCol, int endRow, int endCol, Piece captured)
	{
		this.start = new Point(startCol, startRow);
		this.end = new Point(endCol, endRow);
		this.captured = captured;
		this.wasEnPassant = false;
	}
	
	/**
	 * @return the starting space of the move
	 */
	public Point getStart()
	{
		return start;
	}
	
	/**
	 * @return the ending space of the move
	 */
	public Point getEnd()
	{
		return end;
	}
	
	/**
	 * @return the piece that was captured by this move
	 */
	public Piece getCaptured()
	{
		return captured;
	}
	
	/**
	 * Sets the captured piece.
	 * Only necessary if captured piece is not at end space (en passant).
	 */
	public void setCaptured(Piece captured)
	{
		this.captured = captured;
	}
	
	/**
	 * @return true if the move was an En Passant move
	 */
	public boolean wasEnPassant()
	{
		return wasEnPassant;
	}
	
	/**
	 * Sets if the move was en passant. 
	 * Only necessary if passing true as moves are assumed not en passant by default.
	 */
	public void setWasEnPassant(boolean wasEnPassant)
	{
		this.wasEnPassant = wasEnPassant;
	}
	
	@Override
	public String toString()
	{
		return "Move: (" + start.y + "," + start.x + ") to (" + end.y + "," + end.x + "). captured: " + captured + ". En passant: " + wasEnPassant;
	}
}
