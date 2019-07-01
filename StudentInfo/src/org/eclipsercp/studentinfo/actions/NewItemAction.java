package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
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
import org.eclipsercp.studentinfo.model.INode;
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
		GroupNode parent = null;
		if (selection.getFirstElement() == null) {
			parent = NodeService.getInstance().getRoot();
		} else {
			parent = (GroupNode) selection.getFirstElement();
		}

		IWorkbenchPage page = window.getActivePage();
		NodeEditorInput input = new NodeEditorInput("");
		try {
			page.openEditor(input, ItemEditor.ID,false);
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			editor.addSelectedNode(new ItemNode(parent));
			editor.fillFields();
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
			
		this.selection = (IStructuredSelection) incoming;
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(!(selection.getFirstElement() instanceof ItemNode));
		} else {
			setEnabled(false);
		}
	}

}
