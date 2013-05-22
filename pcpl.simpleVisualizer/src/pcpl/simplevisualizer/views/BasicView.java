package pcpl.simplevisualizer.views;

import java.awt.Frame;
import pcpl.core.visualization.*;
import org.jgraph.*;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.*;

public class BasicView extends ViewPart implements IVisualizer{
	private JGraphModelAdapter m_jgAdapter;
	private String _name = null;
	private String _id = null;
	Composite composite;
	Frame frame;
	public BasicView(){
		super();
		_name = "jGraph";
		_id = "pcpl.simpleVisualizer.BasicView";
	}
	@Override
	public void createPartControl(Composite parent) {
		composite= new Composite( parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		frame = SWT_AWT.new_Frame(composite);
		ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );
		m_jgAdapter = new JGraphModelAdapter( g );
		JGraph graph = new JGraph(m_jgAdapter);
		g.addVertex( "v1" );
	    g.addVertex( "v2" );
	    g.addVertex( "v3" );
	    g.addVertex( "v4" );

	    g.addEdge( "v1", "v2" );
	    g.addEdge( "v2", "v3" );
	    g.addEdge( "v3", "v1" );
	    g.addEdge( "v4", "v3" );
		frame.add(graph);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private void createExecPath(ListenableDirectedGraph g){
		
	}
		
	@Override
	public String getVisualizerName() {
		return _name;
	}
	@Override
	public String getVisualizerID() {
		// TODO Auto-generated method stub
		return _id;
	}
}
