package pieces;
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
	public boolean canMove(Piece[][] board, int row, int col)
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "Q";
	}
}
