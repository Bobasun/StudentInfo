package org.eclipsercp.studentinfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipsercp.studentinfo.model.NodeService;

public class Controller {

	private static Controller instance;
	private NodeService service = NodeService.getInstance();
	private List<ChangeListener> listListeners = new ArrayList<>();
	
	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void save() {
		notifyAllListeners(null);
	}
	
	public void addListener(ChangeListener lis) {
		listListeners.add(lis);
	}

	private void notifyAllListeners(ChangeEvent event) {
		listListeners.forEach(listener -> listener.stateChanged(event));
//		for (int i = 0; i < list.size(); i++)
//			list.get(i).stateChanged(event);
	}
	
	public void removeListener(ChangeListener lis) {
		listListeners.remove(lis);
	}
}
