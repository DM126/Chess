package board;

/**
 * Represents a piece color
 */
public enum Color
{
	BLACK {
		public Color getOppositeColor() { return Color.WHITE; }
	},
	WHITE {
		public Color getOppositeColor() { return Color.BLACK; }
	};
	
	/**
	 * Gets the opposite color from the one passed in.
	 * 
	 * @param color the color to get the opposite of
	 * @return the opposite color
	 */
	public abstract Color getOppositeColor();
}
