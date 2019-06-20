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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.provider.UsersTreeViewerContentProvider;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class UsersView extends ViewPart implements ChangeListener {

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
//		controller.addListener(this);
		controller.addListener(this);
		controller.addListener(this);
		controller.addListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.err.println("Listener");
			}
		});
//		sign

		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				System.err.println("doubleClick");
				TreeViewer viewer = (TreeViewer) event.getViewer();
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				if (thisSelection.getFirstElement() instanceof ItemNode) {
					ItemNode selectedNode = (ItemNode) thisSelection.getFirstElement();
					try {

						getSite().getPage().openEditor(
								new ItemEditorInput(selectedNode.getName()), ItemEditor.ID);
						ItemEditor editor = (ItemEditor) getSite().getPage().getActiveEditor();
						editor.showItem(selectedNode);
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

		INode group = new GroupNode("group 1");
		INode group2 = new GroupNode("group 1.2");
				
		INode item1 = new ItemNode("item1", "", "dsa", 1);
		INode item2 = new ItemNode("item2", "", "dsa", 1);
		service.addNode(service.getRoot(), group);
		service.addNode(group, group2);
	
		service.addNode(group2, item1);
		service.addNode(service.getRoot(), item2);
		
//		
//		
//		System.err.println(service.getRoot().getChildren());
//		System.err.println(service.getRoot().getChildren().get(0).getChildren());
//		System.err.println(service.getRoot().getChildren().get(0).getChildren().get(0).getChildren());
//		

		return service.getAllNodes();
	}

	@Override
	public void setFocus() {
// 		treeViewer.refresh();
//		System.err.println("refreshed");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		treeViewer.refresh();
	}

}
