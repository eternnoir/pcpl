package pcpl.croe.breakpoint;

import java.util.ArrayList;

import org.eclipse.core.resources.IMarker;

public class breakpointRecoder {
	private ArrayList<IMarker> _bpsm;
	
	public breakpointRecoder(){
		_bpsm = new ArrayList<IMarker>();
	}
	
	public void addBreakPointMarker(IMarker im){
		System.out.print(im.getResource().getFullPath().toString());
		_bpsm.add(im);
	}
	
	public ArrayList<IMarker> getResult(){
		return	_bpsm;
	}
}
