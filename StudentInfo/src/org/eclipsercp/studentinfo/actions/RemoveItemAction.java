package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;

public class RemoveItemAction extends Action implements IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.remove";

	public RemoveItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Remove Item");
		setToolTipText("Remove Item");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.DELETE));
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();
		if(page.getActiveEditor() instanceof ItemEditor) {
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			editor.deleteItem();
			page.closeEditor(editor, false);
		} else if (page.getActiveEditor() instanceof GroupEditor) {
			GroupEditor editor = (GroupEditor) page.getActiveEditor();
			editor.deleteGroup();
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
