package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.ItemNode;

public class EditItemAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.userselectaction";
	private IStructuredSelection selection;

	public EditItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Open");
		setToolTipText("User selection action!");
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void run() {
		Object item = selection.getFirstElement();
		ItemNode user = (ItemNode) item;
		IWorkbenchPage page = window.getActivePage();
		ItemEditorInput input = new ItemEditorInput(user.getName());
		try {
			page.openEditor(input, ItemEditor.ID);
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			editor.showItem(user);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
//		System.err.println(incoming  + " Selection");
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(selection.getFirstElement() instanceof ItemNode);
		} else {
			setEnabled(false);
		}

	}

}
