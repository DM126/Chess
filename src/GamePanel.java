import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

import pieces.*;

public class GamePanel extends JPanel
{
	private ImageIcon boardImage;
	private Piece[][] board;
	
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
		
		boardImage = new ImageIcon("resources/chessboard.png");
		SPACE_SIZE = boardImage.getIconWidth() / BOARD_SIZE;
		
		setPreferredSize(new Dimension(boardImage.getIconWidth(), boardImage.getIconHeight()));
	}
	
	public void paintComponent(Graphics g)
	{
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
}
