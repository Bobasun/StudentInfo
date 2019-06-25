package org.eclipsercp.studentinfo.editor;

import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.Node;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.model.RootNode;

public class ItemEditor extends AbstractEditorPart implements IReusableEditor{

	public final static String ID = "StudentInfo.editor";
	private Label labelName;
	private Label labelGroup;
	private Label labelAddress;
	private Label labelCity;
	private Label labelResult;
	private Text textName;
	private Text textGroup;
	private Text textAddress;
	private Text textCity;
	private Text textResult;
	private Text hidenText;
	
	private ItemNode selectedNode;

	public ItemEditor() {
	}

	

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setLayout(new FillLayout());

		Composite composite = new Composite(sashForm, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginTop = 15;
		layout.marginLeft = 30;
		layout.horizontalSpacing = 15;
		layout.verticalSpacing = 15;
		composite.setLayout(layout);

		GridData data = new GridData(GridData.BEGINNING);

		labelName = new Label(composite, SWT.NONE);
		labelName.setText("Name");
		labelName.setData(data);

		data = new GridData(GridData.END);
		data = new GridData(GridData.FILL, GridData.FILL, true, false);
		textName = new Text(composite, SWT.BORDER);
		textName.setData(data);
		textName.addModifyListener(new TextModifyListener());

		data = new GridData(GridData.BEGINNING);
		labelGroup = new Label(composite, SWT.NONE);
		labelGroup.setText("Group");
		labelGroup.setData(data);

		data = new GridData(GridData.END);
		textGroup = new Text(composite, SWT.BORDER);
		textGroup.setData(data);
		textGroup.setEnabled(false);

		data = new GridData(GridData.BEGINNING);
		labelAddress = new Label(composite, SWT.NONE);
		labelAddress.setText("Address");
		labelAddress.setData(data);

		data = new GridData(GridData.END);
		textAddress = new Text(composite, SWT.BORDER);
		textAddress.setData(data);
		textAddress.addModifyListener(new TextModifyListener());

		data = new GridData(GridData.BEGINNING);
		labelCity = new Label(composite, SWT.NONE);
		labelCity.setText("City");
		labelCity.setData(data);

		data = new GridData(GridData.END);
		textCity = new Text(composite, SWT.BORDER);
		textCity.setData(data);
		textCity.addModifyListener(new TextModifyListener());

		data = new GridData(GridData.BEGINNING);
		labelResult = new Label(composite, SWT.NONE);
		labelResult.setText("Result");
		labelResult.setData(data);

		data = new GridData(GridData.END);
		textResult = new Text(composite, SWT.BORDER);
		textResult.setData(data);
		textResult.addModifyListener(new TextModifyListener());

		hidenText = new Text(parent, SWT.NONE);
		hidenText.setVisible(false);
//		Composite compositeImage = new Composite(sashForm,SWT.NONE);
	}

	public void addSelectedNode(ItemNode item) {
		this.selectedNode = item;
	}

	@Override
	public void setFocus() {

	}

	public Text getTextName() {
		return textName;
	}

	public void setTextName(Text textName) {
		this.textName = textName;
	}

	public Text getTextGroup() {
		return textGroup;
	}

	public void setTextGroup(Text textGroup) {
		this.textGroup = textGroup;
	}

	public Text getTextAddress() {
		return textAddress;
	}

	public void setTextAddress(Text textAddress) {
		this.textAddress = textAddress;
	}

	public Text getTextCity() {
		return textCity;
	}

	public void setTextCity(Text textCity) {
		this.textCity = textCity;
	}

	public Text getTextResult() {
		return textResult;
	}

	public void setTextResult(Text textResult) {
		this.textResult = textResult;
	}

	public void fillFields() {
		getTextName().setText(selectedNode.getName());
		getTextAddress().setText(selectedNode.getAddress());
		getTextGroup().setText(selectedNode.getGroup());
		getTextCity().setText(selectedNode.getCity());
		getTextResult().setText("" + selectedNode.getResult());
		getHidenText().setText(selectedNode.getParent().getPath());

	}

	
   @Override
	public void doSave(IProgressMonitor monitor) {
		int fieldResult = Integer.parseInt(getTextResult().getText());
		ItemNode node = new ItemNode(getTextName().getText(), getTextAddress().getText(), getTextCity().getText(), fieldResult);

//		if(Controller.getInstance().isNodeExists(selectedNode)) {
			Controller.getInstance().save(selectedNode, node);
			selectedNode = (ItemNode) Controller.getInstance().getNode(node.getPath());
			fillFields();
			
			ItemEditorInput input = (ItemEditorInput) getEditorInput();
			input.setName(hidenText.getText() + "/" + getTextName().getText());
			setDirty(false);
//		}else {
//			//msg
//		}
	}
	
	public void setContent() {

	}

	public Text getHidenText() {
		return hidenText;
	}

	public void setHidenText(Text hidenText) {
		this.hidenText = hidenText;
	}
	
	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		firePropertyChange(IWorkbenchPartConstants.PROP_INPUT);
	}

	public void deleteItem() {
		Controller.getInstance().remove(
				selectedNode,getHidenText().getText());
		
	}
	
}
