package pcpl.core.visualization;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IVariable;

public interface VisualizerListener {
	public abstract void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint);
}
