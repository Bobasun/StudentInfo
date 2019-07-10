package org.eclipsercp.studentinfo;

import java.util.HashMap;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.actions.EditNodeAction;
import org.eclipsercp.studentinfo.actions.NewNodeAction;
import org.eclipsercp.studentinfo.actions.OpenFileAction;
import org.eclipsercp.studentinfo.actions.RemoveNodeAction;
import org.eclipsercp.studentinfo.actions.SaveAllNodesAction;
import org.eclipsercp.studentinfo.actions.SaveFileAction;
import org.eclipsercp.studentinfo.actions.SaveNodeAction;
import org.eclipsercp.studentinfo.actions.SavePerspective;

public class ActionManager {
	private static final String EDIT_NODE_ACTION = "editNodeAction";
	private static final String NEW_ITEM_NODE_ACTION = "newItemAction";
	private static final String NEW_GROUP_NODE_ACTION = "newGroupAction";
	private static final String REMOVE_NODE_ACTION = "removeAction";
	private static final String SAVE_NODE_NODE_ACTION = "saveNodeAction";
	private static final String SAVE_ALL_NODES_ACTION = "saveAllNodesAction";
	private static final String EXIT_ACTION = "exitAction";
	private static final String SAVE_FILE_ACTION = "saveFileAction";
	private static final String OPEN_FILE_ACTION = "openFileAction";
	private static final String ABOUT_ACTION = "aboutAction";

	private HashMap<String, IWorkbenchAction> mapAction = new HashMap<>();
	private static HashMap<IWorkbenchWindow, ActionManager> map = new HashMap<>();

	private ActionManager(IWorkbenchWindow window) {
		createActions(window);
	}

	private void createActions(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		mapAction.put(EDIT_NODE_ACTION, new EditNodeAction(window));
		mapAction.put(NEW_ITEM_NODE_ACTION, new NewNodeAction(window, NewNodeAction.ID_ITEM));
		mapAction.put(NEW_GROUP_NODE_ACTION, new NewNodeAction(window, NewNodeAction.ID_GROUP));
		mapAction.put(REMOVE_NODE_ACTION, new RemoveNodeAction(window));
		mapAction.put(SAVE_ALL_NODES_ACTION, new SaveAllNodesAction(window));
		mapAction.put(SAVE_NODE_NODE_ACTION, new SaveNodeAction(window));
		mapAction.put(EXIT_ACTION, ActionFactory.QUIT.create(window));
		mapAction.put(SAVE_FILE_ACTION, new SaveFileAction(window));
		mapAction.put(OPEN_FILE_ACTION, new OpenFileAction(window));
		mapAction.put(ABOUT_ACTION, ActionFactory.ABOUT.create(window));

	}

	public static ActionManager getInstance(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		if (!map.containsKey(window)) {
			map.put(window, new ActionManager(window));
		}
		return map.get(window);
	}

	public IWorkbenchAction getEditNodeAction() {
		return mapAction.get(EDIT_NODE_ACTION);
	}

//	
	public IWorkbenchAction getNewGroupNodeAction() {
		return mapAction.get(NEW_GROUP_NODE_ACTION);
	}

	public IWorkbenchAction getNewItemNodeAction() {
		return mapAction.get(NEW_ITEM_NODE_ACTION);
	}

	public IWorkbenchAction getRemoveNodeAction() {
		return mapAction.get(REMOVE_NODE_ACTION);
	}

	public IWorkbenchAction getSaveAllNodesAction() {
		return mapAction.get(SAVE_ALL_NODES_ACTION);
	}

	public IWorkbenchAction getSaveNodeAction() {
		return mapAction.get(SAVE_NODE_NODE_ACTION);
	}

	public IWorkbenchAction getExitAction() {
		return mapAction.get(EXIT_ACTION);
	}

	public IWorkbenchAction getHelpAction() {
		return mapAction.get(ABOUT_ACTION);
	}

	public IWorkbenchAction getSaveFileAction() {
		return mapAction.get(SAVE_FILE_ACTION);
	}

	public IWorkbenchAction getOpenFileAction() {
		return mapAction.get(OPEN_FILE_ACTION);
	}
}
