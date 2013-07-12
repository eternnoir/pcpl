package eplic.core.eventHandler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugPlugin;

import eplic.core.mode.AbstractMode;
import eplic.core.mode.NormalMode;
import eplic.core.mode.RecordMode;
import eplic.core.mode.TraceMode;

public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;
	private AbstractMode  _currentMode;
	private AbstractMode  _normalMode;
	private AbstractMode  _recordMode;
	private AbstractMode  _traceMode;
	private List<AbstractMode> _modeList;
	
	public static EventCenter getInstance() {
		if (instance == null) {
			instance = new EventCenter();
		}
		return instance;
	}

	private EventCenter() {
		handler = new BasicEventHandler();
		DebugPlugin.getDefault().addDebugEventListener(handler);
		_modeList = new ArrayList<AbstractMode>();
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


	public void removeAllBreakPointListener() {
		handler.removeAllBreakPointListener();
	}

	public void removeAllTargetCreationListener() {
		handler.removeAllTargetCreationListener();
	}

	public void removeAllTargetTerminationListener() {
		handler.removeAllTargetTerminationListener();
	}
	
	
	
	public void setNorMode(AbstractMode m){
		_normalMode = m;
		this.addBreakPointListener(_normalMode);
		_currentMode = _normalMode;
	}
	public void setRecMode(AbstractMode m){
		_recordMode = m;
		this.removeAllBreakPointListener();
		this.addBreakPointListener(_recordMode);
		this.addTargetTerminationListener(_recordMode);
		_currentMode = _recordMode;
	}
	public void setTraMode(AbstractMode m){
		_traceMode = m;
		this.removeAllBreakPointListener();
		this.removeAllTargetTerminationListener();
		_traceMode.init();
		this.addBreakPointListener(_traceMode);
		this.addTargetTerminationListener(_traceMode);
		_currentMode = _traceMode;
	}
	public AbstractMode getNorMode(){
		return _normalMode;
	}
	public AbstractMode getRecMode(){
		return _recordMode;
	}
	public List<AbstractMode> getModeList(){
		assert(_modeList != null);
		return _modeList;
	}

	public int getModeType() {
		if(_currentMode == null)
			return 0;
		else
			return _currentMode.getMode();
	}
	public void reset(){
		instance = new EventCenter();
	}

}
