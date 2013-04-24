package pcpl.core.mode;

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
		int mode = EventCenter.getInstance().getModeType();
		_debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try{
			IMarker m =lineBreakpoint.getMarker();
			if(m!=null){
				_bpr.addBreakPointMarker(lineBreakpoint,mode);
			}
			//System.out.print(lineBreakpoint.getLineNumber());
			this.cont();
		}
		catch(Exception ex){
			System.err.print("get breakpoint info error");
			this.cont();
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
		BreakpointManager.getInstance().diffResult(_bpr.getResultN(),_bpr.getResultR());
	
	}
	
	
}
