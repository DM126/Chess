package board;

public class Knight extends Piece
{
	public Knight(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move in an L shape
		return super.canMove(board, startRow, startCol, endRow, endCol) &&
				((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1) ||
				(Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2));
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "N";
	}
}
