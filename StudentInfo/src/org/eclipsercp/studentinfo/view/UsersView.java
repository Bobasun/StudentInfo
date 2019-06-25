package org.eclipsercp.studentinfo.view;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.provider.UsersTreeViewerContentProvider;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.GroupEditorInput;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class UsersView extends ViewPart implements ChangeNodeListener {

	public static final String ID = "org.eclipsercp.studentinfo.treeviewer";

	private TreeViewer treeViewer;
	private Controller controller = Controller.getInstance();
	// controller instance
	ItemEditor itemEd = new ItemEditor();

	public UsersView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		treeViewer.setContentProvider(new UsersTreeViewerContentProvider());
		treeViewer.setInput(getRootNode());
		getSite().setSelectionProvider(treeViewer);
		controller.addListener(this);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				if (thisSelection.getFirstElement() instanceof ItemNode) {
					ItemNode selectedNode = (ItemNode) ((ItemNode) thisSelection.getFirstElement()).clone();

					try {
						ItemEditorInput input = new ItemEditorInput(selectedNode.getPath());
						getSite().getPage().openEditor(input, ItemEditor.ID);
						ItemEditor editor = (ItemEditor) getSite().getPage().getActiveEditor();
						editor.addSelectedNode(selectedNode);
						editor.fillFields();
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (thisSelection.getFirstElement() instanceof GroupNode) {
					GroupNode selectedNode = (GroupNode) thisSelection.getFirstElement();
					try {
						GroupEditorInput input = new GroupEditorInput(selectedNode.getPath());
						getSite().getPage().openEditor(input, GroupEditor.ID);
						GroupEditor editor = (GroupEditor) getSite().getPage().getActiveEditor();
						editor.addSelectedNode(selectedNode);
						editor.fillFields();
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	private INode[] getRootNode() {

		INodeService service = NodeService.getInstance();
		GroupNode group = new GroupNode("group 1");
		GroupNode group2 = new GroupNode("group 1.2");
		INode item1 = new ItemNode("item1", "", "dsa", 1);
		INode item2 = new ItemNode("item2", "", "dsa", 1);
		service.addNode(service.getRoot(), group);
		service.addNode(group, group2);
		service.addNode(group2, item1);
		service.addNode(service.getRoot(), item2);
		return service.getAllNodes();
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void stateChanged(ChangeNodeEvent e) {
		treeViewer.refresh();
	}

}
