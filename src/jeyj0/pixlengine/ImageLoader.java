package jeyj0.pixlengine;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Class to pre-load images for the game.
 * 
 * @author jeyj0
 */
public class ImageLoader {

	/**
	 * List of images loaded as int[]. <br>
	 * <br>
	 * int[0] = <i>width</i><br>
	 * int[1] = <i>height</i><br>
	 * int[2+n] = <i>pixel-value</i><br>
	 */
	private ArrayList<LoadedImage> imgs;

	/**
	 * Path to resources. In it, there should be an /img/ folder
	 */
	private String resourePath;

	/**
	 * Instantiates a new ImageLoader-object
	 * 
	 * @param resourcePath
	 */
	public ImageLoader(String resourcePath) {
		imgs = new ArrayList<LoadedImage>();
		this.resourePath = resourcePath;
	}

	/**
	 * Loads an image with the given name
	 * 
	 * @param name
	 *            A path to the image relative to /resourcePath/img/
	 * @return The id the image has in this ImageLoader instance
	 */
	public int loadFromPath(String name) {
		/*
		 * Read out the path
		 */
		BufferedImage readImg = null;

		String path = this.resourePath + "/img/" + name;
		System.out.println("Loading resource with path: " + path);
		try {
			readImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (readImg == null) {
			System.out.println("Couldn't read resource.");
			return -1;
		}

		/*
		 * Convert image to correct type for reading out the pixels
		 */
		BufferedImage img = new BufferedImage(readImg.getWidth(),
				readImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		img.getGraphics().drawImage(readImg, 0, 0, null);
		img.getGraphics().dispose();

		int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer())
				.getData();
		LoadedImage loadedImg = new LoadedImage(pixels, img.getWidth(),
				img.getHeight());

		imgs.add(loadedImg);
		return imgs.size() - 1;
	}

	/**
	 * Returns the image at the given index or an empty one if there is none at
	 * that index
	 * 
	 * @param index
	 *            The index the image should be at
	 * @return The image at the index
	 */
	public LoadedImage getLoadedImage(int index) {
		if (index < 0)
			return new LoadedImage(new int[0], 0, 0);
		return imgs.get(index);
	}

}
