package org.eclipsercp.studentinfo.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.EditorPart;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;

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
	}

	public void fillFields() {
		textGroup.setText(getSelectedNode().getName());
		textParentGroup.setText(getSelectedNode().getParent().getName());
		setPartName(getSelectedNode().getName());
	}

	@Override
	protected boolean checkModifyFields() {
		return !textGroup.getText().equals(selectedNode.getName()) ? true : false;
	}

	@Override
	protected String getID() {
		return ID;
	}

	private GroupNode getSelectedNode() {
		return (GroupNode) selectedNode;
	}

	@Override
	protected void setFields(INode node) {
		selectedNode.setParent((GroupNode) node);
		textParentGroup.setText(node.getName());
	}

	@Override
	protected EditorPart getActiveEditor() {
		return this;
	}

	public Text getTextGroup() {
		return textGroup;
	}

}
