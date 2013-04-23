package pcpl.core.mode;

import org.eclipse.core.resources.IMarker;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IVariable;

import pcpl.core.eventHandler.BreakPointListener;
import pcpl.core.eventHandler.EventCenter;
import pcpl.core.eventHandler.TargetCreationListener;
import pcpl.core.eventHandler.TargetTerminationListener;
import pcpl.croe.breakpoint.BreakpointManager;

public class TraceMode extends AbstractMode implements BreakPointListener,
		TargetCreationListener, TargetTerminationListener {
	private IDebugTarget[] _debugTargets = null;
	public TraceMode(){
		_modeType = 3;
		BreakpointManager.getInstance().removeAllBreakpoint();
		BreakpointManager.getInstance().setResult();
	}
	
	public void onBreakPointTriggered(IVariable[] variables,IBreakpoint breakpoint) {
		//ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		//int mode = EventCenter.getInstance().getModeType();
		_debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try{
		}
		catch(Exception ex){
			System.err.print("get breakpoint info error");
		}	

	}

}
