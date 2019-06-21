package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.ItemNode;

public class SaveGroupAction extends Action implements ISelectionListener,IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.savegroup";

	public SaveGroupAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save Group");
		setToolTipText("Save Group");
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		
		if(page.getActiveEditor() instanceof GroupEditor){
//		if ((page.getActiveEditor() != null)) {
//			if (page.getActiveEditor().getEditorInput().getName().equalsIgnoreCase("group new")) {
				GroupEditor editor = (GroupEditor) page.getActiveEditor();

				editor.setContent();
//				page.closeEditor(editor, false);
				
//			}
//		}
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
//		IWorkbenchPage page = window.getActivePage();
//		
//		if(page.getActiveEditor() instanceof GroupEditor){
////		if ((page.getActiveEditor() != null)) {
////			if (page.getActiveEditor().getEditorInput().getName().equalsIgnoreCase("group new")) {
//				GroupEditor editor = (GroupEditor) page.getActiveEditor();
//				setEnabled(editor.isDirty());
//	}
		
//		setEnabled(false);
	}

}
