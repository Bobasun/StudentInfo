package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;

public class SaveItemAction extends Action implements IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.saveitem";

	public SaveItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save Item");
		setToolTipText("Save Item");
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		if(page.getActiveEditor() instanceof ItemEditor) {
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			editor.setContent();
//			setEnabled(editor.isDirty());
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
