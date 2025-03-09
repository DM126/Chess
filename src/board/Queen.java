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
		return isClearDiagonalPath(board, startRow, startCol, endRow, endCol) 
			|| isClearHorizontalOrVerticalPath(board, startRow, startCol, endRow, endCol);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "Q";
	}
}
