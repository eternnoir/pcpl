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
package eplic.core.breakpoint;

import java.util.ArrayList;

import org.eclipse.debug.core.model.ILineBreakpoint;
/**
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
