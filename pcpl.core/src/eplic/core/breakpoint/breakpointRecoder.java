package eplic.core.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ILineBreakpoint;

public class breakpointRecoder {
	private ArrayList<ILineBreakpoint> _bps;
	
	public breakpointRecoder(){
		_bps = new ArrayList<ILineBreakpoint>();
	}
	
	public void addBreakPoint(ILineBreakpoint lineBreakpoint){
	
		_bps.add(lineBreakpoint);
	
	}
	
	public ArrayList<ILineBreakpoint> getBPS(){
		return	_bps;
	}
}
