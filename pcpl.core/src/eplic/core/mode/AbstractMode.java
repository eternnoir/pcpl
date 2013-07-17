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
package eplic.core.mode;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

import eplic.core.breakpoint.breakpointRecoder;
import eplic.core.eventHandler.BreakPointListener;
import eplic.core.eventHandler.TargetCreationListener;
import eplic.core.eventHandler.TargetTerminationListener;
/**
 * this is an abstract class for mode. 
 * 
 * @author FrankWang
 *
 */
public abstract class AbstractMode implements BreakPointListener,
		TargetCreationListener, TargetTerminationListener {

	protected int _modeType = 0;
	
	
	@Override
	public void onTargetTerminated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTargetCreated(IDebugTarget target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint, IStackFrame[] stacks) {
		// TODO Auto-generated method stub

	}
	
	abstract public void init();
	abstract public void switchMode();
	/**
	 * get mode id
	 * @return
	 */
	public int getMode(){
		return _modeType;
	}
	
	public breakpointRecoder getBreakPointRecorder(){
		return null;
	}

}
