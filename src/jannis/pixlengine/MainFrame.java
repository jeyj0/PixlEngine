package jannis.pixlengine;

import jannis.pixlengine.world.Field;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Main frame to display the whole program in
 * 
 * @author jeyj0
 */
public class MainFrame extends JFrame {

	/**
	 * Panel containing icons from world
	 */
	private JPanel panel;

	/**
	 * Instantiates a new frame
	 * 
	 * @param title
	 *            The title of the frame
	 */
	public MainFrame(String title) {
		super();

		// ends java program on window close event
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// sets the frame's title
		setTitle(title);

		// set bounds
		setBounds(500, 200, 800, 450);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);

		// Instantiate panel and make background black
		panel = new JPanel();
		panel.setBounds(0, 0, 800, 450);
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
	}

	/**
	 * Test-method to show some things when called
	 */
	public void showStuff() {
		Field f = new Field();
		ArrayList<ImageIcon> icons = f.getIcons();

		for (ImageIcon icon : icons) {
			JLabel imgLabel = new JLabel();
			imgLabel.setIcon(icon);
			panel.add(imgLabel);
			imgLabel.setBounds(1, 1, 32, 32);
			imgLabel.setVisible(true);
		}
	}

}
