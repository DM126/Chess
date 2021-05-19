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
	
	/**
	 * Determines if a piece can legally move to a space
	 * 
	 * @param board the chessboard
	 * @param row the row to move to
	 * @param col the column to move to
	 * @return true if the move can be legally made
	 */
	public abstract boolean canMove(Piece[][] board, int row, int col);
	
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
