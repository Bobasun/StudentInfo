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
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.actions.NewNodeAction;
import org.eclipsercp.studentinfo.actions.OpenFileAction;
import org.eclipsercp.studentinfo.actions.RemoveNodeAction;
import org.eclipsercp.studentinfo.actions.SaveFileAction;
import org.eclipsercp.studentinfo.actions.SavePerspective;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction editUser;
	private IWorkbenchAction newItem;
	private IWorkbenchAction saveNode;
	private IWorkbenchAction newGroup;
	private IWorkbenchAction removeAction;
	private IWorkbenchAction saveAllNodes;
	private IWorkbenchAction exitAction;
//	private IWorkbenchAction aboutAction;
	private IWorkbenchAction savePerspective;
	private IWorkbenchAction saveFile;
	private IWorkbenchAction openFile;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		super.makeActions(window);
		editUser = ActionManager.getInstance(window).getEditNodeAction();
		register(editUser);
		newItem = ActionManager.getInstance(window).getNewItemNodeAction();
		register(newItem);
		saveNode = ActionManager.getInstance(window).getSaveNodeAction();
		register(saveNode);
		saveAllNodes = ActionManager.getInstance(window).getSaveAllNodesAction();
		register(saveAllNodes);
		newGroup = new NewNodeAction(window, NewNodeAction.ID_GROUP);
		register(newGroup);
		removeAction = new RemoveNodeAction(window);
		register(removeAction);
		exitAction = ActionFactory.QUIT.create(window);
		exitAction.setImageDescriptor(
				AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.EXIT));
		register(exitAction);
//		aboutAction = ActionFactory.ABOUT.create(window);
//		register(aboutAction);
		savePerspective = new SavePerspective(window);
		register(savePerspective);
		saveFile = new SaveFileAction(window);
		register(saveFile);
		openFile = new OpenFileAction(window);

	}

	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager menuManager = new MenuManager("&File", "File");
		menuManager.add(openFile);
		menuManager.add(saveFile);
		menuManager.add(savePerspective);
		menuManager.add(exitAction);
//		MenuManager helpMenu = new MenuManager("&Help", "Help");
//		helpMenu.add(aboutAction);
		menuBar.add(menuManager);
//		menuBar.add(helpMenu);

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
