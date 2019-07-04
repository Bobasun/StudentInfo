package org.eclipsercp.studentinfo.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class SaveNodeAction extends Action implements IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.savenode";

	public SaveNodeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Save");
		setToolTipText("Save");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Application.PLUGIN_ID, UtilsWithConstants.SAVE));
	}

	@Override
	public void run() {
		AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage().getActiveEditor();
		editor.doSave(new NullProgressMonitor());

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
