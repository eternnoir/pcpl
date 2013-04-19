package pcpl.core.eventHandler;

import org.eclipse.debug.core.DebugPlugin;

public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;

	public static EventCenter getInstance() {
		if (instance == null) {
			instance = new EventCenter();
		}
		return instance;
	}

	private EventCenter() {
		handler = new BasicEventHandler();
		DebugPlugin.getDefault().addDebugEventListener(handler);
	}

	public void addBreakPointListener(BreakPointListener listener) {
		handler.addBreakPointListener(listener);
	}

	public void addTargetTerminationListener(TargetTerminationListener listener) {
		handler.addTargetTerminationListener(listener);
	}

	public void addTargetCreationListener(TargetCreationListener listener) {
		handler.addTargetCreationListener(listener);
	}

	public void removeBreakPointListener(BreakPointListener listener) {
		handler.removeBreakPointListener(listener);
	}

	public void removeTargetCreationListener(TargetCreationListener listener) {
		handler.removeTargetCreationListener(listener);
	}

	public void removeTargetTerminationListener(
			TargetTerminationListener listener) {
		handler.removeTargetTerminationListener(listener);
	}



}
