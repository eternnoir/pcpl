/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eplic.core.ui;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListDialog;

import eplic.core.mode.*;

/**
 * 
 * @author FrankWang
 *
 */
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