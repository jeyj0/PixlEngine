package jannis.pixlengine.world;

import jannis.pixlengine.objects.Object;

import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * A class to contain all info about a field (also all objects on it)
 * 
 * @author jeyj0
 */
public class Field {

	/**
	 * A list of all objects on the field
	 */
	private ArrayList<Object> objects;

	/**
	 * Instantiates a new field object
	 */
	public Field() {
		objects = new ArrayList<Object>();
	}

	/**
	 * Returns all icons to display on this field
	 * 
	 * @return A list containing all icons to display on this field
	 */
	public ArrayList<ImageIcon> getIcons() {
		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();

		for (Object o : objects)
			icons.add(o.getIcon());

		return icons;
	}

	/**
	 * A list containing all objects on this field
	 * 
	 * @return The list of objects on this field
	 */
	public ArrayList<Object> getContent() {
		return objects;
	}

	/**
	 * Adds an object to this field
	 * 
	 * @param object
	 *            The object to add
	 */
	public void addObject(ArrayList<Object> object) {
		objects = object;
	}

	/**
	 * Removes an object from this field
	 * 
	 * @param object
	 *            The object to remove
	 */
	public void removeObject(ArrayList<Object> object) {
		objects.remove(object);
	}

}
