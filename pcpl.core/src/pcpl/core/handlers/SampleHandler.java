package pcpl.core.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import pcpl.core.breakpoint.BreakpointManager;
import pcpl.core.eventHandler.EventCenter;
import pcpl.core.mode.TraceMode;
import pcpl.core.ui.pcplDialog;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	private pcplDialog _dialog;
	public SampleHandler() {
		initDialog();
	}

	private void initDialog() {
		_dialog = new pcplDialog(Display.getDefault().getActiveShell());
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Core",
				"Hello, Eclipse world");*/
		//_dialog.exec();
		if(EventCenter.getInstance().getModeType() == 0){		//normal mode
			BreakpointManager.getInstance().removeAllBreakpoint();
			BreakpointManager.getInstance().setAllBreakpoint();
			EventCenter.getInstance().setModeType(1);
			System.out.print("BreakPoint Setup \n");
			System.out.print("change Mode Type 1\n");
		}
		else if(EventCenter.getInstance().getModeType() == 1){		//normal mode
			EventCenter.getInstance().setModeType(2);
			System.out.print("change Mode Type 2\n");
		}
		else if(EventCenter.getInstance().getModeType() == 2){//record mode
			EventCenter.getInstance().removeBreakPointListener(EventCenter.getInstance().getRecMode());
			//EventCenter.getInstance().setMode(EventCenter.getInstance().getNorMode());
			EventCenter.getInstance().setModeType(3);
			System.out.print("change Mode Type 3\n");
		}
		else if(EventCenter.getInstance().getModeType() == 3){		//normal mode
			EventCenter.getInstance().setModeType(1);
			System.out.print("change Mode Type 1\n");
		}
		else{	
			System.err.print("mode error");
		}

		return null;
	}
	

}
