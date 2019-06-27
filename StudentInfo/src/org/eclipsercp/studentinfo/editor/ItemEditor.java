package org.eclipsercp.studentinfo.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.studentinfo.Application;
import org.eclipsercp.studentinfo.ImageKeys;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.controller.EnumAction;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.Node;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.view.ChangeNodeListener;

public class ItemEditor extends AbstractEditorPart implements IReusableEditor {

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
	private ItemNode selectedNode;
	private ChangeNodeListener itemListener;

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

		itemListener = new ChangeNodeListener() {

			public void stateChanged(ChangeNodeEvent event) {
				// if((MyEvent)event.getSource()).getAction() == MY_ACTION.ADD ||
				// MY_ACTION.REMOVE || MY_ACTION.EDIT){
				switch (event.getAction()) {
				case UPDATE_NODE:
					if (event.getNewNode() instanceof GroupNode) {
						if (selectedNode.getParent().getPath().equals(event.getNewNode().getPath())) {
//								&& selectedNode.getParent().getChildren() == ((GroupNode) event.getNewNode()).getChildren()) {
							// set input, set group, set title
							//selectedNode.setParent((GroupNode) event.getNewNode());
							setInput(new NodeEditorInput(selectedNode.getPath()));		
							textGroup.setText(event.getNewNode().getName());
//							fillFields();
						}
					}
					System.err.println("Item success updated");
					break;
				case REMOVE_NODE:
					System.err.println("Item success deleted");
					break;
				case ADD_NODE:
					System.err.println("Item success added");
					break;
				default:
					System.err.println("++");
					System.err.println("dddd");
					System.err.println("++");
					break;
				}

				if (!Controller.getInstance().isNodeExists(selectedNode.getParent())) {
//					System.err.println(event.node.getName());
					System.err.println(ItemEditor.this.getTitle());
				}
			}

		};

		Controller.getInstance().addListener(itemListener);
		Canvas canvas = new Canvas(sashForm, SWT.NONE);
		canvas.setLayout(new FillLayout());
		Button button = new Button(canvas, SWT.PUSH);
		button.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
						SWT.OPEN);
//		            dialog.setFilterNames(FILTER_NAMES);
//		            dialog.setFilterExtensions(FILTER_EXTS);
				String result = dialog.open();
				if (result != null) {
//		                   text.setText(result);
//		            	ResourceManager resourceManager = new LocalResourceManager(parentRegistry)
//		                   Image image = ResourceManager.getImage(result);
					ImageDescriptor idesc = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, result);
					Image image = idesc.createImage();
					button.setImage(image);
//		            	ImageData imgData = image.getImageData();
//		                   imgData=imgData.scaledTo(200, 200);
//
//		                   ImageLoader imageLoader = new ImageLoader();
//		                   imageLoader.data = new ImageData[] {imgData};
//		                   imageLoader.save(result, SWT.IMAGE_COPY);
//
//		                   System.out.println(imgData.width+"....."+imgData.height);
//		                   lbl_image_text.setBounds(25,88,imgData.width+10,imgData.height+10);
//		                   //Image size set to Label
//		                   //lbl_image_text.setBounds(25,88,image.getBounds().width+10,image.getBounds().height+10);
//		                   lbl_image_text.setImage(SWTResourceManager.getImage(result));
				}
			}

		});
//		button.setImage(image);
//		Image image = new Image(Display.getCurrent(), "icon/folder.png");
//		canvas.addPaintListener(new PaintListener() {
//
//			@Override
//			public void paintControl(PaintEvent e) {
//				e.gc.drawImage(image, 0, 0);
//			}
//		});
//		Composite compositeImage = new Composite(sashForm,SWT.NONE);
	}

	public ImageDescriptor getImageDescriptor(final String path) {
		ImageDescriptor imgD = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, path);
		if (imgD == null) {
			return null;
		}
		return imgD;
	}

//	public Image getImage(final String path)
//	{
//	    Image image = imageCacheMap.get(path);
//
//	    if (image == null)
//	    {
//	        image = getImageDescripto(path).createImage();
//	        imageCacheMap.put(path, image);
//	    }
//
//	    return image;
//	}

	public void addSelectedNode(INode item) {
		this.selectedNode = (ItemNode) item;
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

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		int fieldResult = Integer.parseInt(getTextResult().getText());
		ItemNode node = new ItemNode(getTextName().getText(), getTextAddress().getText(), getTextCity().getText(),
				fieldResult);

		if (Controller.getInstance().isNodeExists(selectedNode.getParent())) {
			Controller.getInstance().save(selectedNode, node);
			if (Controller.getInstance().isNodeExists(node)) {
				selectedNode = node;
				NodeEditorInput input = (NodeEditorInput) getEditorInput();
				input.setName(selectedNode.getPath());
				setDirty(false);
			} else {
				MessageDialog.openError(this.getSite().getPage().getWorkbenchWindow().getShell(), "Error",
						"Node already exist with this name");
			}
		} else {
			MessageDialog.openError(this.getSite().getPage().getWorkbenchWindow().getShell(), "Error",
					"Can't save node, because it was deleted");
			this.getSite().getPage().closeEditor(this, false);
		}
	}

	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		firePropertyChange(IWorkbenchPartConstants.PROP_INPUT);
	}

	public void deleteItem() { // delete second param
		Controller.getInstance().remove(selectedNode, selectedNode.getParent().getPath());

	}

	@Override
	public void dispose() {
		Controller.getInstance().removeListener(itemListener);
		super.dispose();
	}

	@Override
	protected boolean checkModifyFields() {
		return !textAddress.getText().equals(selectedNode.getAddress())
				|| !textCity.getText().equals(selectedNode.getCity())
				|| !textName.getText().equals(selectedNode.getName())
				|| !textResult.getText().equals("" + selectedNode.getResult());
	}

}
