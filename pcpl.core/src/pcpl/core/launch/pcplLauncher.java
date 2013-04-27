package pcpl.core.launch;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.internal.core.LaunchManager;

import pcpl.core.breakpoint.BreakpointManager;




@SuppressWarnings("restriction")
public class pcplLauncher extends pcplLaunchListener {
	
	
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
				BreakpointManager.getInstance().removeAllBreakpoint();
				BreakpointManager.getInstance().setAllBreakpoint();
				} else {
					System.err.println("Not in Debug Mode");
				}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}
