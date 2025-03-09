package board;

public class Bishop extends Piece
{
	public Bishop(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//cannot move to current position
		if (startRow == endRow && startCol == endCol)
		{
			return false;
		}
		
		return isClearDiagonalPath(board, startRow, startCol, endRow, endCol);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "B";
	}
}
