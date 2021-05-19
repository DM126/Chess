package pieces;
import javax.swing.ImageIcon;

/**
 * Represents a chess piece
 */
public abstract class Piece
{
	protected Color color;
	protected ImageIcon image;
	
	public Piece(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void SetColor(Color color)
	{
		this.color = color;
	}
	
	public ImageIcon getImage()
	{
		return image;
	}
	
	public void SetImage(ImageIcon image)
	{
		this.image = image;
	}
	
	@Override
	public String toString()
	{
		String piece = "";
		
		if (color == Color.BLACK)
		{
			piece += "B";
		}
		else
		{
			piece += "W";
		}
		
		return piece;
	}
}
