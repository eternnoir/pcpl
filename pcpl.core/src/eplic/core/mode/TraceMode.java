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

import java.util.List;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

import eplic.core.breakpoint.BreakpointManager;
import eplic.core.eventHandler.BreakPointListener;
import eplic.core.eventHandler.EventCenter;
import eplic.core.eventHandler.TargetCreationListener;
import eplic.core.eventHandler.TargetTerminationListener;
import eplic.core.visualization.IVisualizer;
import eplic.core.visualization.VisualizerManager;
/**
 * Trace Mode 當EPLIC分析完後使用推薦的中斷點進行trace時的mode
 * @author FrankWang
 *
 */
public class TraceMode extends AbstractMode implements BreakPointListener,
		TargetCreationListener, TargetTerminationListener {
	private IDebugTarget[] _debugTargets = null;
	public TraceMode(){
		_modeType = 3;
	}
	
	public void onBreakPointTriggered(IVariable[] variables,IBreakpoint breakpoint, IStackFrame[] stacks) {
		//ILineBreakpoint lineBreakpoint = (ILineBreakpoint) breakpoint;
		//int mode = EventCenter.getInstance().getModeType();
		_debugTargets = DebugPlugin.getDefault().getLaunchManager().getDebugTargets();
		try{
		}
		catch(Exception ex){
			System.err.print("get breakpoint info error");
		}	

	}

	@Override
	public void init() {
		//set result 
		BreakpointManager.getInstance().disableAllBreakpoint();
		BreakpointManager.getInstance().setResult();
	}

	@Override
	public void switchMode() {
		// TODO Auto-generated method stub
		
	}
	
}
