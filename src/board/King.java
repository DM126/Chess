package board;
import javax.swing.ImageIcon;

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
		return (Math.abs(startRow - endRow) <= 1 &&
				Math.abs(startCol - endCol) <= 1 &&
				!(startRow == endRow && startCol == endCol));
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "K";
	}
}
