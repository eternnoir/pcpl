package eplic.core.mode;


import org.eclipse.core.resources.IMarker;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

import eplic.core.breakpoint.breakpointRecoder;
import eplic.core.eventHandler.EventCenter;

public class NormalMode extends AbstractMode {
	
	private breakpointRecoder _bpr;
	private IDebugTarget[] _debugTargets = null;
	public NormalMode(){
		_bpr = new breakpointRecoder();
		_modeType = 1;
	}
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint, IStackFrame[] stacks) {
		_debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		if(EventCenter.getInstance().getModeType() == 2)
			return;
		ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		try{
			IMarker m =lineBreakpoint.getMarker();
			if(m!=null)
			//	_bpr.addBreakPointMarker(m);
			this.cont();
		}
		catch(Exception ex){
			System.err.print("get breakpoint info error");
		}
	}
	
	public void onTargetTerminated() {
		// TODO Auto-generated method stub

	}
	
	public breakpointRecoder getBreakPointRecorder(){
		assert(_bpr != null);
		return _bpr;
	}
	
	public void cont(){
		for(IDebugTarget debugTarget : _debugTargets){
			try {
				if(debugTarget.canResume()){
					debugTarget.resume();
				}

			} catch (DebugException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
