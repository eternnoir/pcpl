/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eplic.core.eventHandler;

import java.util.ConcurrentModificationException;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;


/**.
 * 
 * A basic event handler.實作了一些EPLIC會用到的event
 * 
 * @author FrankWang
 *
 */

public class BasicEventHandler extends AbstractEventHandler {

	public BasicEventHandler(){

	}
	/**
	 * See Eclipse debug event
	 */
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
