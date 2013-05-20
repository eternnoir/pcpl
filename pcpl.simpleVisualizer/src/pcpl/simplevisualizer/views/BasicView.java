package pcpl.simplevisualizer.views;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.*;

public class BasicView extends ViewPart {
	private Label label;
	public BasicView(){
		super();
		Composite composite = new Composite( null, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		Frame frame = SWT_AWT.new_Frame(composite);
	}
	@Override
	public void createPartControl(Composite parent) {
        label = new Label(parent, 0);
        label.setText("Hello World");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
