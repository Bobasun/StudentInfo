package org.eclipsercp.studentinfo.editor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.ChangeNodeListener;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class ItemEditor extends AbstractEditorPart implements IReusableEditor {

	public final static String ID = "StudentInfo.editor";
	private Text textName;
	private Text textGroup;
	private Text textAddress;
	private Text textCity;
	private Text textResult;
//	private ItemNode selectedNode;
//	private ChangeNodeListener itemListener;
	private String imagePath = "";
	private Button imageButton;

	public ItemEditor() {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {

		SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setLayout(new FillLayout());
//		Composite composite = new Composite(sashForm, SWT.NONE);
		createEditorContext(sashForm);
		createImageButton(sashForm);

	}

	private void createImageButton(SashForm sashForm) {
		Composite composite = new Composite(sashForm, SWT.NONE);
		imageButton = new Button(composite, SWT.PUSH);
		imageButton.setBounds(0, 0, 250, 250);
		imageButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						SWT.SAVE);
//		            dialog.setFilterNames(FILTER_NAMES);
//		            dialog.setFilterExtensions(FILTER_EXTS);
				imagePath = dialog.open();
				if (imagePath != null) {
					try {
						URL url = Paths.get(imagePath).toUri().toURL();
						ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
						Image image = descriptor.createImage();
						imageButton.setImage(image);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		
		imageButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!imagePath.equals(getSelectedNode().getImagePath())) {
					setDirty(true);
				}
			}
			
		});
		
		imageButton.addTouchListener(new TouchListener() {

			@Override
			public void touch(TouchEvent e) {
				if (!imagePath.equals(getSelectedNode().getImagePath())) {
					setDirty(true);
				}
			}
		});

	}

	protected void addNodeListener() {
		listener = new ChangeNodeListener() {
			public void stateChanged(ChangeNodeEvent event) {
				switch (event.getAction()) {
				case UPDATE_NODE:
					if (event.getNewNode() instanceof GroupNode) {
						if (getSelectedNode().getParent().getPath().equals(event.getNewNode().getPath())) {
							textGroup.setText(event.getNewNode().getName());
						}
					}
					break;
				case REMOVE_NODE:
					if (getSelectedNode().getPath().contains(event.getOldNode().getPath())) {
						getSite().getPage().closeEditor(ItemEditor.this, false);
					}
					break;
				case ADD_NODE:
					break;
				default:
					break;
				}
			}
		};
		Controller.getInstance().addListener(listener);
	}

	protected void compositeSetting(Composite composite) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginTop = 15;
		layout.marginLeft = 30;
		layout.horizontalSpacing = 15;
		layout.verticalSpacing = 15;
		composite.setLayout(layout);
	}

	protected void createItputItems(Composite composite) {
		addInputNameRow(composite);
		addInputGroupRow(composite);
		addInputAddressRow(composite);
		addInputCityRow(composite);
		addInputResultRow(composite);
	}

	private void addInputNameRow(Composite composite) {
		Label labelName = new Label(composite, SWT.NONE);
		labelName.setText("Name");
		textName = new Text(composite, SWT.BORDER);
		textName.addModifyListener(new TextModifyListener());
	}

	private void addInputGroupRow(Composite composite) {
		Label labelGroup = new Label(composite, SWT.NONE);
		labelGroup.setText("Group");
		textGroup = new Text(composite, SWT.BORDER);
		textGroup.setEnabled(false);
	}

	private void addInputAddressRow(Composite composite) {
		Label labelAddress = new Label(composite, SWT.NONE);
		labelAddress.setText("Address");
		textAddress = new Text(composite, SWT.BORDER);
		textAddress.addModifyListener(new TextModifyListener());

	}

	private void addInputCityRow(Composite composite) {
		Label labelCity = new Label(composite, SWT.NONE);
		labelCity.setText("City");
		textCity = new Text(composite, SWT.BORDER);
		textCity.addModifyListener(new TextModifyListener());
	}

	private void addInputResultRow(Composite composite) {
		Label labelResult = new Label(composite, SWT.NONE);
		labelResult.setText("Result");
		textResult = new Text(composite, SWT.BORDER);
		textResult.addModifyListener(new TextModifyListener());
	}

	
	@Override
	public void setFocus() {

	}

	public Text getTextName() {
		return textName;
	}

	public Text getTextGroup() {
		return textGroup;
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

	public Text getTextResult() {
		return textResult;
	}

	public Button getImageButton() {
		return imageButton;
	}

	public void fillFields() {
		getTextName().setText(getSelectedNode().getName());
		getTextAddress().setText(getSelectedNode().getAddress());
		getTextGroup().setText(getSelectedNode().getGroup());
		getTextCity().setText(getSelectedNode().getCity());
		getTextResult().setText("" + getSelectedNode().getResult());
		setPartName(getSelectedNode().getName());
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,
				UtilsWithConstants.NEW_ITEM);
		imagePath = getSelectedNode().getImagePath();
		if (!imagePath.equals("")) {
			try {
				descriptor = ImageDescriptor.createFromURL(Paths.get(imagePath).toUri().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		imageButton.setImage(descriptor.createImage());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

		ItemNode node = createNewNode();
		doSave(node);
//		if (Controller.getInstance().isNodeExists(selectedNode.getParent())) {
//			Controller.getInstance().save(selectedNode, node);
//			if (Controller.getInstance().isNodeExists(node)) {
//				selectedNode = node;
//				NodeEditorInput input = (NodeEditorInput) getEditorInput();
//				input.setName(selectedNode.getPath() + ID);
//				setDirty(false);
//				setPartName(selectedNode.getName());
//			} else {
//				MessageDialog.openError(this.getSite().getPage().getWorkbenchWindow().getShell(), "Error",
//						"Node already exist with this name");
//			}
//		}
//		else {
//			MessageDialog.openError(this.getSite().getPage().getWorkbenchWindow().getShell(), "Error",
//					"Can't save node, because it was deleted");
//			this.getSite().getPage().closeEditor(this, false);
//		}
	}

	private ItemNode createNewNode() {
		if (!Validator.validateNumber(getTextResult().getText())) {
			MessageDialog.openError(getSite().getShell(), "Error", "The field must contain only digits");
			getTextResult().setText("" + getSelectedNode().getResult());
		}
		if (!Validator.validateName(getTextName().getText())) {
			getTextName().setText("Default");
		}
		ItemNode node = new ItemNode(getTextName().getText(), getTextAddress().getText(), getTextCity().getText(),
				Integer.parseInt(getTextResult().getText()), imagePath);
		return node;
	}

	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		firePropertyChange(IWorkbenchPartConstants.PROP_INPUT);
	}

	

	@Override
	protected boolean checkModifyFields() {
		return !textAddress.getText().equals(getSelectedNode().getAddress())
				|| !textCity.getText().equals(getSelectedNode().getCity())
				|| !textName.getText().equals(getSelectedNode().getName())
				|| !textResult.getText().equals("" + getSelectedNode().getResult());
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
	private ItemNode getSelectedNode() {
		return (ItemNode) selectedNode;
	}

}
