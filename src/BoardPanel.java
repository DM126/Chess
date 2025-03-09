import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import board.Board;

public class BoardPanel extends JPanel
{
	Board board;
	GamePanel gamePanel;
	
	private ImageIcon boardImage;
	
	//dimensions of a space in pixels, used for drawing the pieces
	private final int SPACE_SIZE;
	
	public BoardPanel(GamePanel gamePanel, Board board)
	{
		this.board = board;
		this.gamePanel = gamePanel;
		
		boardImage = new ImageIcon("resources/chessboard.png");
		SPACE_SIZE = boardImage.getIconWidth() / Board.BOARD_LENGTH;
		
		ClickListener listener = new ClickListener();
		addMouseListener(listener);
		
		setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
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
	
	@Override
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
	
	private class ClickListener implements MouseListener
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			//integer division done to get the index in the board array
			int r = e.getY() / SPACE_SIZE;
			int c = e.getX() / SPACE_SIZE;
			
			gamePanel.spaceClicked(r, c);
		}

		//Unimplemented methods:
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	}
}
