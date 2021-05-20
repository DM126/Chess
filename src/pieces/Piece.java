package pieces;
import javax.swing.ImageIcon;

/**
 * Represents a chess piece
 */
public abstract class Piece
{
	protected Color color; //either black or white
	protected ImageIcon image;
	protected int row, column; //location of the piece
	
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
	 * @param startRow the row the piece is currently in
	 * @param startCol the column the piece is currently in
	 * @param endRow the row to move to
	 * @param endCol the column to move to
	 * @return true if the move can be legally made
	 */
	public abstract boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol);
	
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
