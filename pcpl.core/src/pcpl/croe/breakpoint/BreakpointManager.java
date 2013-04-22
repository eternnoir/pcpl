package pcpl.croe.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;




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
	
	public ArrayList<IMarker> diffResult(ArrayList<IMarker> nor,ArrayList<IMarker> rec){
		ArrayList<IMarker> _ret = new ArrayList<IMarker>();
		for(IMarker nm : nor){
			for(IMarker re : rec){
				if(nm.equals(re)){
					_ret.add(re);
				}
			}
		}
		for(IMarker sel : _ret){
			rec.remove(sel);
		}
		return rec;
	}

}
