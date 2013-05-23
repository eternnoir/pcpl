package pcpl.core.ui;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;
import pcpl.core.mode.*;


public class pcplDialog{
	private ListDialog dialog;
	private DIALOG_RESULT execResult;
	public pcplDialog(Shell parent) {
		execResult = DIALOG_RESULT.UNKNOWN;
		dialog = new ListDialog(parent);
		dialog.setContentProvider(new ArrayContentProvider());
		dialog.setTitle("Hello! CRUnit!");
		dialog.setMessage("Select a mode:");
		dialog.setHeightInChars(3);
		dialog.setWidthInChars(10);
	}
	public DIALOG_RESULT exec() {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				int result = dialog.open();
				switch (result) {
					case Window.OK:
						execResult = DIALOG_RESULT.OK;
						break;
					case Window.CANCEL:
						execResult = DIALOG_RESULT.CANCEL;
						break;
					default:
						execResult = DIALOG_RESULT.UNKNOWN;
				}
				System.out.println(execResult);
			}
		});
		return execResult;
	}
	
	public void run(){
		int result = dialog.open();
		switch (result) {
			case Window.OK:
				execResult = DIALOG_RESULT.OK;
				break;
			case Window.CANCEL:
				execResult = DIALOG_RESULT.CANCEL;
				break;
			default:
				execResult = DIALOG_RESULT.UNKNOWN;
		}
		System.out.println(execResult);
	}

	public void setInput(List<AbstractMode> input) {
		dialog.setInput(input);
		dialog.setInitialSelections(new Object[] { input.get(0) });
	}
}