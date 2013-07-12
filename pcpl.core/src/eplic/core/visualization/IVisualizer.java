package eplic.core.visualization;

import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
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
public interface IVisualizer {
	public String getVisualizerName();
	public String getVisualizerID();	
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint,IStackFrame[] stacks);
}
