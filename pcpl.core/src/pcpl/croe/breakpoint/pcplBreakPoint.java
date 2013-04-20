package pcpl.croe.breakpoint;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.IBreakpoint;

public class pcplBreakPoint implements IBreakpoint {

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IMarker getMarker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMarker(IMarker marker) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getModelIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnabled(boolean enabled) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isRegistered() throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setRegistered(boolean registered) throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPersisted() throws CoreException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPersisted(boolean registered) throws CoreException {
		// TODO Auto-generated method stub

	}

}
