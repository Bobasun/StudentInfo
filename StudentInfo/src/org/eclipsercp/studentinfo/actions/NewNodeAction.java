package org.eclipsercp.studentinfo.actions;

import java.util.Properties;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INodeFactory;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class NewNodeAction extends Action implements ISelectionListener, IWorkbenchAction {

	private final IWorkbenchWindow window;
	private String id;
	public final static String ID_ITEM = "org.eclipsercp.studentinfo.newitem";
	public final static String ID_GROUP = "org.eclipsercp.studentinfo.newgroup";
	private IStructuredSelection selection;

	public NewNodeAction(IWorkbenchWindow window, String id) {
		this.window = window;

		setId(id);
		Properties properties = UtilsWithConstants.getProperties(id);
		setText(properties.getProperty("text"));
		setToolTipText(properties.getProperty("toolTipText"));
		setImageDescriptor(
				AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, properties.getProperty("image")));
		this.id = properties.getProperty("id");
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void run() {
		openNewEditor(new NodeEditorInput(id));
	}

	private void openNewEditor(NodeEditorInput input) {
		try {
			AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage().openEditor(input, id, false);
			INodeFactory nodeFactory = INodeFactory.createNodeFactoty(id);
			editor.addSelectedNode(nodeFactory.createINode(getParent()));
			editor.fillFields();
		} catch (PartInitException e) {
			System.err.println("Error NewItemAction");
		}
	}

	private GroupNode getParent() {
		if (selection.getFirstElement() != null) {
			return (GroupNode) selection.getFirstElement();
		} else {
			return NodeService.getInstance().getRoot();
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(selection.getFirstElement() instanceof GroupNode);
		} else {
			setEnabled(false);
		}
	}

}
