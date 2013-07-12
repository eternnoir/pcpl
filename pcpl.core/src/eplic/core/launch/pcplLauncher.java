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
package eplic.core.launch;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.internal.core.LaunchManager;
import org.eclipse.swt.widgets.Display;

import eplic.core.eventHandler.EventCenter;
import eplic.core.ui.pcplDialog;


/**
 * 
 * @author FrankWang
 *
 */


public class pcplLauncher extends pcplLaunchListener {
	private pcplDialog _dialog;
	
	private static pcplLauncher instance = null;
	public static pcplLauncher getInstance() {
		if (instance == null) {
			instance = new pcplLauncher();
		}
		return instance;
	}
	
	private pcplLauncher(){
	}
	public void launchAdded(ILaunch launch) {
		try {
			String launchMode = launch.getLaunchMode();
			if (launchMode.equals(LaunchManager.DEBUG_MODE)) {
				} else {
					System.err.println("Not in Debug Mode");
				}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}
