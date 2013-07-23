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
package eplic.core.eventHandler;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.DebugPlugin;

import eplic.core.breakpoint.BreakpointManager;
import eplic.core.mode.AbstractMode;
import eplic.core.mode.NormalMode;
import eplic.core.visualization.VisualizerManager;
/**
 * 
 * EventCenter定義了所有EPLIC會用到的Event
 * 擁有一個BasicEventHandler
 * 
 * 在EPLIC的所有Event都會透過這個Class,同時也提供Listener的註冊
 * 或是解除註冊
 * 
 * 最主要的三個Event為Breakpoint被hint到時的event
 * target被建立時,以及target結束時
 * 
 * @see BasicEventHandler
 * @author FrankWang
 *
 */
public class EventCenter {
	private static EventCenter instance = null;
	private AbstractEventHandler handler;
	private AbstractMode  _currentMode;
	private AbstractMode  _normalMode;
	private AbstractMode  _interestedMode;
	private AbstractMode  _traceMode;
	private boolean _isAna;
	private List<AbstractMode> _modeList;
	
	public static EventCenter getInstance() {
		if (instance == null) {
			instance = new EventCenter();
		}
		return instance;
	}
	/**
	 * constructor
	 */
	private EventCenter() {
		handler = new BasicEventHandler();
		DebugPlugin.getDefault().addDebugEventListener(handler);
		_modeList = new ArrayList<AbstractMode>();
	}
	/**
	 * add BreakPointListener
	 * @param listener
	 */
	public void addBreakPointListener(BreakPointListener listener) {
		handler.addBreakPointListener(listener);
	}
	/**
	 * add TargetTerminationListener
	 * @param listener
	 */
	public void addTargetTerminationListener(TargetTerminationListener listener) {
		handler.addTargetTerminationListener(listener);
	}
	/**
	 * add TargetCreationListener
	 * @param listener
	 */
	public void addTargetCreationListener(TargetCreationListener listener) {
		handler.addTargetCreationListener(listener);
	}
	/**
	 * remove BreakPointListener
	 * @param listener
	 */
	public void removeBreakPointListener(BreakPointListener listener) {
		handler.removeBreakPointListener(listener);
	}
	/**
	 * remove TargetCreationListener
	 * @param listener
	 */
	public void removeTargetCreationListener(TargetCreationListener listener) {
		handler.removeTargetCreationListener(listener);
	}
	/**
	 * remove TargetTerminationListene
	 * @param listener
	 */
	public void removeTargetTerminationListener(
			TargetTerminationListener listener) {
		handler.removeTargetTerminationListener(listener);
	}

	/**
	 * remove All BreakPointListene
	 */
	public void removeAllBreakPointListener() {
		handler.removeAllBreakPointListener();
		handler.addBreakPointListener(VisualizerManager.getInstance());
	}
	/**
	 * remove All TargetCreationListener
	 */
	public void removeAllTargetCreationListener() {
		handler.removeAllTargetCreationListener();
	}
	/**
	 * remove All TargetTerminationListener
	 */
	public void removeAllTargetTerminationListener() {
		handler.removeAllTargetTerminationListener();
	}
	/**
	 * Set normal mode into event center
	 * @param m
	 */
	public void setNorMode(AbstractMode m){
		_normalMode = m;
		this.addBreakPointListener(_normalMode);
		_currentMode = _normalMode;
	}
	/**
	 * set interested mode into event center.
	 * it will remove normal mode's breakpoint listener.
	 * @param m
	 */
	public void setIntMode(AbstractMode m){
		_interestedMode = m;
		this.removeAllBreakPointListener();
		this.addBreakPointListener(_interestedMode);
		this.addTargetTerminationListener(_interestedMode);
		_currentMode = _interestedMode;
	}
	/**
	 * set trace mode into event center
	 * it will remove other breakpoint & termination listener.
	 * @param m
	 */
	public void setTraMode(AbstractMode m){
		_traceMode = m;
		this.removeAllBreakPointListener();
		this.removeAllTargetTerminationListener();
		_traceMode.init();
		_currentMode = _traceMode;
		this.addBreakPointListener(_traceMode);
		this.addTargetTerminationListener(_traceMode);
	}
	/**
	 * get current mode
	 * @param m
	 */
	public void setCurrentMode(AbstractMode m){
		_currentMode = m;
	}
	public AbstractMode getNorMode(){
		return _normalMode;
	}
	public AbstractMode getInsMode(){
		return _interestedMode;
	}
	public List<AbstractMode> getModeList(){
		assert(_modeList != null);
		return _modeList;
	}
	/**
	 * Switch mode
	 * @return
	 */
	public String switchMode(){
		if(_currentMode == null){
			BreakpointManager.getInstance().removeAllBreakpoint();
			BreakpointManager.getInstance().setAllBreakpoint();
			this.setNorMode(new NormalMode());
			return "First Start";
		}
		else{
			return _currentMode.switchMode();
		}
	}
	/**
	 * get mode id
	 * @return
	 */
	public int getModeType() {
		if(_currentMode == null)
			return 0;
		else
			return _currentMode.getMode();
	}
	public void reset(){
		instance = new EventCenter();
	}
	public boolean getAna() {
		return _isAna;
	}
	public void setAna(boolean _isAna) {
		this._isAna = _isAna;
	}

}
