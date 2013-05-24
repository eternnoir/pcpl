package pcpl.core.visualization;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;

import pcpl.core.breakpoint.BreakpointManager;

public class VisualizerManager {
	private static VisualizerManager instance = null;
	 List<IVisualizer> _visualizerList = new ArrayList<IVisualizer>();
	public static VisualizerManager getInstance() {
		if (instance == null) {
			instance = new VisualizerManager();
		}
		return instance;
	}
	
	public VisualizerManager(){
	}
	public void addVisualizer(IVisualizer vl){
		_visualizerList.add(vl);
	}
	public List<IVisualizer> getVisualizerList(){
		return _visualizerList;
	}
	
	public ArrayList<ILineBreakpoint> getNorSet(){
		return BreakpointManager.getInstance().getNormalSet();
	}
	
	public ArrayList<ILineBreakpoint> getRecSet(){
		return BreakpointManager.getInstance().getRecordSet();
	}
	
	public IResource getResourceByBreakpoint(IBreakpoint b){
		return BreakpointManager.getInstance().getResourceByBreakpoint(b);
	}
}
