package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class NewItemAction extends Action implements ISelectionListener, IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.newitem";
	private IStructuredSelection selection;

	public NewItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("New Item");
		setToolTipText("New Item");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.NEW_ITEM));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void run() {
		openNewEditor(new NodeEditorInput("new Item"));
	}

	private void openNewEditor(NodeEditorInput input) {
		try {
			ItemEditor editor = (ItemEditor) window.getActivePage().openEditor(input, ItemEditor.ID, false);
			editor.addSelectedNode(new ItemNode(getParent()));
			editor.fillFields();
		} catch (PartInitException e) {
			System.err.println("Error NewItemAction");
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
