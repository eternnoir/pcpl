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
package eplic.core.visualization;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;

import eplic.core.breakpoint.BreakpointManager;
import eplic.core.eventHandler.BreakPointListener;
import eplic.core.eventHandler.EventCenter;
/**
 * Visualizer Manager 負責管理所有Visualizer, 實作的Visualizer必須向他進行註冊
 * @author FrankWang
 *
 */
public class VisualizerManager implements BreakPointListener{
	private static VisualizerManager instance = null;
	 List<IVisualizer> _visualizerList = new ArrayList<IVisualizer>();
	public static VisualizerManager getInstance() {
		if (instance == null) {
			instance = new VisualizerManager();
		}
		return instance;
	}
	
	public VisualizerManager(){
		EventCenter.getInstance().addBreakPointListener(this);
	}
	/**
	 * add Visualizer
	 * @param vl
	 */
	public void addVisualizer(IVisualizer vl){
		_visualizerList.add(vl);
	}
	/**
	 * get Visualizer List
	 * @return
	 */
	public List<IVisualizer> getVisualizerList(){
		return _visualizerList;
	}
	/**
	 * get Normal Set. from breakpoint manager
	 * @see BreakpointManager
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getNorSet(){
		return BreakpointManager.getInstance().getNormalSet();
	}
	/**
	 * get Interested Set. from breakpoint manager
	 * @see BreakpointManager
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getInterestedSet(){
		return BreakpointManager.getInstance().getInterestedSet();
	}
	/**
	 * get Result Set. from breakpoint manager
	 * @see BreakpointManager
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getResult(){
		return BreakpointManager.getInstance().getResult();
	}
	/**
	 * get Resource By Breakpoint
	 * @param b
	 * @return
	 */
	public IResource getResourceByBreakpoint(IBreakpoint b){
		return BreakpointManager.getInstance().getResourceByBreakpoint(b);
	}

	@Override
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint, IStackFrame[] stacks) {
		for(IVisualizer _iv: _visualizerList){
			_iv.onBreakPointTriggered(variables, breakpoint, stacks);
		}
		
	}
}
