package pcpl.core;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.ui.IStartup;

import pcpl.core.eventHandler.EventCenter;
import pcpl.core.launch.pcplLauncher;
import pcpl.core.mode.AbstractMode;
import pcpl.core.mode.NormalMode;
import pcpl.core.mode.RecordMode;

public class StartUp implements IStartup {

	@Override
	public void earlyStartup() {
		DebugPlugin.getDefault().getLaunchManager()
		.addLaunchListener(pcplLauncher.getInstance());
	}

}
