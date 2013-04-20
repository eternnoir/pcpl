package pcpl.core.mode;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IVariable;

import pcpl.croe.breakpoint.breakpointRecoder;

public class NormalMode extends AbstractMode {
	
	private breakpointRecoder _bpr;
	public NormalMode(){
		_bpr = new breakpointRecoder();
		_modeType = 1;
	}
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint) {
		ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		try{
			_bpr.addBreakPointMarker(lineBreakpoint.getMarker());
		}
		catch(Exception ex){
			
		}
		//System.out.print(lineBreakpoint.getLineNumber());

	}
	
	public breakpointRecoder getBreakPointRecorder(){
		assert(_bpr != null);
		return _bpr;
	}
	
}
