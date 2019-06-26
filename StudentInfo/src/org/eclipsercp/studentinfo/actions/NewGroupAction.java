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
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.GroupEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class NewGroupAction extends Action implements IWorkbenchAction, ISelectionListener {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.newgroup";
	private IStructuredSelection selection;

	public NewGroupAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("New Group");
		setToolTipText("New Group");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.NEW_FOLDER));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(!(selection.getFirstElement() instanceof ItemNode));
		} else {
			setEnabled(false);
		}
	}

	@Override
	public void run() {
		GroupEditorInput input = new GroupEditorInput("new Group");
		openEditor(input, window.getActivePage());

	}

	private void openEditor(GroupEditorInput input, IWorkbenchPage page) {
		GroupNode parent = getParent();
		try {
			page.openEditor(input, GroupEditor.ID);
			GroupEditor editor = (GroupEditor) page.getActiveEditor();
//			editor.getHidenText().setText(parent.getPath());
			editor.addSelectedNode(new GroupNode(parent));
		} catch (PartInitException e) {
			System.err.println("Error NewGroupAction ");
		}
	}

	private GroupNode getParent() {
		GroupNode parent = null;
		if (selection.getFirstElement() == null) {
			parent = NodeService.getInstance().getRoot();
		} else {
			parent = (GroupNode) selection.getFirstElement();
		}
		return parent;
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

}
