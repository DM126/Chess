package board;
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
	public boolean canMove(Board board, int startRow, int startCol, int endRow, int endCol)
	{
		//can move diagonally
		if (Math.abs(startRow - endRow) == Math.abs(startCol - endCol))
		{
			//check for pieces between start and end.
			//only one of these loops will actually be entered, depending
			//on which direction the bishop is moving.
			for (int i = 1; startRow + i < endRow && startCol + i < endCol; i++)
			{
				//down-right
				if (!board.isEmptySpace(startRow + i,  startCol + i))
				{
					return false;
				}
			}
			for (int i = 1; startRow + i < endRow && startCol - i > endCol; i++)
			{
				//down-left
				if (!board.isEmptySpace(startRow + i,  startCol - i))
				{
					return false;
				}
			}
			for (int i = 1; startRow - i > endRow && startCol - i > endCol; i++)
			{
				//up-left
				if (!board.isEmptySpace(startRow - i,  startCol - i))
				{
					return false;
				}
			}
			for (int i = 1; startRow - i > endRow && startCol + i < endCol; i++)
			{
				//up-right
				if (!board.isEmptySpace(startRow - i,  startCol + i))
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "B";
	}
}
