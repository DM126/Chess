package board;

public class King extends Piece
{
	public King(Color color)
	{
		super(color);
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{	
		//can move 1 space in any direction
		return super.canMove(board, startRow, startCol, endRow, endCol) &&
				Math.abs(startRow - endRow) <= 1 &&
				Math.abs(startCol - endCol) <= 1;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "K";
	}
}
