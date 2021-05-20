package pieces;
import javax.swing.ImageIcon;

public class Rook extends Piece
{
	public Rook(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackrook.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whiterook.png");
		}
	}
	
	@Override
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move either horizontally or vertically
		
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "R";
	}
}
