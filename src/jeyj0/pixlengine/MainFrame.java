package jeyj0.pixlengine;


import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeyj0.pixlengine.world.Chunk;
import jeyj0.pixlengine.world.Field;

/**
 * Main frame to display the whole program in
 * 
 * @author jeyj0
 */
public class MainFrame extends JFrame {

	/**
	 * Serial version ID needed for java... Just leave this here :P
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel containing icons from world
	 */
	private JPanel panel;

	/**
	 * PixlEngine Object running this frame
	 */
	private PixlEngine engine;

	/**
	 * Instantiates a new frame
	 * 
	 * @param title
	 *            The title of the frame
	 */
	public MainFrame(PixlEngine engine, String title) {
		super();

		this.engine = engine;

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
		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
		Chunk[][] chunks = engine.getWorldInstance().getChunks();

		for (Chunk[] chunks_i : chunks) {
			for (Chunk c : chunks_i) {
				for (Field[] fields_i : c.getFields()) {
					for (Field f : fields_i) {
						if (f.getIcons().size() > 0)
							System.out.println(">0 Objects");
						icons.addAll(f.getIcons());
					}
				}
			}
		}

		for (ImageIcon icon : icons) {
			JLabel imgLabel = new JLabel();
			imgLabel.setIcon(icon);
			panel.add(imgLabel);
			imgLabel.setBounds(1, 1, 32, 32);
			imgLabel.setVisible(true);
		}
	}

}
