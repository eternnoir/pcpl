package eplic.core.mode;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

import eplic.core.breakpoint.breakpointRecoder;
import eplic.core.eventHandler.BreakPointListener;
import eplic.core.eventHandler.TargetCreationListener;
import eplic.core.eventHandler.TargetTerminationListener;

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
			IBreakpoint breakpoint, IStackFrame[] stacks) {
		// TODO Auto-generated method stub

	}
	
	abstract public void init();
	
	public int getMode(){
		return _modeType;
	}
	
	public breakpointRecoder getBreakPointRecorder(){
		return null;
	}

}
