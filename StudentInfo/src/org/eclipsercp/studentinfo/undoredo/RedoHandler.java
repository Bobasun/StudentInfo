package org.eclipsercp.studentinfo.undoredo;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.model.INode;

public class RedoHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		if (window.getActivePage().getActiveEditor() != null) {
			AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage().getActiveEditor();
			UndoRedoINodes stack = editor.getStack();
			if (stack.hasRedo()) {
				INode node = stack.popRedo();
				stack.pushUndo(editor.getUndoRedoNode());
				editor.setUndoRedoNode(node);
				editor.fillFields(node);
			}
		}
		return null;
	}

}
