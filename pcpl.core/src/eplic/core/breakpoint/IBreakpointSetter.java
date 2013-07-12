package eplic.core.breakpoint;

import org.eclipse.core.resources.IResource;

public interface IBreakpointSetter {
	public void setBreakpoint(IResource resource,int lineNum);
}
