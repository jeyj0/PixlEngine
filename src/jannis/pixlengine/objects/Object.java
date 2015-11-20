package jannis.pixlengine.objects;

import jannis.pixlengine.PixlEngine;

import javax.swing.ImageIcon;

public class Object {
	
	private String iconName;
	
	public Object(String iconName) {
		this.iconName = iconName;
	}

	public ImageIcon getIcon() {
		return new ImageIcon(PixlEngine.getResourcePath() + "/obj/" + iconName);
	}
	
}
