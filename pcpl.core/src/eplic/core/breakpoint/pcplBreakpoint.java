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


import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jdt.internal.debug.core.breakpoints.JavaLineBreakpoint;

/**
 * 
 * Not use now
 * 
 * @author FrankWang
 *
 */

public class pcplBreakpoint extends JavaLineBreakpoint {

	public pcplBreakpoint(IResource resource) throws DebugException, CoreException {
	    IMarker marker;

			marker = resource.createMarker(
			         "org.eclipse.debug.core.breakpointMarker");
	        setMarker(marker);
	        setEnabled(true);
			ensureMarker().setAttribute(IMarker.LINE_NUMBER, 12);
			ensureMarker().setAttribute(IBreakpoint.ID, "Debug");
			Map<String,Object> m = new HashMap<String,Object>();
			/*
			 * IBreakpoint.ENABLED
				IMarker.LINE_NUMBER
				IMarker.CHAR_START
				IMarker.CHAR_END
			 * 
			 */
			m.put(IBreakpoint.ID, 1);
			m.put(IMarker.LINE_NUMBER, 5);
			m.put(IMarker.CHAR_START, 0);
			m.put(IMarker.CHAR_END, 10);
			
			this.addLineBreakpointAttributes(m, "Debug", true, 5, 0, 10);
	}
}
