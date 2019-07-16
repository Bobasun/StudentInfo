package org.eclipsercp.studentinfo.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class RemoveNodeAction extends Action implements ISelectionListener, IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.remove";
	private IStructuredSelection selection;

	public RemoveNodeAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("Remove");
		setToolTipText("Remove");
		window.getSelectionService().addSelectionListener(this);
		setImageDescriptor(
				AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, UtilsWithConstants.DELETE));
	}

	@Override
	public void run() {
		INode node = (INode) selection.getFirstElement();
		Controller.getInstance().remove(node);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
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
