package org.eclipsercp.studentinfo.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.studentinfo.controller.ChangeNodeEvent;
import org.eclipsercp.studentinfo.controller.ChangeNodeListener;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.controller.EnumAction;
import org.eclipsercp.studentinfo.dnd.MyDragListener;
import org.eclipsercp.studentinfo.dnd.NodeTransfer;
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
		createTreeviewer(parent);
		getSite().setSelectionProvider(treeViewer);
		controller.addListener(this);
		createDnD();
		addContextMenu();
	}

	private void createDnD() {
		int operations = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transferTypes = new Transfer[] { NodeTransfer.getInstance() };
		treeViewer.addDragSupport(operations, transferTypes, new MyDragListener(treeViewer));
	}

	private void createTreeviewer(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		treeViewer.setContentProvider(new UsersTreeViewerContentProvider());
		treeViewer.setLabelProvider(new UsersTreeViewerLabelProvider());
		treeViewer.setInput(getRootNode());
		treeViewer.expandAll();
		treeViewer.addDoubleClickListener(createDoubleClickListener());
	}

	private void addContextMenu() {
		MenuManager m = new MenuManager();
		Menu menu = m.createContextMenu(treeViewer.getTree());
		treeViewer.getTree().setMenu(menu);
		getSite().registerContextMenu(m, treeViewer);

	}

	private IDoubleClickListener createDoubleClickListener() {
		return new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
				INode selectedNode = (INode) thisSelection.getFirstElement();
				if (selectedNode instanceof RootNode) {
					return;
				}
				openNodeEditor(selectedNode);
			}

			private void openNodeEditor(INode node) {
				String editorId = getEditorId(node);
				String path = node.getPath() + editorId;
				NodeEditorInput input = new NodeEditorInput(path);
				try {
					AbstractEditorPart editor = (AbstractEditorPart) getSite().getWorkbenchWindow().getActivePage()
							.openEditor(input, editorId, false);
					editor.addSelectedNode(node);
					editor.fillFields();
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}

			private String getEditorId(INode selectedNode) {
				if (selectedNode instanceof ItemNode) {
					return ItemEditor.ID;
				} else if (selectedNode instanceof GroupNode) {
					return GroupEditor.ID;
				}
				return "";
			}
		};
	}

	public INode[] getRootNode() {
		INodeService service = NodeService.getInstance();
		return service.getAllNodes();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void stateChanged(ChangeNodeEvent e) {
		if (e.getAction().equals(EnumAction.SET_ROOT)) {
			treeViewer.setInput(getRootNode());
			getSite().getPage().closeAllEditors(false);

		}
		treeViewer.refresh();
		treeViewer.expandAll();
	}

}
