package eplic.core.eventHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;


public abstract class AbstractEventHandler implements IDebugEventSetListener {

	protected List<BreakPointListener> bpListeners;
	protected List<TargetCreationListener> creationListeners;
	protected List<TargetTerminationListener> terminationListeners;

	public AbstractEventHandler() {
		bpListeners = Collections
				.synchronizedList(new ArrayList<BreakPointListener>());
		terminationListeners = Collections
				.synchronizedList(new ArrayList<TargetTerminationListener>());
		creationListeners = Collections
				.synchronizedList(new ArrayList<TargetCreationListener>());

	}

	public void addBreakPointListener(BreakPointListener listener) {
		this.bpListeners.add(listener);
	}

	public void addTargetCreationListener(TargetCreationListener l) {
		this.creationListeners.add(l);
	}

	public void addTargetTerminationListener(TargetTerminationListener l) {
		this.terminationListeners.add(l);
	}
	public void clearAllBreakpointListener(){
		this.bpListeners.clear();
	}
	public void clearTargetTerminationListener(){
		this.terminationListeners.clear();
	}

	@Override
	public void handleDebugEvents(DebugEvent[] events) {
		// See the JavaDoc of DebugEvent to understand the logic here.
		for (DebugEvent event : events) {
			Object source = event.getSource();
			if (source instanceof IDebugElement) {
				IDebugElement debugElement = (IDebugElement) source;
				int eventKind = event.getKind();
				switch (eventKind) {
				// TODO handle resume event.
					case DebugEvent.SUSPEND:
						if (event.getDetail() == DebugEvent.BREAKPOINT) {
							handleBreakpointEvent(debugElement);
						}
						break;
					case DebugEvent.TERMINATE:
						if (source instanceof IDebugTarget) {
							handleTargetTerminateEvent(debugElement);
						}
						break;
					case DebugEvent.CREATE:
						if (source instanceof IDebugTarget) {
							handleTargetCreatedEvent(debugElement);
						}
						break;
				}
			}
		}
	}

	public void removeBreakPointListener(BreakPointListener listener) {
		this.bpListeners.remove(listener);
	}

	public void removeTargetCreationListener(TargetCreationListener l) {
		this.creationListeners.remove(l);
	}

	public void removeTargetTerminationListener(TargetTerminationListener l) {
		this.terminationListeners.remove(l);
	}



	protected abstract boolean handleBreakpointEvent(IDebugElement dElement) ;

	protected abstract boolean handleTargetCreatedEvent(IDebugElement dElement);

	protected abstract boolean handleTargetTerminateEvent(IDebugElement dElement);
}
