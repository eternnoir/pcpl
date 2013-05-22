package pcpl.core.visualization;

import java.util.ArrayList;
import java.util.List;

public class VisualizerManager {
	private static VisualizerManager instance = null;
	 List<IVisualizer> _visualizerlist = new ArrayList<IVisualizer>();
	public static VisualizerManager getInstance() {
		if (instance == null) {
			instance = new VisualizerManager();
		}
		return instance;
	}
	
	public VisualizerManager(){
	}
	public void addVisualizer(IVisualizer vl){
		
	}
}
