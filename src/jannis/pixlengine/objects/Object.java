package jannis.pixlengine.objects;

import jannis.pixlengine.PixlEngine;

import javax.swing.ImageIcon;

/**
 * A class for any object in the world
 * 
 * @author jeyj0
 */
public class Object {

	/**
	 * The name of the icon for this object
	 */
	private String[] iconNames;

	/**
	 * The current state of this object. Used to determine the correct icon to
	 * display
	 */
	private int state;

	/**
	 * Constructor for objects with only one state and icon
	 * 
	 * @param iconName
	 */
	public Object(String iconName) {
		iconNames = new String[1];
		iconNames[0] = iconName;

		state = 0;
	}

	/**
	 * The icon for the current state
	 * 
	 * @return The icon to display for the current state
	 */
	public ImageIcon getIcon() {
		return new ImageIcon(PixlEngine.getResourcePath() + "/obj/"
				+ iconNames[state]);
	}

}
