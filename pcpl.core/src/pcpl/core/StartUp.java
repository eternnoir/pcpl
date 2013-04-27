package pcpl.core;

import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.ui.IStartup;

import pcpl.core.breakpoint.BreakpointManager;
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
		AbstractMode b = new NormalMode();
		EventCenter.getInstance().setModeType(1);
		EventCenter.getInstance().setNorMode(b);
		AbstractMode re = new RecordMode();
		EventCenter.getInstance().setRecMode(re);
		

	}

}
