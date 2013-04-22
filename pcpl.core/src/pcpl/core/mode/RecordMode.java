package pcpl.core.mode;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IVariable;

import pcpl.core.eventHandler.EventCenter;
import pcpl.croe.breakpoint.BreakpointManager;
import pcpl.croe.breakpoint.breakpointRecoder;

public class RecordMode extends AbstractMode {
	private breakpointRecoder _bpr;	
	private IDebugTarget[] _debugTargets = null;
	
	public RecordMode(){
		_bpr = new breakpointRecoder();
		_modeType = 2;
	}
	
	public void onBreakPointTriggered(IVariable[] variables,IBreakpoint breakpoint) {
		ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		_debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try{
			_bpr.addBreakPointMarker(lineBreakpoint.getMarker());
			System.out.print(lineBreakpoint.getLineNumber());
			this.cont();
		}
		catch(Exception ex){
			System.err.print("get breakpoint info error");
		}	

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
	
	public void onTargetTerminated() {
		ArrayList<IMarker> _ret;		
		_ret = BreakpointManager.getInstance().diffResult(EventCenter.getInstance().getNorMode().getBreakPointRecorder().getResult()
				, EventCenter.getInstance().getRecMode().getBreakPointRecorder().getResult());
	
	}
	
	
}
