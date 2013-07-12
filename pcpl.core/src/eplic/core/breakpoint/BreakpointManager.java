package eplic.core.breakpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;

import eplic.core.eventHandler.EventCenter;

/**
 * EPLIC - A Tool to Assist Locating Interested Code.
 * Copyright (C) 2013 Frank Wang <eternnoir@gmail.com>
 * 
 * This file is part of EPLIC.
 * 
 * 這個Class 負責管理所有跟breakpoint有關的操作，像是新增breakpoint
 * 禁用所有breakpoint等
 * 
 * @author FrankWang
 *
 */
public class BreakpointManager {
	private static BreakpointManager instance = null;
	private ArrayList<ILineBreakpoint> _result;
	private Map<IBreakpoint,IResource> _breakpointMap;
	public static BreakpointManager getInstance() {
		if (instance == null) {
			instance = new BreakpointManager();
		}
		return instance;
	}
	
	public BreakpointManager(){
		_result = null;
		_breakpointMap = new HashMap<IBreakpoint,IResource>();
	}
	/**
	 * 比較兩個breakpoint set, 第一個代入的是normal set，第二個為IS
	 * 會將IS中含有NS的breakpoint給去掉
	 * 
	 * @param nor
	 * @param rec
	 * @return result set
	 */
	public ArrayList<ILineBreakpoint> diffResult(ArrayList<ILineBreakpoint> nor,ArrayList<ILineBreakpoint> rec){
		for(ILineBreakpoint nm : nor){
			while(rec.indexOf(nm)!= -1){
				rec.remove(nm);
			}
		}
		_result = new ArrayList<ILineBreakpoint>(rec.size());
		for(ILineBreakpoint b : rec){
			_result.add(b);
		}
		return _result;
	}
	/**
	 * get result set
	 * @return
	 */
	
	public ArrayList<ILineBreakpoint> getResult(){
		assert(_result != null);
		return _result;
	}
	/**
	 * disable all breakpoint in project
	 */
	public void disableAllBreakpoint(){
		IBreakpoint[] b = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints();
		for(IBreakpoint _b : b){
			try {
				_b.setEnabled(false);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * remove all breakpoint 
	 */
	public void removeAllBreakpoint(){
		IBreakpoint[] b = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints();
		try {
			DebugPlugin.getDefault().getBreakpointManager().removeBreakpoints(b, true);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * set result set's breakpoint
	 */
	public void setResult(){
		for(ILineBreakpoint b : _result){
			try {
				b.setEnabled(true);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 將所有函式開頭都插入中斷點
	 */
	public void setAllBreakpoint(){
		// for now, just support java
		ArrayList<IResource> resourceList =  FileParaviserUtils.getAllFilesInProject("java");
		for(IResource r : resourceList){
			setBreakpointByResource(r);
		}
	}
	/**
	 * 將傳入的iresource所有函式的開頭插入中斷點
	 * 
	 * @param r
	 */
	public void setBreakpointByResource(IResource r){
		IFile f = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(r.getLocation());
	    String strings;
	    String[] lines = null;
	    // get single line,to check need to set breakpoint
	    try {
			InputStream s = f.getContents();
			strings = IOUtils.toString(s,"UTF-8");
			//lines = strings.split(System.getProperty("line.separator"));
			lines = strings.split("\n");
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	    ArrayList<Integer> lineNumbers = checkFunctionNameLineNumber(lines);
	    for(int i : lineNumbers){
	    	BreakpointSetter.getInstance().setBreakpoint(r, i);
	    }
	    
	}
	/**
	 * create a map between breakpoint and resource
	 * 純粹只是為了方便從breakpoint找到他在哪個檔案
	 * 
	 * @param b
	 * @param r
	 */
	public void addBreakpointSet(IBreakpoint b, IResource r){
		_breakpointMap.put(b, r);
	}
	/**
	 * get iresource by breakpoint
	 * @param b
	 * @return
	 */
	public IResource getResourceByBreakpoint(IBreakpoint b){
		IResource ret = null;
		ret = _breakpointMap.get(b);
		return ret;
	}
	/**
	 * get Normal set, it will ask eventcenter for normal set
	 * 
	 * @see EventCenter
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getNormalSet(){
		return EventCenter.getInstance().getNorMode().getBreakPointRecorder().getBPS();
	}
	/**
	 * get Interested  set, it will ask eventcenter for Interested  set
	 * 
	 * @see EventCenter
	 * @return
	 */
	public ArrayList<ILineBreakpoint> getInterestedSet(){
		return EventCenter.getInstance().getRecMode().getBreakPointRecorder().getBPS();
	}
	/**
	 * reset, it will remove all breakpoints which are ECPLIC set.
	 */
	public void reset(){
		this.removeAllBreakpoint();
	}
	/**
	 * 確認傳入的字串是不是一個函式
	 * 
	 * @param line
	 * @return
	 */
	
	private ArrayList<Integer> checkFunctionNameLineNumber(String[] line){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i = 0;i < line.length;i++){
			if(checkFunction(line[i])){	//判斷function
				ret.add(i+1);
			}
		}
		
		return ret;
	}
	/**
	 * 確認傳入的字串是不是一個函式
	 * @param s
	 * @return
	 */
	
	private boolean checkFunction(String s){
		if((s.indexOf("public")>-1)||(s.indexOf("private")>-1)||(s.indexOf("protected")>-1)){	//判斷function
			if((s.indexOf("(")>-1) && (s.indexOf("abstract")<0)){
				return true;
			}
		}
		return false;
	}	

}
