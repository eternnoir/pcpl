package pcpl.core.breakpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.jdt.debug.core.IJavaBreakpointListener;
import org.eclipse.jdt.debug.core.JDIDebugModel;
import org.eclipse.jdt.internal.debug.core.breakpoints.JavaLineBreakpoint;

import pcpl.core.mode.RecordMode;

public class BreakpointSetter {
	private static BreakpointSetter instance = null;
	public static BreakpointSetter getInstance() {
		if (instance == null) {
			instance = new BreakpointSetter();
		}
		return instance;
	}
	
	public BreakpointSetter(){

	}
	
	public void setBreakpoint(IResource resource,int lineNum){
		String typeName = FileParaviserUtils.getClassName(resource);	//javabreakpoint need this
		try {
			IBreakpoint bp = JDIDebugModel.createLineBreakpoint(resource,typeName, lineNum, -1, -1, 0,true,null);
			DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(bp);
			System.out.print("setBreakpointat "+typeName+":"+lineNum+"\n");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
