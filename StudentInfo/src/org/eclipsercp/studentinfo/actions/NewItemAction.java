package org.eclipsercp.studentinfo.actions;



import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.ItemEditorInput;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.NodeService;

public class NewItemAction extends Action implements ISelectionListener, IWorkbenchAction {

	private final IWorkbenchWindow window;
	public final static String ID = "org.eclipsercp.studentinfo.newitem";
	private IStructuredSelection selection;

	public NewItemAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("New Item");
		setToolTipText("New Item");
		window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void run() {

//		Object item = selection.getFirstElement();
//		INode node = (Node) item;
		IWorkbenchPage page = window.getActivePage();
//		INode itemNode = new ItemNode("","","",0);
		ItemEditorInput input = new ItemEditorInput("new");
		try {
			page.openEditor(input, ItemEditor.ID);
			ItemEditor editor = (ItemEditor) page.getActiveEditor();
			
			INode parent = null;
//			INodeService service = new NodeService(node.getRoot());
//			service.addNode(node, itemNode);
			System.err.println(selection);
			if (selection == null) {
				parent = NodeService.getInstance().getRoot();
			} else {
				parent = (INode) selection.getFirstElement();
				editor.getTextGroup().setText(parent.getName());
				editor.getHidenText().setText(parent.getPath());
			}
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		
		if (incoming instanceof IStructuredSelection) {
			this.selection = (IStructuredSelection) incoming;
			setEnabled(!(selection.getFirstElement() instanceof ItemNode));
		} else {
			setEnabled(false);
		}
	}

}
