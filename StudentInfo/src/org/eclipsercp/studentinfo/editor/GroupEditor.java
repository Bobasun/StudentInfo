package org.eclipsercp.studentinfo.editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.view.ChangeNodeListener;

public class GroupEditor extends AbstractEditorPart {

	public final static String ID = "StudentInfo.groupEditor";
	private Text textParentGroup;
	private Label labelParentGroup;
	private Text textGroup;
	private Label labelGroup;
	private GroupNode selectedNode;
	private ChangeNodeListener groupListener;

	public GroupEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		super.dispose();
		Controller.getInstance().removeListener(groupListener);
		
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginTop = 15;
		layout.marginLeft = 30;
		composite.setLayout(layout);

		labelParentGroup = new Label(composite, SWT.NONE);
		labelParentGroup.setText("Parent Group");

		textParentGroup = new Text(composite, SWT.NONE);
		textParentGroup.setEnabled(false);

		labelGroup = new Label(composite, SWT.NONE);
		labelGroup.setText("Group");

		textGroup = new Text(composite, SWT.NONE);
		textGroup.addModifyListener(new TextModifyListener());

		System.err.println("create listener group..");
		
		groupListener = new ChangeNodeListener() {

			@Override
			public void stateChanged(ChangeNodeEvent e) {

				switch (e.getAction()) {
				case REMOVE_NODE:
					if (selectedNode.getPath().contains( e.getOldNode().getPath()))
					//		.equals(e.getOldNode().getPath()))
					{
						getSite().getPage().closeEditor(GroupEditor.this, false);
						System.err.println("Group success deleted");
					}
					break;
				case UPDATE_NODE:
					if (e.getNewNode() instanceof GroupNode) {
						if (selectedNode.getPath().substring(0, e.getOldNode().getParent().getPath().length())
								.equals(e.getOldNode().getParent().getPath())
								&& selectedNode.getParent().getChildren() == ((GroupNode) e.getNewNode())
										.getChildren()) {
//								{
							// set input, set group, set title
							selectedNode.setParent((GroupNode) e.getNewNode());
//							setInput(new NodeEditorInput(selectedNode.getPath() + ID));
							textParentGroup.setText(e.getNewNode().getName());
						}
					}
					System.err.println("Group success updated");
					break;
				case ADD_NODE:
//					setInput(new NodeEditorInput(selectedNode.getPath() + ID));
					System.err.println("Group success added");
					break;
				default:

				}
			}
		};
		Controller.getInstance().addListener(groupListener);
	}

//	private void closeEditorsOfNodesChildren(IWorkbenchPage page, List<INode> nodes) {
//		
//		IEditorPart e = null;
//		for (INode node : nodes) {
//				e = page.findEditor(new NodeEditorInput(node.getPath()));
//				if (node.hasChildren()) {
//					closeEditorsOfNodesChildren(page, ((GroupNode) node).getChildren());
//				}
//			if (e != null) {
//				page.closeEditor(e, false);
//			}
//		}
//	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		GroupNode node = new GroupNode(textGroup.getText());
//		if (!isChildrenOpen(selectedNode.getChildren())) {
		if (Controller.getInstance().isNodeExists(selectedNode.getParent())) {
			Controller.getInstance().save(selectedNode, node);
			if (Controller.getInstance().isNodeExists(node)) {
				selectedNode = node;
				NodeEditorInput input = (NodeEditorInput) getEditorInput();
				input.setName(selectedNode.getPath() + ID);
//		} else {
//			MessageDialog.openError(this.getSite().getShell(), "Error", "Please, close child groups and items!");
//		}
				setDirty(false);
				setPartName(selectedNode.getName());
			}
		}

	}

	private boolean isChildrenOpen(List<INode> nodes) {
		IEditorPart e = null;
		boolean isOpen = false;
		for (INode node : nodes) {
			if (node instanceof GroupNode) {
				e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.findEditor(new NodeEditorInput(node.getPath()));
				isOpen = (e != null || isChildrenOpen(((GroupNode) node).getChildren())) ? true : false;
//				isOpen = isChildrenOpen(((GroupNode) node).getChildren());
			} else if (node instanceof ItemNode) {
				e = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
						.findEditor(new NodeEditorInput(node.getPath()));
				isOpen = e != null ? true : false;
			}
			if (isOpen) {
				break;
			}
		}
		return isOpen;
	}

	public Text getTextGroup() {
		return textGroup;
	}

	public void addSelectedNode(INode node) {
		selectedNode = (GroupNode) node;
	}

	public void deleteGroup() { // delete second param
		Controller.getInstance().remove(selectedNode, selectedNode.getParent().getPath());

	}

	public void fillFields() {
		textGroup.setText(selectedNode.getName());
		textParentGroup.setText(selectedNode.getParent().getName());
		setPartName(selectedNode.getName());
	}

	@Override
	protected boolean checkModifyFields() {
		// TODO Auto-generated method stub
		return !textGroup.getText().equals(selectedNode.getName()) ? true : false;
	}

}
