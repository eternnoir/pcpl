package eplic.core.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ILineBreakpoint;

public class breakpointRecoder {
	private ArrayList<ILineBreakpoint> _bpsmN;
	private ArrayList<ILineBreakpoint> _bpsmR;
	
	public breakpointRecoder(){
		_bpsmN = new ArrayList<ILineBreakpoint>();
		_bpsmR = new ArrayList<ILineBreakpoint>();
	}
	
	public void addBreakPointMarker(ILineBreakpoint lineBreakpoint,int mode){
		if(mode == 1){
			_bpsmN.add(lineBreakpoint);
			
			// for performerce ,after add disable it
			try {
				lineBreakpoint.setEnabled(false);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			_bpsmR.add(lineBreakpoint);
		}

	}
	
	public ArrayList<ILineBreakpoint> getResultN(){
		return	_bpsmN;
	}
	public ArrayList<ILineBreakpoint> getResultR(){
		return	_bpsmR;
	}
}
