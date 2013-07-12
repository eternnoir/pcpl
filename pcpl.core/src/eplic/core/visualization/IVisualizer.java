package eplic.core.visualization;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

public interface IVisualizer {
	public String getVisualizerName();
	public String getVisualizerID();	
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint,IStackFrame[] stacks);
}
