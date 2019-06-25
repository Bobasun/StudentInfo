package org.eclipsercp.studentinfo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipsercp.studentinfo.actions.NewGroupAction;
import org.eclipsercp.studentinfo.actions.NewItemAction;
import org.eclipsercp.studentinfo.actions.RemoveItemAction;
import org.eclipsercp.studentinfo.actions.SaveGroupAction;
import org.eclipsercp.studentinfo.actions.SaveNodeAction;
import org.eclipsercp.studentinfo.actions.EditItemAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction openUser;
	private IWorkbenchAction newItem;
	private IWorkbenchAction saveNode;
	private IWorkbenchAction newGroup;
//	private SaveGroupAction saveGroup;
	private IWorkbenchAction removeAction;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		openUser = new EditItemAction(window);
		register(openUser);
		newItem = new NewItemAction(window);
		register(newItem);
		saveNode = new SaveNodeAction(window);
		register(saveNode);
		newGroup = new NewGroupAction(window);
		register(newGroup);
//		saveGroup = new SaveGroupAction(window);
//		register(saveGroup);
		removeAction = new RemoveItemAction(window);
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
		toolbar.add(openUser); 
		toolbar.add(saveNode);
		toolbar.add(removeAction);
		toolbar.add(newItem);
		toolbar.add(newGroup);
//		toolbar.add(saveGroup);
		
	}

}

