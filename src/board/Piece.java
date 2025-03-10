package board;

import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * Represents a chess piece
 */
public abstract class Piece
{
	protected Color color; //either black or white
	protected ImageIcon image;
	
	//location of the piece on the board.
	//x = col, y = row: (col, row)
	protected Point location;
	
	//Directory where piece images are stored
	protected static final String IMAGE_PATH = "resources/pieces/";
	
	//File extension of piece images
	private static final String FILE_EXTENSION = ".png";
	
	/**
	 * Creates a chess piece with a color and no location.
	 * Location should be set after creation.
	 * 
	 * @param color the color of the piece
	 */
	protected Piece(Color color)
	{
		this.color = color;
		this.image = new ImageIcon(getImageFilePath());
		location = new Point(0, 0);
	}
	
	/**
	 * Creates a chess piece with a color and a location.
	 * 
	 * @param color the color of the piece
	 * @param startRow the starting row of the piece
	 * @param startCol the starting column of the piece
	 */
	protected Piece(Color color, int startRow, int startCol)
	{
		this(color);
		this.setLocation(startCol, startRow);
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
	
	public void setLocation(int row, int col)
	{
		location.setLocation(col, row);
		System.out.println("location changed to " + location);
	}
	
	public Point getLocation()
	{
		return location;
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
	 * Determines if there is a clear diagonal path from start to end.
	 * Used by Bishop and Queen.
	 * 
	 * @param board the chessboard, used to check if a piece is in the way
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the destination row
	 * @param endCol the destination column
	 * @return true if the path from start to end is a diagonal with no pieces in between
	 */
	protected boolean isClearDiagonalPath(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//Distance traveled in both directions will be the same for diagonal movements
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
		{
			//Determine if you should add or subtract i from starting row/column to reach destination
			int rowDiff = startRow < endRow ? 1 : -1;
			int colDiff = startCol < endCol ? 1 : -1;
			
			//Travel the diagonal path until the destination is reached, checking if anything is in the way
			for (int i = 1; startRow + (i * rowDiff) != endRow && startCol + (i * colDiff) != endCol; i++)
			{
				if (!board.isEmptySpace(startRow + (i * rowDiff),  startCol + (i * colDiff)))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines if there is a clear horizontal or vertical path from start to end.
	 * Used by Rook and Queen.
	 * 
	 * @param board the chessboard, used to check if a piece is in the way
	 * @param startRow the starting row
	 * @param startCol the starting column
	 * @param endRow the destination row
	 * @param endCol the destination column
	 * @return true if the path from start to end is a horizontal or vertical path with no pieces in between
	 */
	protected boolean isClearHorizontalOrVerticalPath(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//Only one direction can have movement
		if ((startRow == endRow) != (startCol == endCol))
		{
			//check for pieces between start and end.
			//only one of these for loops will actually be entered, depending
			//on whether the rook is going horizontal or vertical.
			for (int r = Math.min(startRow, endRow) + 1; r < Math.max(startRow, endRow); r++)
			{
				if (!board.isEmptySpace(r, startCol))
				{
					return false;
				}
			}
			for (int c = Math.min(startCol, endCol) + 1; c < Math.max(startCol, endCol); c++)
			{
				if (!board.isEmptySpace(startRow, c))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
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
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//no piece can move to a space occupied by a piece of the same color,
		//and destination cannot be same as start
		Piece destinationPiece = board.getPiece(endRow, endCol);
		return (destinationPiece == null || destinationPiece.getColor() != this.getColor()) 
				&& !(startRow == endRow && startCol == endCol);
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
