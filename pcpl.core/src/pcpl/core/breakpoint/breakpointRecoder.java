package pcpl.core.breakpoint;

import java.util.ArrayList;
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
			//System.out.print("1\n");
		}
		else{
			//System.out.print("2\n");
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
