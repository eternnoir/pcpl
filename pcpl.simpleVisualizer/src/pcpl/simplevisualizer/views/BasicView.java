package pcpl.simplevisualizer.views;

import java.awt.Frame;
import java.util.ArrayList;

import pcpl.core.breakpoint.BreakpointManager;
import pcpl.core.breakpoint.FileParaviserUtils;
import pcpl.core.visualization.*;
import pcpl.core.eventHandler.*;
import org.jgraph.*;
import org.jgraph.graph.DefaultEdge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.jdt.internal.debug.core.model.JDIStackFrame;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.*;

public class BasicView extends ViewPart implements IVisualizer
						, BreakPointListener{
	private JGraphModelAdapter m_jgAdapter;
	private ListenableGraph g;
	private JGraph graph;
	private IStackFrame[] _stacks;
	private ArrayList<Object> _graphObjectList;
	private IBreakpoint _susBP;
	private int _nodeNumber;
	private String _name = null;
	private String _id = null;
	Composite composite;
	Frame frame;
	public BasicView(){
		super();
		_name = "jGraph";
		_id = "pcpl.simpleVisualizer.BasicView";
		_nodeNumber = 5;
		_graphObjectList = new ArrayList<Object>();
		EventCenter.getInstance().addBreakPointListener(this);
	}
	@Override
	public void createPartControl(Composite parent) {
		composite= new Composite( parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		frame = SWT_AWT.new_Frame(composite);
		g = new ListenableDirectedGraph( DefaultEdge.class );
		m_jgAdapter = new JGraphModelAdapter( g );
		graph = new JGraph(m_jgAdapter);
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
	@Override
	public void onBreakPointTriggered(IVariable[] variables,
			IBreakpoint breakpoint,IStackFrame[] stacks) {
		_stacks = stacks;
		_susBP = breakpoint;
		if(EventCenter.getInstance().getModeType() == 3){
			this.update();
		}
	}
	
	private void update(){
		this.clearGraph();
		this.setPreStackFrame(_nodeNumber);
		this.setPosStackFrame(_nodeNumber);
	}
	private void init(){

	}
	
	private void clearGraph(){
		g.removeAllVertices(_graphObjectList);
	}
	private void setPreStackFrame(int num){
		try{
			// set before stackframe
			for(int i =0;i<_stacks.length&&i<num;i++){
				JDIStackFrame j = (JDIStackFrame)_stacks[i];
				String s = j.getReceivingTypeName();
				_graphObjectList.add(s);
				g.addVertex(s);
			}
		}
		catch (DebugException e) {
			e.printStackTrace();
		}
	}
	
	private void setPosStackFrame(int num){
		ArrayList<ILineBreakpoint> _bpsmN = BreakpointManager.getInstance().getNormalSet();
		ArrayList<ILineBreakpoint> _bpsmR = BreakpointManager.getInstance().getNormalSet();
		int index = _bpsmN.indexOf(_susBP);
		assert(index > 0);	//can not find breakpoint in nor set ,it is wired
		for(int i = index ;i<index+num && i<_bpsmN.size();i++){
			String _strClassName =FileParaviserUtils.getClassName( 
			BreakpointManager.getInstance().getResourceByBreakpoint(_bpsmN.get(i)));
			String[] _strClassNameList = FileParaviserUtils.splitClassName(_strClassName); 
			_graphObjectList.add(_strClassNameList[_strClassNameList.length-1]);
			g.addVertex(_strClassNameList[_strClassNameList.length-1]);
		}
	}
}
