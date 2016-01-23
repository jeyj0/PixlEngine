package jeyj0.pixlengine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashSet;

import javax.swing.JFrame;

import jeyj0.pixlengine.entities.Entity;
import jeyj0.pixlengine.entities.Mob;
import jeyj0.pixlengine.gui.GuiComponent;
import jeyj0.pixlengine.in.InputHandler;
import jeyj0.pixlengine.world.World;
import jeyj0.pixlengine.world.World.TileField;

/**
 * Main Engine class. Use this to run a (new) game.
 * 
 * @author jeyj0
 */
public class PixlEngine extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	/**
	 * Name of the current project/game to render
	 */
	private String projectName;

	/**
	 * Relative path to resources
	 */
	private String resourcePath;

	/**
	 * Determines whether the game is running or not
	 */
	private boolean running;

	/**
	 * Standard count. This sets the value that is going to be used.
	 */
	private int ticksPerSecond = 60;

	/**
	 * Total count of ticks that already happened
	 */
	private int tickCount;

	/**
	 * Loader for all images used in the game
	 */
	private ImageLoader imageLoader;

	/**
	 * World containing everything for this game
	 */
	private World world;

	private HashSet<GuiComponent> guiComponents;

	/**
	 * Amount of fields shown on the screen (horizontally)
	 */
	private int screenFieldWidth;

	/**
	 * Amount of fields shown on the screen (vertically)
	 */
	private int screenFieldHeight;

	/**
	 * Number of pixels per field
	 */
	private int pxPerField;

	/**
	 * How many actual pixels are one game pixel
	 */
	private int scaleFactor;

	/**
	 * The screens horizontal offset
	 */
	private double xOffset;

	/**
	 * The screens vertical offset
	 */
	private double yOffset;

	/**
	 * The final rendered image to display
	 */
	private BufferedImage image;

	/**
	 * The pixels in the rendered image
	 */
	private int[] pixels;

	/**
	 * Amount of game pixels horizontally
	 */
	private int xPixels;

	/**
	 * Amount of game pixels vertically
	 */
	private int yPixels;

	/**
	 * The frame the game is rendered in
	 */
	private JFrame frame;

	/**
	 * The Mob to get the offset after. This mob will always be centered and
	 * followed around. If not set, the offset must be defined in another way.
	 */
	private Mob player;

	/**
	 * Event-handler to use
	 */
	private InputHandler inputHandler;

	/**
	 * Instantiates a new engine-object
	 * 
	 * @param name
	 *            The name of the game
	 * @param height
	 *            The viewable height in fields
	 * @param width
	 *            The viewable width in fields
	 * @param pxPerField
	 *            The pixels on one field
	 * @param scaleFactor
	 *            The pixel display size (How many screen pixels are one game
	 *            pixel)
	 * @param resPath
	 *            The path to all needed resources
	 */
	public PixlEngine(String projectName, int height, int width,
			int pxPerField, int scaleFactor, String resPath) {
		/*
		 * Set instance variables
		 */
		this.projectName = projectName;
		this.resourcePath = resPath;
		this.running = false;
		this.tickCount = 0;
		this.pxPerField = pxPerField;
		this.scaleFactor = scaleFactor;
		this.screenFieldWidth = width;
		this.screenFieldHeight = height;

		/*
		 * Set up rendered image
		 */
		image = new BufferedImage(width * pxPerField, height * pxPerField,
				BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		xPixels = width * pxPerField;
		yPixels = height * pxPerField;

		/*
		 * Set up Canvas
		 */
		int canvasWidth = width * pxPerField * scaleFactor;
		int canvasHeight = height * pxPerField * scaleFactor;
		setMinimumSize(new Dimension(canvasWidth, canvasHeight));
		setMaximumSize(new Dimension(canvasWidth, canvasHeight));
		setPreferredSize(new Dimension(canvasWidth, canvasHeight));

		/*
		 * Set up frame
		 */
		frame = new JFrame(this.projectName);
		// setting main info
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		// adding components
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		// displaying
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		/*
		 * Create instance objects
		 */
		this.imageLoader = new ImageLoader(this.resourcePath);
		this.world = new World();
		this.guiComponents = new HashSet<GuiComponent>();
		this.inputHandler = new InputHandler(this);
	}

	/**
	 * Start method for runnable
	 */
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	/**
	 * Stop method for runnable
	 */
	public synchronized void stop() {
		running = false;
	}

	/**
	 * What is being run in the game thread by runnable
	 */
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / ticksPerSecond; // Nano-seconds per
															// tick

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		boolean shouldRender;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			shouldRender = false;

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");

				ticks = 0;
				frames = 0;
			}
		}
	}

	/**
	 * Executes a game tick. That is recalculating the world.
	 */
	public void tick() {
		// tick all entities
		for (Entity e : getWorld().getAllEntities())
			e.tick();

		tickCount++;
	}

	/**
	 * Changes the cameras viewpoint to the offset
	 * 
	 * @param x
	 *            The horizontal offset
	 * @param y
	 *            The vertical offset
	 */
	public void setOffset(double x, double y) {
		this.xOffset = x;
		this.yOffset = y;

		// System.out.println("xOffset in pixels: " + (int) (xOffset *
		// pxPerField)
		// + " (" + (double) (xOffset * pxPerField) + ")");
		// System.out.println("yOffset in pixels: " + (int) (yOffset *
		// pxPerField)
		// + " (" + (double) (yOffset * pxPerField) + ")");
	}

	/**
	 * Renders the game as it is calculated right now.
	 */
	public void render() {
		/*
		 * center player if set
		 */
		if (player != null) {
			setOffset(player.getX() + (double) player.getWidth() / 2
					- (double) screenFieldWidth / 2, player.getY()
					+ (double) player.getHeight() / 2
					- (double) screenFieldHeight / 2);
		}

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		/*
		 * edit image here
		 */
		for (int x = 0; x < xPixels; x++) {
			for (int y = 0; y < yPixels; y++) {
				colorPixel(0xff000000, x, y, true); // 0xAARRGGBB
													// background-color
			}
		}

		/*
		 * Render Back GuiComponents
		 */
		for (GuiComponent c : guiComponents)
			if (!c.isFront())
				renderLoadedImage(c.getGraphics(), c.getLeft(), c.getTop());

		/*
		 * Render tiles
		 */
		for (TileField f : getWorld().getFieldsInRect(xOffset, yOffset,
				screenFieldWidth, screenFieldHeight)) {
			LoadedImage loadedImage = imageLoader.getLoadedImage(f.getTile()
					.getImageId());

			int startX = (int) ((f.getX() - xOffset) * pxPerField);
			int startY = (int) ((f.getY() - yOffset) * pxPerField);

			// TODO: edit this to actually take only the needed part of the
			// image!!! Note: startX and startY are relative to the screen, not
			// to the loadedImage
			loadedImage = loadedImage.getInterval(0, 0, -1, -1);
			renderLoadedImage(loadedImage, startX, startY);
		}

		/*
		 * Render entities
		 */
		for (Entity e : getWorld().getEntitiesInRect(xOffset, yOffset,
				screenFieldWidth, screenFieldHeight)) {
			LoadedImage loadedImage = imageLoader
					.getLoadedImage(e.getImageId());

			int startX = (int) ((e.getX() - xOffset) * pxPerField);
			int startY = (int) ((e.getY() - yOffset) * pxPerField);

			renderLoadedImage(loadedImage, startX, startY);
		}

		/*
		 * Render Front GuiComponents
		 */
		for (GuiComponent c : guiComponents)
			if (c.isFront())
				renderLoadedImage(c.getGraphics(), c.getLeft(), c.getTop());

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();
		bs.show();
	}

	/**
	 * Colors the pixel at the given coordinates
	 * 
	 * @param colorValue
	 *            The color-value to set the pixel to
	 * @param x
	 *            The y coordinate of the pixel
	 * @param y
	 *            The x coordinate of the pixel
	 * @param overwrite
	 *            Whether the new color should override all previous or it
	 *            should be merged with the ones underneath
	 * @return Whether the pixel exists
	 */
	private boolean colorPixel(int colorValue, int x, int y, boolean overwrite) {
		// if x or y is negative
		if (x < 0 || y < 0)
			return false;

		// calculate the index in the pixels array
		int index = y * xPixels + x;

		// if a line was added by the x OR the index is too high for the image
		if ((index - index % xPixels) / xPixels > y || index >= pixels.length)
			return false;

		if (overwrite || (colorValue >> 24 & 0xff) == 0xff)
			pixels[index] = colorValue;
		else
			pixels[index] = mergeColors(pixels[index], colorValue);
		return true;
	}

	/**
	 * Colors a single pixel on the screen (merging it with the underlying
	 * color)
	 * 
	 * @param colorValue
	 *            The value to color the screen in
	 * @param x
	 *            The x coordinate of the pixel
	 * @param y
	 *            The y coordinate of the pixel
	 * @return Whether the pixel exists
	 */
	private boolean colorPixel(int colorValue, int x, int y) {
		return colorPixel(colorValue, x, y, false);
	}

	/**
	 * Merges two colors
	 * 
	 * @param c1
	 *            Color one
	 * @param c2
	 *            Color two
	 * @return The merging result
	 */
	private static int mergeColors(int c1, int c2) {
		/*
		 * Extracting the bit-data
		 */
		int c1a = c1 >> 24 & 0xff;
		int c1r = (c1 & 0xff0000) >> 16;
		int c1g = (c1 & 0xff00) >> 8;
		int c1b = c1 & 0xff;

		int c2a = c2 >> 24 & 0xff;
		int c2r = (c2 & 0xff0000) >> 16;
		int c2g = (c2 & 0xff00) >> 8;
		int c2b = c2 & 0xff;

		/*
		 * Adding the colors
		 */
		// If the value gets too high it would be transparent again, so the
		// maximum is 255 (if the value gets higher than 255 it becomes 255)
		int c3a = (c1a + c2a);// > 255 ? 255 : (c1a + c2a);
		int c3r = (c1r * c1a + c2r * c2a) / c3a;
		int c3g = (c1g * c1a + c2g * c2a) / c3a;
		int c3b = (c1b * c1a + c2b * c2a) / c3a;

		return c3a << 24 | c3r << 16 | c3g << 8 | c3b;
	}

	/**
	 * Renders a LoadedImage onto the screen
	 * 
	 * @param img
	 *            The image to render
	 * @param startX
	 *            The x-offset of the picture on the screen
	 * @param startY
	 *            The y-offset of the picture on the screen
	 */
	private void renderLoadedImage(LoadedImage img, int startX, int startY) {
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++)
				colorPixel(img.getPixel(x, y), startX + x, startY + y);
	}

	/**
	 * @return The ImageLoader object to load images used in the game in
	 */
	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	/**
	 * @return The currently used world for rendering
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return The Gui Components currently in this engine
	 */
	public HashSet<GuiComponent> getGuiComponents() {
		return guiComponents;
	}

	/**
	 * Adds a GuiComponent to the list of currently displayed components
	 * 
	 * @param component
	 *            The component to add to the list
	 */
	public void addGuiComponent(GuiComponent component) {
		guiComponents.add(component);
	}

	/**
	 * @return total tick count since starting of the game
	 */
	public int getTickCount() {
		return tickCount;
	}

	/**
	 * Switches the focus to the new player
	 * 
	 * @param player
	 *            The Mob to take in focus
	 * @return Whether the player is in the world used. If not, it hasn't been
	 *         set as player.
	 */
	public boolean setPlayer(Mob player) {
		if (getWorld().hasEntity(player)) {
			this.player = player;
			return true;
		}
		return false;
	}

	/**
	 * The followed Mob
	 * 
	 * @return The currently set player
	 */
	public Mob getPlayer() {
		return player;
	}

	/**
	 * @return The used InputHandler for this game
	 */
	public InputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * @return The amount of screen pixels that make up one game pixel in the
	 *         width and height
	 */
	public int getScaleFactor() {
		return scaleFactor;
	}

	/**
	 * @return The amount of game pixels that make up one field in the game
	 */
	public int getPixelsPerField() {
		return pxPerField;
	}

	/**
	 * @return The frame used to display with this engine
	 */
	public JFrame getFrame() {
		return frame;
	}

}
