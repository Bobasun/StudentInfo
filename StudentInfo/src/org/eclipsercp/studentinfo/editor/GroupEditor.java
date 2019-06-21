package org.eclipsercp.studentinfo.editor;

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
import org.eclipsercp.studentinfo.model.NodeService;

public class GroupEditor extends AbstractEditorPart {

	public final static String ID = "StudentInfo.groupEditor";

	public GroupEditor() {
		// TODO Auto-generated constructor stub
	}

	private Text textGroup;
	private Label labelGroup;
	private Text hidenText;

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

	public void setContent() {
		GroupNode node = new GroupNode(textGroup.getText());
		Controller.getInstance().save(node, hidenText.getText());
//		setDirty(false);
	}

	public Text getHidenText() {
		return hidenText;
	}

	public Text getTextGroup() {
		return textGroup;
	}

	public void setTextGroup(Text textGroup) {
		this.textGroup = textGroup;
	}

	public void setHidenText(Text hidenText) {
		this.hidenText = hidenText;
	}

}
