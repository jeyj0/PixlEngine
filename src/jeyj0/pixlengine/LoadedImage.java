package jeyj0.pixlengine;

/**
 * An object representation for Images
 * 
 * @author jeyj0
 */
public class LoadedImage {

	/**
	 * List of pixels in this image
	 */
	private int[] pixels;

	/**
	 * Width of this image (in pixels)
	 */
	private int width;

	/**
	 * Height of this image (in pixels)
	 */
	private int height;

	/**
	 * Instantiates a new LoadedImage-object
	 * 
	 * @param pixels
	 *            The pixel values in this image
	 * @param width
	 *            The width of this image in pixels
	 * @param height
	 *            The height of this image in pixels
	 */
	public LoadedImage(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return The pixeldata of this image
	 */
	public int[] getPixels() {
		return pixels;
	}

	/**
	 * @return The width of this image in pixels
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return The height of this image in pixels
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns a cut-out from this image
	 * 
	 * @param startX
	 *            The x-coordinate to start at (upper left corner)
	 * @param startY
	 *            The y-coordinate to start at (upper left corner)
	 * @param intervalWidth
	 *            The maximum width for the resulting image - this will be
	 *            smaller if the image is not big enough to fill everything
	 * @param intervalHeight
	 *            The maximum width for the resulting image - this will be
	 *            smaller if the image is not big enough to fill everything
	 * @return The resulting cut-out as another LoadedImage-instance
	 */
	public LoadedImage getInterval(int startX, int startY, int intervalWidth,
			int intervalHeight) {
		intervalWidth = startX + intervalWidth <= width && intervalWidth >= 0 ? intervalWidth
				: width - startX;
		intervalHeight = startY + intervalHeight <= height
				&& intervalHeight >= 0 ? intervalHeight : height - startY;

		int[] intervalPixels = new int[intervalWidth * intervalHeight];

		for (int x = 0; x < intervalWidth; x++)
			for (int y = 0; y < intervalHeight; y++)
				intervalPixels[y * width + x] = getPixel(x, y);

		LoadedImage loadedImage = new LoadedImage(intervalPixels,
				intervalWidth, intervalHeight);
		return loadedImage;
	}

	/**
	 * Returns the color-value of a specific pixel with the given coordinates
	 * 
	 * @param x
	 *            The x-coordinate of the pixel to get the color of
	 * @param y
	 *            The y-coordinate of the pixel to get the color of
	 * @return The color-value of the pixel
	 */
	public int getPixel(int x, int y) {
		return pixels[y * width + x];
	}

}
