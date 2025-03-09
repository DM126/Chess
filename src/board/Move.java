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
	
	public Move(int startRow, int startCol, int endRow, int endCol, Piece captured)
	{
		this.start = new Point(startCol, startRow);
		this.end = new Point(endCol, endRow);
		this.captured = captured;
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
}
