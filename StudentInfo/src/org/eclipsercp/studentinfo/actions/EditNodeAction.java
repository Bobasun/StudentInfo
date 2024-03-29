package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class EditNodeAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.userselectaction";
	private IStructuredSelection selection;

	public EditNodeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setActionDefinitionId(ID); // for command
		setText("Open");
		setToolTipText("Open");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.OPEN));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void run() {
		INode item = (INode) selection.getFirstElement();
		String editorId = getEditorId();
		editNodeEditor(new NodeEditorInput(item.getPath() + editorId), editorId, item);
	}

	private void editNodeEditor(NodeEditorInput input, String editorId, INode item) {
		try {
			AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage().openEditor(input, editorId);
			editor.setFocus();
			editor.addSelectedNode(item);
			editor.fillFields();
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	private String getEditorId() {
		if (selection.getFirstElement() instanceof ItemNode) {
			return ItemEditor.ID;
		} else if (selection.getFirstElement() instanceof GroupNode) {
			return GroupEditor.ID;
		}
		return null;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(
					selection.getFirstElement() instanceof INode && !(selection.getFirstElement() instanceof RootNode));
		} else {
			setEnabled(false);
		}

	}

}
