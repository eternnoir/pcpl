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
package eplic.simplevisualizer.views;


import java.awt.Frame;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import eplic.core.visualization.*;
import eplic.core.eventHandler.*;

import org.jgraph.*;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.Breakpoint;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.internal.core.BreakpointManager;
import org.eclipse.jdt.internal.debug.core.model.JDIStackFrame;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
/**
 * 一個基本的用JGraphT實作的Call Graph
 * 
 * @author frankwang
 *
 */
public class BasicView extends ViewPart implements IVisualizer{
	private JGraphModelAdapter m_jgAdapter;
	private ListenableGraph g;
	private JGraph graph;
	private IStackFrame[] _stacks;
	private ArrayList<Object> _graphObjectList;
	private ILineBreakpoint _susBP;
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
		VisualizerManager.getInstance().addVisualizer(this);
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
		_susBP = (ILineBreakpoint) breakpoint;
		if(EventCenter.getInstance().getModeType() == 3){
			this.update();
		}
	}
	/**
	 * 更新call graph
	 */
	private void update(){
		this.clearGraph();
		this.setPreStackFrame(_nodeNumber);
		this.setPosStackFrame(_nodeNumber);
		this.arrange();
	}
	private void init(){

	}
	/**
	 * 清除call graph
	 */
	private void clearGraph(){
		g.removeAllVertices(_graphObjectList);
		graph.removeAll();
		_graphObjectList = new ArrayList<Object>();
	}
	/**
	 * 設定所有在目前狀態下的Call Stack
	 * @param num
	 */
	private void setPreStackFrame(int num){
		try{
			// set before stackframe
			for(int i =0;i<_stacks.length&&i<num;i++){
				JDIStackFrame j = (JDIStackFrame)_stacks[i];
				String s = j.getReceivingTypeName();
				String[] _strClassNameList = FileParaviserUtils.splitClassName(s); 
				//_graphObjectList.add(_strClassNameList[_strClassNameList.length-1]);
				_graphObjectList.add(0, _strClassNameList[_strClassNameList.length-1]);
				g.addVertex(_strClassNameList[_strClassNameList.length-1]);
			}
		}
		catch (DebugException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 設定未來會呼叫哪些函式的call graph
	 * @param num
	 */
	private void setPosStackFrame(int num){
		ArrayList<ILineBreakpoint> _bpsmR = VisualizerManager.getInstance().getResult();
		int index = _bpsmR.indexOf((ILineBreakpoint)_susBP);
		for(int i = index ;i<index+num && i<_bpsmR.size();i++){
			String _strClassName =FileParaviserUtils.getClassName( 
					VisualizerManager.getInstance().getResourceByBreakpoint(_bpsmR.get(i)));
			String[] _strClassNameList = FileParaviserUtils.splitClassName(_strClassName); 
			if(!g.containsVertex(_strClassNameList[_strClassNameList.length-1])){
				_graphObjectList.add(_strClassNameList[_strClassNameList.length-1]);
				g.addVertex(_strClassNameList[_strClassNameList.length-1]);
			}
		}
	}
	/**
	 * 設定每個node的位置
	 * @param vertex
	 * @param x
	 * @param y
	 */
	private void positionVertexAt( Object vertex, int x, int y ) {
		DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds = new Rectangle2D.Double(x,y,bounds.getWidth()+20,bounds.getHeight()+20);

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        org.jgraph.graph.AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
	}
	/**
	 * 排位置
	 */
	private void arrange(){
		int x = 200;
		for(int i=0;i<_graphObjectList.size();i++){
			positionVertexAt(_graphObjectList.get(i),x*i,50);
		}
		for(int i=1;i<_graphObjectList.size();i++){
			g.addEdge(_graphObjectList.get(i-1),_graphObjectList.get(i));
		}
	}
}
