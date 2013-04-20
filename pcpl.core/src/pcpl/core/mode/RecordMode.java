package pcpl.core.mode;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IVariable;

public class RecordMode extends AbstractMode {
	
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint) {
		ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		try{
			//_bpr.addBreakPointMarker(lineBreakpoint.getMarker());
		}
		catch(Exception ex){
			
		}
		//System.out.print(lineBreakpoint.getLineNumber());

	}
	
	public void onTargetTerminated() {
		int i = 0;
	}
}
