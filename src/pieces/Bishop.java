package pieces;
import javax.swing.ImageIcon;

public class Bishop extends Piece
{
	public Bishop(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackbishop.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whitebishop.png");
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
		return super.toString() + "B";
	}
}
