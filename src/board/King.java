package board;
import javax.swing.ImageIcon;

public class King extends Piece
{
	public King(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackking.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whiteking.png");
		}
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "K";
	}
}
