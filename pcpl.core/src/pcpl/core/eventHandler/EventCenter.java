package pcpl.core.eventHandler;

import org.eclipse.debug.core.DebugPlugin;

import pcpl.core.mode.AbstractMode;

public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;
	private int  _currentModeType;
	private AbstractMode  _normalMode;
	private AbstractMode  _recordMode;

	public static EventCenter getInstance() {
		if (instance == null) {
			instance = new EventCenter();
		}
		return instance;
	}

	private EventCenter() {
		handler = new BasicEventHandler();
		DebugPlugin.getDefault().addDebugEventListener(handler);
		_currentModeType = 0;
		_normalMode = null;
		_recordMode = null;
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
	
	public void setModeType(int m){
		_currentModeType = m;
	}
	
	public int getModeType(){
		
		return _currentModeType;
	}
	public void setNorMode(AbstractMode m){
		_normalMode = m;
		//this.addBreakPointListener(m);
		//this.addTargetTerminationListener(m);
	}
	public void setRecMode(AbstractMode m){
		_recordMode = m;
		this.addBreakPointListener(m);
		this.addTargetTerminationListener(m);
	}
	public void setTraMode(AbstractMode m){
		_recordMode = m;
		this.addBreakPointListener(m);
		this.addTargetTerminationListener(m);
	}
	
	
	public AbstractMode getNorMode(){
		return _normalMode;
	}
	public AbstractMode getRecMode(){
		return _recordMode;
	}



}
