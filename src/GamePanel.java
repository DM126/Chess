import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import board.*;
import board.Color; //resolve naming collision with awt.Color

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

	//dimensions of a space in pixels, used for drawing the pieces
	private final int SPACE_SIZE;
	
	public GamePanel()
	{
		board = new Board();
		
		isWhitesTurn = true;
		
		boardImage = new ImageIcon("resources/chessboard.png");
		SPACE_SIZE = boardImage.getIconWidth() / Board.BOARD_LENGTH;
		
		ClickListener listener = new ClickListener();
		addMouseListener(listener);
		
		setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		boardImage.paintIcon(this, g, 0, 0);
		
		for (int r = 0; r < Board.BOARD_LENGTH; r++)
		{
			for (int c = 0; c < Board.BOARD_LENGTH; c++)
			{
				if (board.getPiece(r, c) != null)
				{
					//board is drawn so that white is on the bottom
					board.getPiece(r, c).getImage().paintIcon(this, g, c * SPACE_SIZE, r * SPACE_SIZE);
				}
			}
		}
	}
	
	/**
	 * Flips the row on screen. Might need this later if I decide to go with
	 * official chess notation for board order.
	 * 
	 * @param row the row on screen
	 * @return the row in the board array
	 */
	private static int flipRow(int row)
	{
		return Math.abs(row - Board.BOARD_LENGTH) - 1;
	}
	
	/**
	 * Contains logic for when a space on the board is clicked.
	 * If no piece is currently selected, selects a piece if one is clicked.
	 * If a piece is currently selected, moves it or switches selections.
	 * 
	 * @param row the row clicked
	 * @param col the column clicked
	 */
	private void spaceClicked(int row, int col)
	{
		if (selectedSpace == null)
		{
			Piece clicked = board.getPiece(row, col);
			
			if (clicked != null)
			{
				if (isWhitesTurn)
				{
					if (clicked.getColor() == Color.WHITE)
					{
						System.out.println("white selected " + clicked + " at " + row + "," + col);
						selectedSpace = new Point(col, row);
					}
				}
				else
				{
					if (clicked.getColor() == Color.BLACK)
					{
						System.out.println("black selected " + clicked + " at " + row + "," + col);
						selectedSpace = new Point(col, row);
					}
				}
			}
		}
		else //piece is already selected
		{
			//if a piece of the same color is selected, switch selection
			if (board.getPiece(row, col) != null && 
				board.getPiece(selectedSpace.y, selectedSpace.x).getColor() == board.getPiece(row, col).getColor())
			{
				System.out.println("switched selection to " + board.getPiece(row, col) + " at " + row + "," + col);
				selectedSpace.setLocation(col, row);
			}
			else //new selection is empty space or different color
			{
				//test if the piece can move
				if (board.getPiece(selectedSpace.y, selectedSpace.x).canMove(board, selectedSpace.y, selectedSpace.x, row, col))
				{
					//move the piece and switch turns
					board.movePiece(selectedSpace.y, selectedSpace.x, row, col);
					System.out.println("moved " + board.getPiece(row, col) + " to " + row + "," + col);
					
					//check for pawn promotion
					if (board.getPiece(row, col) instanceof Pawn)
					{
						promotePawn(row, col);
					}
					
					isWhitesTurn = !isWhitesTurn;
					
					repaint();
				}
				else //TODO debug remove
				{
					System.out.println("could not move " + board.getPiece(selectedSpace.y, selectedSpace.x) + " to " + row + "," + col);
				}
				
				selectedSpace = null;
			}
		}
	}
	
	/**
	 * checks if a pawn should be promoted, and promotes it if so
	 * 
	 * @param row the row of the pawn
	 * @param column the column of the pawn
	 */
	private void promotePawn(int row, int column)
	{
		Piece pawn = board.getPiece(row, column);
		if ((pawn.getColor() == Color.BLACK && row == Board.BOARD_LENGTH - 1) ||
			(pawn.getColor() == Color.WHITE && row == 0))
		{
			String promoteTo = promotionPrompt();
			Piece promoted;
			if (promoteTo == "Bishop")
			{
				promoted = new Bishop(pawn.getColor());
			}
			else if (promoteTo == "Rook")
			{
				promoted = new Rook(pawn.getColor());
			}
			else
			{
				promoted = new Queen(pawn.getColor());
			}
			board.setPiece(promoted, row, column);
		}
	}
	
	/**
	 * Opens a prompt for the user to select what to promote a pawn to
	 * 
	 * @return a string with the piece type to promote to (Bishop, Rook, or Queen)
	 */
	private String promotionPrompt()
	{
		String[] options = {"Bishop", "Rook", "Queen"};
		int choice;
		do
		{
			//TODO set icon to pawn image, depending on color
			choice = JOptionPane.showOptionDialog(this, "Choose a piece to promote the pawn to", "Promote pawn", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Queen");
		}
		while (choice == JOptionPane.CLOSED_OPTION);
		return options[choice];
	}
	
	private class ClickListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e)
		{
			//integer division done to get the index in the board array
			int r = e.getY() / SPACE_SIZE;
			int c = e.getX() / SPACE_SIZE;
			
			spaceClicked(r, c);
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
