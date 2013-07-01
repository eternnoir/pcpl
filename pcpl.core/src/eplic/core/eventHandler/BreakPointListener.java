package eplic.core.eventHandler;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

public interface BreakPointListener {
	public abstract void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint, IStackFrame[] stacks);
}
