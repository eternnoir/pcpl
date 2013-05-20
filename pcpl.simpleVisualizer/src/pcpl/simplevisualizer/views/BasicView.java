package pcpl.simplevisualizer.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.*;

public class BasicView extends ViewPart {
	private Label label;
	public BasicView(){
		super();
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
