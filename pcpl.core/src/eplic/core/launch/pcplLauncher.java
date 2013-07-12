package eplic.core.launch;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.internal.core.LaunchManager;
import org.eclipse.swt.widgets.Display;

import eplic.core.eventHandler.EventCenter;
import eplic.core.ui.pcplDialog;


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
