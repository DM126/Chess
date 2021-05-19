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
	private Piece[][] board;
	
	private boolean isWhitesTurn; //true if it is currently white's turn
	
	//Space of the currently selected piece. null if no piece is selected.
	//Values range from 0-8. x=column, y=row
	private Point selectedSpace;
	
	private static final int BOARD_SIZE = 8; //number of rows/columns on the board
	private final int SPACE_SIZE; //dimensions of a space in pixels
	
	public GamePanel()
	{
		//set up the board
		board = new Piece[BOARD_SIZE][BOARD_SIZE];
		board[0][0] = new Rook(Color.BLACK);
		board[0][1] = new Knight(Color.BLACK);
		board[0][2] = new Bishop(Color.BLACK);
		board[0][3] = new Queen(Color.BLACK);
		board[0][4] = new King(Color.BLACK);
		board[0][5] = new Bishop(Color.BLACK);
		board[0][6] = new Knight(Color.BLACK);
		board[0][7] = new Rook(Color.BLACK);
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			board[1][i] = new Pawn(Color.BLACK);
		}
		
		for (int i = 0; i < BOARD_SIZE; i++)
		{
			board[6][i] = new Pawn(Color.WHITE);
		}
		board[7][0] = new Rook(Color.WHITE);
		board[7][1] = new Knight(Color.WHITE);
		board[7][2] = new Bishop(Color.WHITE);
		board[7][3] = new Queen(Color.WHITE);
		board[7][4] = new King(Color.WHITE);
		board[7][5] = new Bishop(Color.WHITE);
		board[7][6] = new Knight(Color.WHITE);
		board[7][7] = new Rook(Color.WHITE);
		
		isWhitesTurn = true;
		
		boardImage = new ImageIcon("resources/chessboard.png");
		SPACE_SIZE = boardImage.getIconWidth() / BOARD_SIZE;
		
		ClickListener listener = new ClickListener();
		addMouseListener(listener);
		
		setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		boardImage.paintIcon(this, g, 0, 0);
		
		for (int r = 0; r < BOARD_SIZE; r++)
		{
			for (int c = 0; c < BOARD_SIZE; c++)
			{
				if (board[r][c] != null)
				{
					board[r][c].getImage().paintIcon(this, g, c * SPACE_SIZE, r * SPACE_SIZE);
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
				Piece clicked = board[r][c];
				
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
				if (board[r][c] != null && board[selectedSpace.y][selectedSpace.x].getColor() == board[r][c].getColor())
				{
					//switch selection if a piece of the same color is selected
					System.out.println("switched selection to " + board[r][c] + " at " + r + "," + c);
					selectedSpace = new Point(c, r);
				}
				else if (board[selectedSpace.y][selectedSpace.x].canMove(board, r, c))
				{
					//move the piece and switch turns
					board[r][c] = board[selectedSpace.y][selectedSpace.x];
					board[selectedSpace.y][selectedSpace.x] = null;
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
