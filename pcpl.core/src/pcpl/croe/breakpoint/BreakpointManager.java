package pcpl.croe.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;

public class BreakpointManager {
	private static BreakpointManager instance = null;
	private  ArrayList<ILineBreakpoint> _result = null;
	public static BreakpointManager getInstance() {
		if (instance == null) {
			instance = new BreakpointManager();
		}
		return instance;
	}
	
	public BreakpointManager(){
		_result = null;
	}
	
	public ArrayList<ILineBreakpoint> diffResult(ArrayList<ILineBreakpoint> nor,ArrayList<ILineBreakpoint> rec){
		for(ILineBreakpoint nm : nor){
			while(rec.indexOf(nm)!= -1){
				rec.remove(nm);
			}
		}
		_result = new ArrayList<ILineBreakpoint>(rec.size());
		for(ILineBreakpoint b : rec){
			_result.add(b);
		}
		return _result;
	}
	
	public ArrayList<ILineBreakpoint> getResult(){
		assert(_result != null);
		return _result;
	}
	
	public void removeAllBreakpoint(){
		IBreakpoint[] b = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints();
		for(IBreakpoint _b : b){
			try {
				_b.setEnabled(false);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setResult(){
		for(ILineBreakpoint b : _result){
			try {
				b.setEnabled(true);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
