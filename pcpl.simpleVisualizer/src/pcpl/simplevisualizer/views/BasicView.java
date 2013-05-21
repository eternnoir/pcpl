package pcpl.simplevisualizer.views;

import java.awt.Frame;

import org.jgraph.JGraph;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.*;

public class BasicView extends ViewPart {
	private Label label;
	Composite composite;
	Frame frame;
	public BasicView(){
		super();
	}
	@Override
	public void createPartControl(Composite parent) {
        label = new Label(parent, 0);
        label.setText("Hello World");
		composite= new Composite( parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		frame = SWT_AWT.new_Frame(composite);
        JGraph graph = new JGraph();
        frame.add(graph);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
