package org.eclipsercp.studentinfo.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;

public class SaveAllNodesAction extends Action implements IWorkbenchAction{
	
	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.saveallnodes";
	
	@Override
	public void dispose() {
	
	}
	
	public SaveAllNodesAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save All");
		setToolTipText("Save All");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Application.PLUGIN_ID, ImageKeys.SAVE_ALL));
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		for (IEditorReference ref : page.getEditorReferences()) {
			if(ref.isDirty()) {
				AbstractEditorPart editor = (AbstractEditorPart) ref.getEditor(false);
				editor.doSave(new NullProgressMonitor());
			}
		}

	}

}
