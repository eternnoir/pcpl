package pcpl.core.eventHandler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugPlugin;

import pcpl.core.mode.AbstractMode;
import pcpl.core.mode.NormalMode;
import pcpl.core.mode.RecordMode;
import pcpl.core.mode.TraceMode;

public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;
	private int  _currentModeType;
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
		_currentModeType = 0;
		_normalMode = new NormalMode();
		_recordMode = new RecordMode();
		_traceMode = new TraceMode();
		_modeList = new ArrayList<AbstractMode>();
		_modeList.add(_normalMode);
		_modeList.add(_recordMode);
		_modeList.add(_traceMode);
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
		if(m == 1){
			this.setRecMode();
		}
		else if(m == 3){
			this.setTraMode();
		}
	}
	
	public int getModeType(){
		
		return _currentModeType;
	}
	public void setNorMode(){
		//this.addBreakPointListener(m);
		//this.addTargetTerminationListener(m);
	}
	public void setRecMode(){
		this.handler.clearAllBreakpointListener();
		this.handler.clearTargetTerminationListener();
		_recordMode.init();
		this.addBreakPointListener(_recordMode);
		this.addTargetTerminationListener(_recordMode);
	}
	public void setTraMode(){
		this.handler.clearAllBreakpointListener();
		this.handler.clearTargetTerminationListener();
		_traceMode.init();
		this.handler.clearAllBreakpointListener();
		this.addBreakPointListener(_traceMode);
		this.addTargetTerminationListener(_traceMode);
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


}
