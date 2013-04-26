package pcpl.core.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import pcpl.core.eventHandler.EventCenter;
import pcpl.core.mode.TraceMode;
import pcpl.croe.breakpoint.FileParaviserUtils;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
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
		FileParaviserUtils.getAllFile("java");
		if(EventCenter.getInstance().getModeType() == 1){		//normal mode
			EventCenter.getInstance().setModeType(2);
			System.out.print("change Mode Type 2\n");
		}
		else if(EventCenter.getInstance().getModeType() == 2){//record mode
			EventCenter.getInstance().removeBreakPointListener(EventCenter.getInstance().getRecMode());
			//EventCenter.getInstance().setMode(EventCenter.getInstance().getNorMode());
			TraceMode t = new TraceMode();
			EventCenter.getInstance().setTraMode(t);
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
