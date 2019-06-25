package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.GroupEditorInput;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;

public class RemoveItemAction extends Action implements ISelectionListener,IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.remove";
	private IStructuredSelection selection;

	public RemoveItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Remove Item");
		setToolTipText("Remove Item");
		
		window.getSelectionService().addSelectionListener(this);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.DELETE));
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		
		if(selection.getFirstElement() instanceof ItemNode) {
			ItemNode item = (ItemNode) selection.getFirstElement();
			Controller.getInstance().remove(item, item.getParent().getPath());
			page.closeEditor(page.findEditor(new ItemEditorInput(item.getPath())), false);
		} else if (selection.getFirstElement() instanceof GroupNode) {
			GroupNode group  = (GroupNode) selection.getFirstElement();
			Controller.getInstance().remove(group, group.getParent().getPath());
			page.closeEditor(page.findEditor(new GroupEditorInput(group.getPath())), false);
//			page.findEditors(new GroupEditorInput(group.getPath()), GroupEditor.ID, IWorkbenchPage.MATCH_INPUT);
//			getActiveEditors(page);
//			page.find
		}
		
//		if(page.getActiveEditor() instanceof ItemEditor) {
//			ItemEditor editor = (ItemEditor) page.getActiveEditor();
//			editor.deleteItem();
//			page.closeEditor(editor, false);
//		} else if (page.getActiveEditor() instanceof GroupEditor) {
//			GroupEditor editor = (GroupEditor) page.getActiveEditor();
//			editor.deleteGroup();
//			page.closeEditor(editor, false);
//		}
	}

	private IEditorReference[] getActiveEditors(IWorkbenchPage page, GroupNode node) {
		int countNodes = node.getChildren().size();
		IEditorReference[] ref = new IEditorReference[] {};
		return ref;
//		return new IEditorReference[] {
//						(IEditorReference) page.findEditor(new ItemEditorInput(""))};
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(!(selection.getFirstElement() instanceof RootNode));
		} else {
			setEnabled(false);
		}		
	}

}
