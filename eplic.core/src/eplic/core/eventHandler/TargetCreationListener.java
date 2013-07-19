package eplic.core.eventHandler;

import org.eclipse.debug.core.model.IDebugTarget;

public interface TargetCreationListener {
	void onTargetCreated(IDebugTarget target);
}
