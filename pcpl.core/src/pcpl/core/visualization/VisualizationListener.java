package pcpl.core.visualization;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IVariable;

public interface VisualizationListener {
	public abstract void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint);
}
