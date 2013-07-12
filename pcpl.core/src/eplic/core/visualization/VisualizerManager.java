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
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * 
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
	public void addVisualizer(IVisualizer vl){
		_visualizerList.add(vl);
	}
	public List<IVisualizer> getVisualizerList(){
		return _visualizerList;
	}
	
	public ArrayList<ILineBreakpoint> getNorSet(){
		return BreakpointManager.getInstance().getNormalSet();
	}
	
	public ArrayList<ILineBreakpoint> getInterestedSet(){
		return BreakpointManager.getInstance().getInterestedSet();
	}
	public ArrayList<ILineBreakpoint> getResult(){
		return BreakpointManager.getInstance().getResult();
	}
	
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
