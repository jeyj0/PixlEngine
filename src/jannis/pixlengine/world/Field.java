package jannis.pixlengine.world;

import jannis.pixlengine.objects.Object;

import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Field {

	private ArrayList<Object> objects;
	
	public Field() {
		objects = new ArrayList<Object>();
	}
	
	public ArrayList<ImageIcon> getIcons() {
		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
		
		for (Object o : objects)
			icons.add(o.getIcon());
		
		return icons;
	}

	public ArrayList<Object> getContent() {
		return objects;
	}

	public void addObstacle(ArrayList<Object> object) {
		objects = object;
	}

	public void removeObstacle(ArrayList<Object> object) {
		objects.remove(object);
	}
	
}
