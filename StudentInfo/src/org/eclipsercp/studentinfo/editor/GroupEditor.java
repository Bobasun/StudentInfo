package org.eclipsercp.studentinfo.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class GroupEditor extends AbstractEditorPart {

	public final static String ID = "StudentInfo.groupEditor";

	public GroupEditor() {
		// TODO Auto-generated constructor stub
	}

	private Text textGroup;
	private Label labelGroup;
	private Text hidenText;
	private GroupNode selectedNode;

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginTop = 15;
		layout.marginLeft = 30;
		composite.setLayout(layout);

		labelGroup = new Label(composite, SWT.NONE);
		labelGroup.setText("Group");

		textGroup = new Text(composite, SWT.NONE);
		hidenText = new Text(parent, SWT.NONE);
		hidenText.setVisible(false);
		textGroup.addModifyListener(new TextModifyListener());
	}

	 @Override
		public void doSave(IProgressMonitor monitor) {
		GroupNode node = new GroupNode(textGroup.getText());
		Controller.getInstance().save(selectedNode, node);
//		selectedNode = (GroupNode) Controller.getInstance().getNode(hidenText.getText(), node);
		selectedNode = node;
		GroupEditorInput input = (GroupEditorInput) getEditorInput();
		input.setName(hidenText.getText());
		System.err.println(hidenText.getText());
		setDirty(false);
		//
	}

	public Text getHidenText() {
		return hidenText;
	}

	public Text getTextGroup() {
		return textGroup;
	}

	public void addSelectedNode(GroupNode node) {
		selectedNode = node;
	}

	public void deleteGroup() {
		Controller.getInstance().remove(selectedNode, hidenText.getText());

	}

	public void fillFields() {
		textGroup.setText(selectedNode.getName());
		getHidenText().setText(selectedNode.getParent().getPath());
	}

}
