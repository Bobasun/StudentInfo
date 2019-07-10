package org.eclipsercp.studentinfo.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;

public class MyMenuManager {

	//private IWorkbenchWindow window
	private TreeViewer treeViewer;
	private MenuManager manager;
	private String id;

	public MyMenuManager(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
		this.manager = new MenuManager();
		addMenuItems();
	}

	private void addMenuItems() {
//		manager.add(action);
//		manager.add(createAddAction());
//		manager.add(createRemoveAction());
//		manager.add(createSeparator());
//		manager.add(createGroupOptionAction());
//		manager.add(createItemOptionAction());
	}

	private Action createItemOptionAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private Action createGroupOptionAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private Action createSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	private Action createRemoveAction() {
		// TODO Auto-generated method stub
		return null;
	}

	private Action createAddAction() {
		
		return null;
	}

	public Menu createMenu() {
		return manager.createContextMenu(treeViewer.getTree());
	}

}
