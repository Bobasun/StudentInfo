package studentinfo.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.RootNode;

public class RemoveNodeHandler extends AbstractHandler {

	public static final String REMOVE_COMMAND_ID = "StudentInfo.commands.removeCommand";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		ISelectionService selectionService = window.getSelectionService();
		IStructuredSelection selection = (IStructuredSelection) selectionService.getSelection();
		if (selection.getFirstElement() instanceof INode && !(selection.getFirstElement() instanceof RootNode)) {
			INode node = (INode) selection.getFirstElement();
			Controller.getInstance().remove(node);
		}
		return null;
	}
}
