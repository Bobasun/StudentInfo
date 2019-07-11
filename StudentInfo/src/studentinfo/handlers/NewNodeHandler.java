package studentinfo.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeFactory;
import org.eclipsercp.studentinfo.model.ItemNode;

public class NewNodeHandler extends AbstractCommonHandler {

	public static final String ADD_COMMAND_ID = "StudentInfo.commands.commandNew";

	private IWorkbenchWindow window;
	private String id;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelectionService selectionService = window.getSelectionService();
		IStructuredSelection selection = (IStructuredSelection) selectionService.getSelection();
		GroupNode parent = null;
		if (selection.getFirstElement() instanceof INode) {
			parent = getParent((INode) selection.getFirstElement());
		} else {
			parent = Controller.getInstance().getRootNode();
		}
		State state = getStateItemOption();
		State state2 = getStateGroupOption();
		if ((Boolean) state.getValue()) {
			id = ItemEditor.ID;
		} else if ((Boolean) state2.getValue()) {
			id = GroupEditor.ID;
		}
		if (id != null) {
			openNewEditor(new NodeEditorInput(id), parent);
		}
	
		return null;
	}

	private GroupNode getParent(INode node) {
		if (node instanceof GroupNode) {
			return (GroupNode) node;
		} else if (node instanceof ItemNode) {
			return (node.getParent());
		}
		return null;

	}

	private void openNewEditor(NodeEditorInput input, GroupNode parent) {
		try {
			AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage().openEditor(input, id, false);
			INodeFactory nodeFactory = INodeFactory.createNodeFactoty(id);
			editor.addSelectedNode(nodeFactory.createINode(parent));
			editor.fillFields();
		} catch (PartInitException e) {
			System.err.println("Error NewItemAction");
		}
	}

}
