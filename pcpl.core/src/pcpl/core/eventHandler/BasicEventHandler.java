package pcpl.core.eventHandler;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;



public class BasicEventHandler extends AbstractEventHandler {

	public BasicEventHandler(){

	}
	@Override
	protected boolean handleBreakpointEvent(IDebugElement dElement) {
		boolean ok = false;
		IDebugTarget dTarget = dElement.getDebugTarget();
		try {
			if (dTarget.hasThreads()) {
				IThread[] threads = dTarget.getThreads();
				for (IThread thread : threads) {
					if (thread.hasStackFrames()) {
						IBreakpoint[] bps = thread.getBreakpoints();
						IStackFrame stack = thread.getTopStackFrame();
						IVariable[] variables = stack.getVariables();
						for (BreakPointListener listener : this.bpListeners) {
							listener.onBreakPointTriggered(variables, bps[0]);
						}
						ok = true;
					}
				}
			} else {
				System.out.print(dTarget.getName());
				System.out.println(" has no threads.");
			}
		} catch (DebugException e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	protected boolean handleTargetCreatedEvent(IDebugElement dElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean handleTargetTerminateEvent(IDebugElement dElement) {
		// TODO Auto-generated method stub
		return false;
	}

}
