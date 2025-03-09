package board;
import javax.swing.ImageIcon;

/**
 * Represents a chess piece
 */
public abstract class Piece
{
	protected Color color; //either black or white
	protected ImageIcon image;
	
	//location of the piece on the board
	protected int row;
	protected int column;
	
	//Directory where piece images are stored
	protected static final String IMAGE_PATH = "resources/pieces/";
	
	//File extension of piece images
	private static final String FILE_EXTENSION = ".png";
	
	protected Piece(Color color)
	{
		this.color = color;
		this.image = new ImageIcon(getImageFilePath());
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public ImageIcon getImage()
	{
		return image;
	}
	
	public void setImage(ImageIcon image)
	{
		this.image = image;
	}
	
	/**
	 * Gets the full file path for the piece's image.
	 * Example: resources/pieces/blackbishop.png
	 * 
	 * @return the file path to the piece's image
	 */
	private String getImageFilePath()
	{
		String colorStr = color.toString().toLowerCase();
		String pieceName = this.getClass().getSimpleName().toLowerCase();
		return IMAGE_PATH  + colorStr + pieceName + FILE_EXTENSION;
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
