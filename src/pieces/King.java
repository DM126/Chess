package pieces;
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
	public boolean canMove(Piece[][] board, int row, int col)
	{
		return true;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "K";
	}
}
