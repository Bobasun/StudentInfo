package org.eclipsercp.studentinfo.editor;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.utils.UtilsWithConstants;

public class ItemEditor extends AbstractEditorPart {

	public final static String ID = "StudentInfo.editor";
	private Text textName;
	private Text textGroup;
	private Text textAddress;
	private Text textCity;
	private Text textResult;
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
						SWT.OPEN);
				String localPath;
				localPath = dialog.open();
				if (localPath != null) {
					try {
						imagePath = localPath;
						URL url = Paths.get(imagePath).toUri().toURL();
						ImageDescriptor descriptor = ImageDescriptor.createFromURL(url);
						Image image = descriptor.createImage();
						Image resizedImage = UtilsWithConstants.resize(image, 250, 250);
						imageButton.setImage(resizedImage);
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

	public void fillFields() {
		fillFields(getSelectedNode());
	}

	protected ItemNode createNewNode() {
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
	protected boolean checkModifyFields() {
		return !textAddress.getText().equals(getSelectedNode().getAddress())
				|| !textCity.getText().equals(getSelectedNode().getCity())
				|| !textName.getText().equals(getSelectedNode().getName())
				|| !textResult.getText().equals("" + getSelectedNode().getResult());
	}

	@Override
	protected String getID() {
		return ID;
	}

	public ItemNode getSelectedNode() {
		return (ItemNode) selectedNode;
	}

	@Override
	protected void setFields(INode node) {
		textGroup.setText(node.getName());
	}

	@Override
	protected EditorPart getActiveEditor() {
		return this;
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

	@Override
	public void fillFields(INode node) {
		ItemNode itemNode = (ItemNode) node;
		getTextName().setText(itemNode.getName());
		getTextAddress().setText(itemNode.getAddress());
		getTextGroup().setText(itemNode.getGroup());
		getTextCity().setText(itemNode.getCity());
		getTextResult().setText("" + itemNode.getResult());
		setPartName(itemNode.getName());
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID,
				UtilsWithConstants.DEFAULT_PICTURE);
		imagePath = itemNode.getImagePath();
		if (!imagePath.equals("")) {
			try {
				descriptor = ImageDescriptor.createFromURL(Paths.get(imagePath).toUri().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		imageButton.setImage(UtilsWithConstants.resize(descriptor.createImage(), 250, 250));
	}

}
