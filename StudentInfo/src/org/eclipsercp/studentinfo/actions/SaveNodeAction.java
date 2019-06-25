package org.eclipsercp.studentinfo.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;

public class SaveNodeAction extends Action implements IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.saveitem";

	public SaveNodeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save");
		setToolTipText("Save");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Application.PLUGIN_ID, ImageKeys.SAVE));
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		if(page.getActiveEditor() instanceof ItemEditor) {
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			editor.doSave(new NullProgressMonitor());
			
		} else if(page.getActiveEditor() instanceof GroupEditor) {
			GroupEditor editor = (GroupEditor) page.getActiveEditor();
			editor.doSave(new NullProgressMonitor());
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
