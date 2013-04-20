package pcpl.core;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.ui.IStartup;

import pcpl.core.eventHandler.BreakPointListener;
import pcpl.core.eventHandler.EventCenter;
import pcpl.core.launch.pcplLauncher;
import pcpl.core.mode.AbstractMode;
import pcpl.core.mode.NormalMode;

public class StartUp implements IStartup {

	@Override
	public void earlyStartup() {
		DebugPlugin.getDefault().getLaunchManager()
		.addLaunchListener(pcplLauncher.getInstance());
		AbstractMode b = new NormalMode();
		EventCenter.getInstance().addBreakPointListener(b);
		EventCenter.getInstance().setMode(b);
		EventCenter.getInstance().addTargetTerminationListener(b);

	}

}
