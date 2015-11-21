package jeyj0.pixlengine;

import jeyj0.pixlengine.world.World;

/**
 * The main class for the a new engine.
 * 
 * @author jeyj0
 */
public class PixlEngine {

	/**
	 * Path to all resources (icons etc.)
	 */
	private static String resourcePath;

	/**
	 * Name of the current project. Used for example as title for the main
	 * frame.
	 */
	private static String projectName;

	/**
	 * Size of the chunks in the world
	 */
	private static int chunkSize;

	/**
	 * World-object the engine is currently using to render
	 */
	private World world;

	/**
	 * The frame the engine displays everything in
	 */
	private MainFrame frame;

	/**
	 * Instantiates a new PixlEngine
	 * 
	 * @param worldX
	 *            Horizontal chunk count for the world that is going to be
	 *            created
	 * @param worldY
	 *            Vertical chunk count for the world that is going to be created
	 * @param projectName
	 *            Name of the project
	 * @param resourcePath
	 *            Path to all resources such as icons
	 * @param chunkSize
	 *            Size of the chunks in the world
	 */
	public PixlEngine(int worldX, int worldY, String projectName,
			String resourcePath, int chunkSize) {
		PixlEngine.projectName = projectName;
		PixlEngine.resourcePath = resourcePath;
		PixlEngine.chunkSize = chunkSize;
		
		world = new World(worldX, worldY);
		frame = new MainFrame(this, PixlEngine.projectName);
		frame.setVisible(true);
	}

	/**
	 * Path to all resources such as icons
	 * 
	 * @return resourcePath
	 */
	public static String getResourcePath() {
		return resourcePath;
	}

	/**
	 * The current project's name
	 * 
	 * @return projectName
	 */
	public static String getProjectName() {
		return projectName;
	}

	/**
	 * The set chunk size
	 * 
	 * @return chunkSize
	 */
	public static int getChunkSize() {
		return chunkSize;
	}

	/**
	 * The world instance currently rendering
	 * 
	 * @return world
	 */
	public World getWorldInstance() {
		return world;
	}

	/**
	 * Render world as it is to calling time
	 */
	public void render() {
		frame.showStuff();
	}

}
