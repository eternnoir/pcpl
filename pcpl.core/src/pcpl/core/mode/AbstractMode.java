package pcpl.core.mode;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IVariable;

import pcpl.core.eventHandler.BreakPointListener;
import pcpl.core.eventHandler.TargetCreationListener;
import pcpl.core.eventHandler.TargetTerminationListener;
import pcpl.croe.breakpoint.breakpointRecoder;

public abstract class AbstractMode implements BreakPointListener,
		TargetCreationListener, TargetTerminationListener {

	protected int _modeType = 0;
	
	
	@Override
	public void onTargetTerminated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTargetCreated(IDebugTarget target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint) {
		// TODO Auto-generated method stub

	}
	
	public int getMode(){
		return _modeType;
	}
	
	public breakpointRecoder getBreakPointRecorder(){
		return null;
	}

}
