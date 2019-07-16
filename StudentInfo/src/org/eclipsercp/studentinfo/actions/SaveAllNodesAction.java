package org.eclipsercp.studentinfo.actions;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class SaveAllNodesAction extends Action implements IWorkbenchAction {

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
		setImageDescriptor(
				AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.SAVE_ALL));
		setAccelerator(SWT.CTRL | SWT.SHIFT | 's');
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		for (IEditorReference ref : page.getEditorReferences()) {
			if (ref.isDirty()) {
				AbstractEditorPart editor = (AbstractEditorPart) ref.getEditor(false);
				editor.doSave(new NullProgressMonitor());
			}
		}

	}

}
