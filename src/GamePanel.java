import java.awt.*;
import javax.swing.*;

import board.*;
import board.Color; //resolve naming collision with awt.Color

/**
 * Represents the main game panel
 */
public class GamePanel extends JPanel
{
	private Board board;
	
	//Panel with the chessboard
	BoardPanel boardPanel;
	
	//Displays the previous move and current game state
	JLabel stateDescription;
	
//	private boolean isWhitesTurn; //true if it is currently white's turn
	private Color turn; //the color whose turn it is
	
	//Space of the currently selected piece. null if no piece is selected.
	//Values range from 0-8. x=column, y=row
	private Point selectedSpace;
	
	public GamePanel()
	{
		board = new Board();
		
		turn = Color.WHITE;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		boardPanel = new BoardPanel(this, board);
		add(boardPanel);
		
		stateDescription = new JLabel("Begin game");
		add(stateDescription);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		boardPanel.repaint();
	}
	
	/**
	 * Contains logic for when a space on the board is clicked.
	 * If no piece is currently selected, selects a piece if one is clicked.
	 * If a piece is currently selected, moves it or switches selections.
	 * 
	 * @param row the row clicked
	 * @param col the column clicked
	 */
	public void spaceClicked(int row, int col)
	{
		if (selectedSpace == null)
		{
			Piece clicked = board.getPiece(row, col);
			
			if (clicked != null && clicked.getColor() == turn)
			{
				System.out.println(turn + " selected " + clicked + " at " + row + "," + col);
				selectedSpace = new Point(col, row);
			}
		}
		//if a piece of the same color is selected, switch selection
		else if (board.getPiece(row, col) != null && 
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
				//only make the move if it doesn't leave the king in check
				if (!leavesKingInCheck(selectedSpace.y, selectedSpace.x, row, col))
				{
					makeMove(row, col);
				}
				else //TODO debug remove
				{
					System.out.println("could not move - would leave king in check");
				}
			}
			else //TODO debug remove
			{
				System.out.println("could not move " + board.getPiece(selectedSpace.y, selectedSpace.x) + " to " + row + "," + col);
			}
			
			selectedSpace = null;
		}
	}
	
	/**
	 * Move the piece at the currently selected space to the passed in row/column and switch turns.
	 * 
	 * @param destinationRow the destination row
	 * @param destinationCol the destination column
	 */
	private void makeMove(int destinationRow, int destinationCol)
	{
		//move the piece and switch turns
		board.movePiece(selectedSpace.y, selectedSpace.x, destinationRow, destinationCol);
		Piece movedPiece = board.getPiece(destinationRow, destinationCol);
		System.out.println("moved " + movedPiece + " to " + destinationRow + "," + destinationCol);
		
		//check for pawn promotion
		if (movedPiece instanceof Pawn)
		{
			promotePawn(destinationRow, destinationCol);
		}
		
		Color oppositeColor = turn.getOppositeColor();
		if (checkmate(oppositeColor))
		{
			System.out.println(turn + " wins!");
		}
		else if (inCheck(oppositeColor))
		{
			System.out.println(oppositeColor + " king in check.");
		}
		
		//Change turns
		turn = oppositeColor;
		
		repaint();
	}
	
	/**
	 * Determines if the king of the given color is in check
	 * 
	 * @param color the color of the king in check
	 * @return true if the king is in check
	 */
	public boolean inCheck(Color color)
	{
		Point kingSpace = color == Color.BLACK ? board.getBlackKingSpace() : board.getWhiteKingSpace();
		
		//Check each piece of the other color and see if it can take the king
		for (int r = 0; r < Board.BOARD_LENGTH; r++)
		{
			for (int c = 0; c < Board.BOARD_LENGTH; c++)
			{
				Piece piece = board.getPiece(r, c);
				if (piece != null && piece.getColor() != color && piece.canMove(board, r, c, kingSpace.y, kingSpace.x))
				{
					//TODO debug remove
					System.out.println(color + " king is in check");
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Determines if the color is in checkmate. If this method returns
	 * true then the color passed as the argument loses.
	 * 
	 * @param color the color to check
	 * @return true if the color is in checkmate
	 */
	public boolean checkmate(Color color)
	{
		//check if there are any valid moves that would not leave the king in check
		for (int r1 = 0; r1 < Board.BOARD_LENGTH; r1++)
		{
			for (int c1 = 0; c1 < Board.BOARD_LENGTH; c1++)
			{
				Piece piece = board.getPiece(r1, c1);
				if (piece != null && piece.getColor() == color && anyValidMovesExist(r1, c1, piece))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Checks if any valid moves exist for the piece at r1,c1.
	 * 
	 * @param r1 the row of the piece
	 * @param c1 the column of the piece
	 * @param piece the piece to move
	 * @return true if there are any valid moves for the piece
	 */
	private boolean anyValidMovesExist(int r1, int c1, Piece piece)
	{
		for (int r2 = 0; r2 < Board.BOARD_LENGTH; r2++)
		{
			for (int c2 = 0; c2 < Board.BOARD_LENGTH; c2++)
			{
				if (!(r1 == r2 && c1 == c2) &&
					piece.canMove(board, r1, c1, r2, c2) &&
					!leavesKingInCheck(r1, c1, r2, c2))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Simulates a move and determines if it would leave the king in check.
	 * Starting space must contain a piece.
	 * 
	 * @param startRow the starting row of the piece
	 * @param startCol the starting column of the piece
	 * @param endRow the ending row of the piece
	 * @param endCol the ending column of the piece
	 * @return true if the move would leave the king in check
	 */
	public boolean leavesKingInCheck(int startRow, int startCol, int endRow, int endCol)
	{
		//Keep track of board state
		Piece startPiece = board.getPiece(startRow, startCol);
//		Piece endPiece = board.getPiece(endRow, endCol);
		
		Move move = board.simulateMove(startRow, startCol, endRow, endCol);
		boolean isInCheck = inCheck(startPiece.getColor());
		
		//revert board state
		board.undoMove(move);
//		board.simulateMove(endRow, endCol, startRow, startCol);
//		board.setPiece(endPiece, endRow, endCol);
		
		return isInCheck;
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
			if (promoteTo.equals("Bishop"))
			{
				promoted = new Bishop(pawn.getColor());
			}
			else if (promoteTo.equals("Knight"))
			{
				promoted = new Knight(pawn.getColor());
			}
			else if (promoteTo.equals("Rook"))
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
		String[] options = {"Bishop", "Knight", "Rook", "Queen"};
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
}
