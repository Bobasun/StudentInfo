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
import org.eclipsercp.studentinfo.controller.ChangeNodeListener;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class GroupEditor extends AbstractEditorPart {

	public final static String ID = "StudentInfo.groupEditor";
	private Text textParentGroup;
	private Text textGroup;
	
	public GroupEditor() {
	}

	@Override
	public void createPartControl(Composite parent) {
		createEditorContext(parent);

	}

	protected void addNodeListener() {
		listener = new ChangeNodeListener() {

			@Override
			public void stateChanged(ChangeNodeEvent event) {
				switch (event.getAction()) {
				case REMOVE_NODE:
					if (selectedNode.getPath().contains(event.getOldNode().getPath())) {
						getSite().getPage().closeEditor(GroupEditor.this, false);
					}
					break;
				case UPDATE_NODE:
					if (event.getNewNode() instanceof GroupNode) {
						if (getSelectedNode().getParent().getPath().equals(event.getNewNode().getPath())) {
							textGroup.setText(event.getNewNode().getName());
//							if (selectedNode.getPath().substring(0, e.getOldNode().getParent().getPath().length())
//									.equals(e.getOldNode().getParent().getPath())
//									&& selectedNode.getParent().getChildren() == ((GroupNode) e.getNewNode())
//											.getChildren()) {
							selectedNode.setParent((GroupNode) event.getNewNode());
							textParentGroup.setText(event.getNewNode().getName());
						}
					}
					break;
				case ADD_NODE:
					break;
				default:
				}
			}
		};
		Controller.getInstance().addListener(listener);
	}

	protected void createItputItems(Composite composite) {
		createParentGroupRow(composite);
		createInputGroupRow(composite);
	}

	private void createParentGroupRow(Composite composite) {
		Label labelParentGroup = new Label(composite, SWT.NONE);
		labelParentGroup.setText("Parent Group");
		textParentGroup = new Text(composite, SWT.NONE);
		textParentGroup.setEnabled(false);
	}

	private void createInputGroupRow(Composite composite) {
		Label labelGroup = new Label(composite, SWT.NONE);
		labelGroup.setText("Group");
		textGroup = new Text(composite, SWT.NONE);
		textGroup.addModifyListener(new TextModifyListener());
	}

	protected void compositeSetting(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginTop = 15;
		layout.marginLeft = 30;
		composite.setLayout(layout);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		GroupNode node = new GroupNode(textGroup.getText());
		doSave(node);
//		if (Controller.getInstance().isNodeExists(selectedNode.getParent())) {
//			Controller.getInstance().save(selectedNode, node);
//			if (Controller.getInstance().isNodeExists(node)) {
//				selectedNode = node;
//				NodeEditorInput input = (NodeEditorInput) getEditorInput();
//				input.setName(selectedNode.getPath() + ID);
//				setDirty(false);
//				setPartName(selectedNode.getName());
//			}
//		}
	}
	
	public Text getTextGroup() {
		return textGroup;
	}

	

	public void fillFields() {
		textGroup.setText(selectedNode.getName());
		textParentGroup.setText(selectedNode.getParent().getName());
		setPartName(selectedNode.getName());
	}

	@Override
	protected boolean checkModifyFields() {
		return !textGroup.getText().equals(selectedNode.getName()) ? true : false;
	}

	@Override
	protected String getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	protected void setSelectedNode(INode node) {
		// TODO Auto-generated method stub
		
	}
	private GroupNode getSelectedNode() {
		return (GroupNode) selectedNode;
	}

	

}
