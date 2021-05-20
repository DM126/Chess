import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pieces.*;
import pieces.Color; //resolve naming collision with awt.Color

/**
 * Represents the main game panel
 */
public class GamePanel extends JPanel
{
	private ImageIcon boardImage;
	private Board board;
	
	private boolean isWhitesTurn; //true if it is currently white's turn
	
	//Space of the currently selected piece. null if no piece is selected.
	//Values range from 0-8. x=column, y=row
	private Point selectedSpace;

	private final int SPACE_SIZE; //dimensions of a space in pixels
	
	public GamePanel()
	{
		board = new Board();
		
		isWhitesTurn = true;
		
		boardImage = new ImageIcon("resources/chessboard.png");
		SPACE_SIZE = boardImage.getIconWidth() / Board.BOARD_SIZE;
		
		ClickListener listener = new ClickListener();
		addMouseListener(listener);
		
		setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		boardImage.paintIcon(this, g, 0, 0);
		
		for (int r = 0; r < Board.BOARD_SIZE; r++)
		{
			for (int c = 0; c < Board.BOARD_SIZE; c++)
			{
				if (board.getPiece(r, c) != null)
				{
					board.getPiece(r, c).getImage().paintIcon(this, g, c * SPACE_SIZE, r * SPACE_SIZE);
				}
			}
		}
	}
	
	private class ClickListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			//integer division done to get the index in the board array
			int r = e.getY() / SPACE_SIZE;
			int c = e.getX() / SPACE_SIZE;
			
			if (selectedSpace == null)
			{
				Piece clicked = board.getPiece(r, c);
				
				if (clicked != null)
				{
					if (isWhitesTurn)
					{
						if (clicked.getColor() == Color.WHITE)
						{
							System.out.println("white selected " + clicked + " at " + r + "," + c);
							selectedSpace = new Point(c, r);
						}
					}
					else
					{
						if (clicked.getColor() == Color.BLACK)
						{
							System.out.println("black selected " + clicked + " at " + r + "," + c);
							selectedSpace = new Point(c, r);
						}
					}
				}
			}
			else //piece is already selected
			{
				if (board.getPiece(r, c) != null && 
					board.getPiece(selectedSpace.y, selectedSpace.x).getColor() == board.getPiece(r, c).getColor())
				{
					//switch selection if a piece of the same color is selected
					System.out.println("switched selection to " + board.getPiece(r, c) + " at " + r + "," + c);
					selectedSpace = new Point(c, r);
				}
				else if (board.getPiece(selectedSpace.y, selectedSpace.x).canMove(board, selectedSpace.y, selectedSpace.x, r, c))
				{
					//move the piece, unselect the space, and switch turns
					board.movePiece(selectedSpace.y, selectedSpace.x, r, c);
					selectedSpace = null;
					
					isWhitesTurn = !isWhitesTurn;
					
					repaint();
				}
			}
		}

		//Unimplemented methods:
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
