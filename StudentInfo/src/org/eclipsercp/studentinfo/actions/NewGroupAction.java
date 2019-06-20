package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.GroupEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;

public class NewGroupAction extends Action implements IWorkbenchAction, ISelectionListener {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.newgroup";
	private IStructuredSelection selection;

	public NewGroupAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("New Group");
		setToolTipText("New Group");
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
		IWorkbenchPage page = window.getActivePage();
		GroupEditorInput input = new GroupEditorInput("new Group");
		try {
			page.openEditor(input, GroupEditor.ID);
			GroupEditor editor = (GroupEditor) page.getActiveEditor();
			editor.setParent((INode) selection.getFirstElement());
			
//			GroupNode group = new GroupNode(name)
		} catch (PartInitException e) {
			System.err.println("Error NewGroupAction ");
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
