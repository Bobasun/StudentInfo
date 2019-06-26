package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.GroupEditorInput;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;

public class EditItemAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.userselectaction";
	private IStructuredSelection selection;

	public EditItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Open");
		setToolTipText("Open");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.OPEN));
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void run() {
		IWorkbenchPage page = window.getActivePage();
		if (selection.getFirstElement() instanceof ItemNode) {
			ItemNode qitem = (ItemNode) selection.getFirstElement();
			ItemNode item = ((ItemNode) qitem.clone());
			ItemEditorInput input = new ItemEditorInput(item.getPath());
			try {
				page.openEditor(input, ItemEditor.ID);
				ItemEditor editor = (ItemEditor) page.getActiveEditor();
				editor.addSelectedNode(item);
				editor.fillFields();
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(selection.getFirstElement() instanceof GroupNode) {
			GroupNode node  = (GroupNode) selection.getFirstElement();
//			GroupNode node = (GroupNode) group.clone();
			GroupEditorInput input = new GroupEditorInput(node.getPath());
			System.err.println("----" + node.getPath());
			try {
				page.openEditor(input, GroupEditor.ID);
				GroupEditor editor = (GroupEditor) page.getActiveEditor();
				editor.addSelectedNode(node);
				editor.fillFields();
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(selection.getFirstElement() instanceof RootNode) {
			
		}

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(selection.getFirstElement() instanceof INode);
		} else {
			setEnabled(false);
		}

	}

}
