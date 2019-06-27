package org.eclipsercp.studentinfo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipsercp.studentinfo.actions.EditNodeAction;
import org.eclipsercp.studentinfo.actions.NewGroupAction;
import org.eclipsercp.studentinfo.actions.NewItemAction;
import org.eclipsercp.studentinfo.actions.RemoveNodeAction;
import org.eclipsercp.studentinfo.actions.SaveAllNodesAction;
import org.eclipsercp.studentinfo.actions.SaveNodeAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction editUser;
	private IWorkbenchAction newItem;
	private IWorkbenchAction saveNode;
	private IWorkbenchAction newGroup;
	private IWorkbenchAction removeAction;
	private IWorkbenchAction saveAllNodes;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		editUser = new EditNodeAction(window);
		register(editUser);
		newItem = new NewItemAction(window);
		register(newItem);
		saveNode = new SaveNodeAction(window);
		register(saveNode);
		saveAllNodes = new SaveAllNodesAction(window);
		register(saveAllNodes);
		newGroup = new NewGroupAction(window);
		register(newGroup);
		removeAction = new RemoveNodeAction(window);
		register(removeAction);
		
		
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		super.fillMenuBar(menuBar);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		super.fillCoolBar(coolBar);
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle());  
		coolBar.add(toolbar); 
		toolbar.add(editUser); 
		toolbar.add(saveNode);
		toolbar.add(saveAllNodes);
		toolbar.add(removeAction);
		toolbar.add(newItem);
		toolbar.add(newGroup);
		
	}

}

