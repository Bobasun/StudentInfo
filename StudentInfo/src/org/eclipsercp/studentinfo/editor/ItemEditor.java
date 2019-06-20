package org.eclipsercp.studentinfo.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
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

public class ItemEditor extends AbstractEditorPart {

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

//	private INode parent;
//	private ItemNode localItem;

	public ItemEditor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
//	Node node = new Node();
//    NodeService.getInstance().add(node);
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

		data = new GridData(GridData.BEGINNING);
		labelCity = new Label(composite, SWT.NONE);
		labelCity.setText("City");
		labelCity.setData(data);

		data = new GridData(GridData.END);
		textCity = new Text(composite, SWT.BORDER);
		textCity.setData(data);

		data = new GridData(GridData.BEGINNING);
		labelResult = new Label(composite, SWT.NONE);
		labelResult.setText("Result");
		labelResult.setData(data);

		data = new GridData(GridData.END);
		textResult = new Text(composite, SWT.BORDER);
		textResult.setData(data);

		hidenText = new Text(parent, SWT.NONE);
		hidenText.setVisible(false);
//		Composite compositeImage = new Composite(sashForm,SWT.NONE);
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

	public void showItem(ItemNode user) {
		getTextName().setText(user.getName());
		getTextAddress().setText(user.getAddress());
		getTextGroup().setText(user.getGroup());
		getTextCity().setText(user.getCity());
		getTextResult().setText("" + user.getResult());
//		this.localItem = user;
	}

	public void setContent() {
//		int fieldResult = Integer.parseInt(getTextAddress().getText());
		ItemNode node = new ItemNode(getTextName().getText(),getTextAddress().getText(),
				getTextCity().getText(),0);
		
		Controller.getInstance().save(node, hidenText.getText());
//		
//		INodeService service = NodeService.getInstance();
//		if (localItem != null) {
//			service.updateNode(localItem, node);
//			localItem = null;
//		} else {
//			service.addNode(parent, node);
//			notifyAllListeners(null);

	}
//	}

	public Text getHidenText() {
		return hidenText;
	}

	public void setHidenText(Text hidenText) {
		this.hidenText = hidenText;
	}

//	public void setParent(INode parent) {
////		if (parent instanceof RootNode || parent instanceof GroupNode) {
//		this.parent = parent;
//		}
//		System.err.println(parent);
//	}

//	public void addListener(ChangeListener lis) {
//		list.add(lis);
//	}
//
//	private void notifyAllListeners(ChangeEvent event) {
//		list.forEach(listener -> listener.stateChanged(event));
////		for (int i = 0; i < list.size(); i++)
////			list.get(i).stateChanged(event);
//	}
//	
//	public void removeListener(ChangeListener lis) {
//		list.remove(lis);
//	}

	
	
}
