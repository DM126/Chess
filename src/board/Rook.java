package board;

public class Rook extends Piece
{
	public Rook(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move either horizontally or vertically
		return super.canMove(board, startRow, startCol, endRow, endCol)
			&& isClearHorizontalOrVerticalPath(board, startRow, startCol, endRow, endCol);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "R";
	}
}
