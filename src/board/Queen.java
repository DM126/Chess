package board;

public class Queen extends Piece
{
	public Queen(Color color)
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
		
		return isClearDiagonalPath(board, startRow, startCol, endRow, endCol) 
			|| isClearHorizontalOrVerticalPath(board, startRow, startCol, endRow, endCol);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "Q";
	}
}
