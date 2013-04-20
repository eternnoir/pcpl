package pcpl.croe.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;

public class breakpointRecoder {
	private ArrayList<IMarker> _bpsm;
	
	public breakpointRecoder(){
		_bpsm = new ArrayList<IMarker>();
	}
	
	public void addBreakPointMarker(IMarker im){
		_bpsm.add(im);
	}
}
