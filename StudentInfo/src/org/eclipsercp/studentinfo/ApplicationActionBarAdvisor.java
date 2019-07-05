package org.eclipsercp.studentinfo;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipsercp.studentinfo.actions.EditNodeAction;
import org.eclipsercp.studentinfo.actions.NewItemAction;
import org.eclipsercp.studentinfo.actions.RemoveNodeAction;
import org.eclipsercp.studentinfo.actions.SaveAllNodesAction;
import org.eclipsercp.studentinfo.actions.SaveNodeAction;
import org.eclipsercp.studentinfo.actions.SavePerspective;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction editUser;
	private IWorkbenchAction newItem;
	private IWorkbenchAction saveNode;
	private IWorkbenchAction newGroup;
	private IWorkbenchAction removeAction;
	private IWorkbenchAction saveAllNodes;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction savePerspective;
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		editUser = new EditNodeAction(window);
		register(editUser);
		newItem = new NewItemAction(window,NewItemAction.ID_ITEM);
		register(newItem);
		saveNode = new SaveNodeAction(window);
		register(saveNode);
		saveAllNodes = new SaveAllNodesAction(window);
		register(saveAllNodes);
		newGroup = new NewItemAction(window, NewItemAction.ID_GROUP);
		register(newGroup);
		removeAction = new RemoveNodeAction(window);
		register(removeAction);
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		savePerspective = new SavePerspective(window);
		register(savePerspective);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager menuManager = new MenuManager("&File", "File");
		menuManager.add(exitAction);
		menuManager.add(savePerspective);
		MenuManager helpMenu = new MenuManager("&Help", "Help");
		helpMenu.add(aboutAction);
		menuBar.add(menuManager);
		menuBar.add(helpMenu);
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

