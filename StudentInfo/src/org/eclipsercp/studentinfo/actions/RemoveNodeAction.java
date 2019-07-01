package org.eclipsercp.studentinfo.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;

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
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, ImageKeys.DELETE));
	}

	@Override
	public void run() {
		IWorkbenchPage page = window.getActivePage();	
		INode node = (INode) selection.getFirstElement();
//		page.closeEditor(page.findEditor(new NodeEditorInput(node.getPath())), true);
//		closeEditorsOfNodesChildren(page, ((GroupNode) node).getChildren());
		Controller.getInstance().remove(node, node.getParent().getPath());
	}

//	private void closeEditorsOfNodesChildren(IWorkbenchPage page, List<INode> nodes) {
//		IEditorPart e = null;
//		for (INode node : nodes) {
//				e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
//						.findEditor(new NodeEditorInput(node.getPath()));
//				if (node.hasChildren()) {
//					closeEditorsOfNodesChildren(page, ((GroupNode) node).getChildren());
//				}
//			if (e != null) {
//				page.closeEditor(e, true);
//			}
//		}
//	}

	@Override
	public void dispose() {

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
