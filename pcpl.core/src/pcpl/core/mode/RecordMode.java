package pcpl.core.mode;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IVariable;

import pcpl.croe.breakpoint.breakpointRecoder;

public class RecordMode extends AbstractMode {
	private breakpointRecoder _bpr;
	public RecordMode(){
		_bpr = new breakpointRecoder();
		_modeType = 2;
	}
	
	public void onBreakPointTriggered(IVariable[] variables,IBreakpoint breakpoint) {
		ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		try{
			_bpr.addBreakPointMarker(lineBreakpoint.getMarker());
			System.out.print(lineBreakpoint.getLineNumber());
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
