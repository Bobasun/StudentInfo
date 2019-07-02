package org.eclipsercp.studentinfo.view;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.INodeService;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.provider.UsersTreeViewerContentProvider;
import org.eclipsercp.studentinfo.provider.UsersTreeViewerLabelProvider;

public class UsersView extends ViewPart implements ChangeNodeListener {

	public static final String ID = "org.eclipsercp.studentinfo.treeviewer";

	private TreeViewer treeViewer;
	private Controller controller = Controller.getInstance();

	public UsersView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		treeViewer.setContentProvider(new UsersTreeViewerContentProvider());
		treeViewer.setInput(getRootNode());
		treeViewer.setLabelProvider(new UsersTreeViewerLabelProvider());
		getSite().setSelectionProvider(treeViewer);
		treeViewer.expandAll();
		controller.addListener(this);
		treeViewer.addDoubleClickListener(createDoubleClickListener());

	}
	
	private IDoubleClickListener createDoubleClickListener() {
		return new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				String editorId = "";
				INode selectedNode = (INode) thisSelection.getFirstElement();
				if (selectedNode instanceof ItemNode) {
					editorId = ItemEditor.ID;
				} else if (selectedNode instanceof RootNode) {
					return;
				} else if (selectedNode instanceof GroupNode) {
					editorId = GroupEditor.ID;
				}
				String path = selectedNode.getPath() + editorId;

				NodeEditorInput input = new NodeEditorInput(path);
				AbstractEditorPart editor;
				try {
					editor = (AbstractEditorPart) getSite().getWorkbenchWindow().getActivePage().openEditor(input,
							editorId, false);
					editor.addSelectedNode(selectedNode);
					editor.fillFields();
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

//	private String getEditorId(IStructuredSelection selection) {
//		if (selection.getFirstElement() instanceof ItemNode) {
//			return ItemEditor.ID;
//		}else if(selection.getFirstElement() instanceof RootNode) {
//			return;
//		} else if (selection.getFirstElement() instanceof GroupNode) {
//			return GroupEditor.ID;
//		}
//		return null;
//	}
	
	private INode[] getRootNode() {
		INodeService service = NodeService.getInstance();
		return service.getAllNodes();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void stateChanged(ChangeNodeEvent e) {
		treeViewer.refresh();
		treeViewer.expandAll();
	}

}
