package eplic.core.eventHandler;

import java.util.ConcurrentModificationException;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;



/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * 
 * @author FrankWang
 *
 */

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
						IStackFrame[] stacks = thread.getStackFrames();
						IVariable[] variables = stack.getVariables();
						for (BreakPointListener listener : this.bpListeners) {
							listener.onBreakPointTriggered(variables, bps[0], stacks);
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
	protected boolean handleTargetTerminateEvent(IDebugElement dElement) {
		boolean ok = false;
		try {
			for (TargetTerminationListener listener : this.terminationListeners) {
				listener.onTargetTerminated();
			}
			ok = true;
		} catch (ConcurrentModificationException e) {
			return handleTargetTerminateEvent(dElement);
		} // see http://www.javaperformancetuning.com/articles/fastfail2.shtml
		return ok;
	}

	@Override
	protected boolean handleTargetCreatedEvent(IDebugElement dElement) {
		for (TargetCreationListener l : this.creationListeners) {
			l.onTargetCreated(dElement.getDebugTarget());
		}
		return true;
	}

}
