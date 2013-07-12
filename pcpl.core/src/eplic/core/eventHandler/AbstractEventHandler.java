package eplic.core.eventHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.IDebugEventSetListener;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * this class define events about eclipse,like breakpoint TargetTerminate TargetCreated
 * 
 * 
 * @see BasicEventHandler
 * @author frankwang(eternnoir)
 *
 */

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
	/**
	 * add breakpoint listener to bpListeners
	 * @param listener
	 */
	public void addBreakPointListener(BreakPointListener listener) {
		this.bpListeners.add(listener);
	}
	/**
	 * add TargetCreationListener
	 * @param l
	 */
	public void addTargetCreationListener(TargetCreationListener l) {
		this.creationListeners.add(l);
	}
	/**
	 * add TerminationListener
	 * @param l
	 */
	public void addTargetTerminationListener(TargetTerminationListener l) {
		this.terminationListeners.add(l);
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
	/**
	 * remove BreakPointListener from event handler
	 * @param listener
	 */
	public void removeBreakPointListener(BreakPointListener listener) {
		this.bpListeners.remove(listener);
	}
	/**
	 * remove TargetCreationListener form event handler
	 * @param l
	 */
	public void removeTargetCreationListener(TargetCreationListener l) {
		this.creationListeners.remove(l);
	}
	/**
	 * remove TargetTerminationListener from event handler
	 * @param l
	 */
	public void removeTargetTerminationListener(TargetTerminationListener l) {
		this.terminationListeners.remove(l);
	}
	

	public void removeAllBreakPointListener() {
		this.bpListeners.clear();
	}

	public void removeAllTargetCreationListener() {
		this.creationListeners.clear();
	}

	public void removeAllTargetTerminationListener() {
		this.terminationListeners.clear();
	}
	



	protected abstract boolean handleBreakpointEvent(IDebugElement dElement) ;

	protected abstract boolean handleTargetCreatedEvent(IDebugElement dElement);

	protected abstract boolean handleTargetTerminateEvent(IDebugElement dElement);
}
