import javax.swing.*;

public class Main
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//TODO make a home screen
		frame.getContentPane().add(new GamePanel());
		
		frame.pack();
		frame.setVisible(true);
	}

}
