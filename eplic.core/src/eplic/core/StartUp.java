package eplic.core;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.ui.IStartup;

import eplic.core.launch.pcplLauncher;

public class StartUp implements IStartup {

	@Override
	public void earlyStartup() {
		DebugPlugin.getDefault().getLaunchManager()
		.addLaunchListener(pcplLauncher.getInstance());
	}

}
