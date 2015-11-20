package jannis.pixlengine;

public class PixlEngine {

	private static String resourcePath;
	private static String projectName;
	private static int chunkSize;
	
	private MainFrame frame;
	
	public PixlEngine(String resourcePath, String projectName, int chunkSize) {
		PixlEngine.resourcePath = resourcePath;
		PixlEngine.projectName = projectName;
		PixlEngine.chunkSize = chunkSize;
		
		frame = new MainFrame(PixlEngine.projectName);
		frame.setVisible(true);
	}
	
	public static String getResourcePath() {
		return resourcePath;
	}
	
	public static String getProjectName() {
		return projectName;
	}
	
	public static int getChunkSize() {
		return chunkSize;
	}
	
}
