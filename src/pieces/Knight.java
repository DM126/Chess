package pieces;
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
	public String toString()
	{
		return super.toString() + "N";
	}
}