package board;
import javax.swing.ImageIcon;

public class Knight extends Piece
{
	public Knight(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackknight.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whiteknight.png");
		}
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move in an L shape
		return ((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1) ||
				(Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2));
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "N";
	}
}
