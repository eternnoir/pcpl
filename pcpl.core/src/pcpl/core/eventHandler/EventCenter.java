package pcpl.core.eventHandler;

import org.eclipse.debug.core.DebugPlugin;

import pcpl.core.mode.AbstractMode;

public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;
	private AbstractMode  _currentMode;

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
	
	public void setMode(AbstractMode m){
		_currentMode = m;
	}
	
	public AbstractMode getMode(){
		assert (_currentMode != null);
		return _currentMode;
	}



}
