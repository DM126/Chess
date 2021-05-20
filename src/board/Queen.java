package board;
import javax.swing.ImageIcon;

public class Queen extends Piece
{
	public Queen(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackqueen.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whitequeen.png");
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
		return super.toString() + "Q";
	}
}
