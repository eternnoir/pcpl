package eplic.core.breakpoint;

import org.eclipse.core.resources.IResource;
/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * A breakpoint Setter's interface.
 * 
 * @author FrankWang
 *
 */
public interface IBreakpointSetter {
	public void setBreakpoint(IResource resource,int lineNum);
}
