package pieces;
import javax.swing.ImageIcon;

public class Pawn extends Piece
{	
	public Pawn(Color color)
	{
		super(color);
		if (color == Color.BLACK)
		{
			image = new ImageIcon("resources/pieces/blackpawn.png");
		}
		else
		{
			image = new ImageIcon("resources/pieces/whitepawn.png");
		}
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "P";
	}
}
