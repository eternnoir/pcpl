package pcpl.croe.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;




public class BreakpointManager {
	private static BreakpointManager instance = null;
	
	public static BreakpointManager getInstance() {
		if (instance == null) {
			instance = new BreakpointManager();
		}
		return instance;
	}
	
	public BreakpointManager(){
		
	}
	
	public ArrayList<ILineBreakpoint> diffResult(ArrayList<ILineBreakpoint> nor,ArrayList<ILineBreakpoint> rec){
		ArrayList<ILineBreakpoint> _ret = new ArrayList<ILineBreakpoint>();
		int i;
		for(ILineBreakpoint nm : nor){
			while(rec.indexOf(nm)!= -1){
				rec.remove(nm);
			}

		}
		return rec;
	}

}
