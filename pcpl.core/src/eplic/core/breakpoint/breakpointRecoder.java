package eplic.core.breakpoint;

import java.util.ArrayList;

import org.eclipse.debug.core.model.ILineBreakpoint;
/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * A breakpoint recorder. record breakpoints
 * 
 * @author FrankWang
 *
 */
public class breakpointRecoder {
	private ArrayList<ILineBreakpoint> _bps;
	/**
	 * constructor
	 */
	public breakpointRecoder(){
		_bps = new ArrayList<ILineBreakpoint>();
	}
	/**
	 * record a breakpoint
	 * @param lineBreakpoint
	 */
	public void addBreakPoint(ILineBreakpoint lineBreakpoint){
		_bps.add(lineBreakpoint);
	
	}
	/**
	 * get result
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getBPS(){
		return	_bps;
	}
}
