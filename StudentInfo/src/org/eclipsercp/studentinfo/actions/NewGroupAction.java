package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class NewGroupAction extends Action implements IWorkbenchAction, ISelectionListener {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.newgroup";
	private IStructuredSelection selection;

	public NewGroupAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("New Group");
		setToolTipText("New Group");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.NEW_FOLDER));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void run() {
		openNewEditor(new NodeEditorInput("new Group"));
	}

	private void openNewEditor(NodeEditorInput input) {
		try {
			GroupEditor editor = (GroupEditor) window.getActivePage().openEditor(input, GroupEditor.ID, false);
			editor.addSelectedNode(new GroupNode(getParent()));
			editor.fillFields();
		} catch (PartInitException e) {
			System.err.println("Error NewGroupAction ");
		}
	}

	private GroupNode getParent() {
		if (selection.getFirstElement() != null) {
			return (GroupNode) selection.getFirstElement();
		} else {
			return NodeService.getInstance().getRoot();
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(selection.getFirstElement() instanceof GroupNode);
		} else {
			setEnabled(false);
		}
	}
}
