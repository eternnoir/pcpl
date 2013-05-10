package pcpl.core.visualization;

public class VisualizerManager {
	private static VisualizerManager instance = null;
	public static VisualizerManager getInstance() {
		if (instance == null) {
			instance = new VisualizerManager();
		}
		return instance;
	}
	
	public VisualizerManager(){
	}
	public void addVisualizer(VisualizerListener vl){
		
	}
}
