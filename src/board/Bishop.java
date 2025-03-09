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
		return super.canMove(board, startRow, startCol, endRow, endCol)
			&& isClearDiagonalPath(board, startRow, startCol, endRow, endCol);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "B";
	}
}
