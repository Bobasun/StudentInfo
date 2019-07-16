package org.eclipsercp.studentinfo.dnd;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.editor.AbstractEditorPart;
import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;
import org.eclipsercp.studentinfo.editor.NodeEditorInput;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;
import org.eclipsercp.studentinfo.model.RootNode;

public class MyDropListener implements DropTargetListener {

	private IWorkbenchWindow window;

	public MyDropListener(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		if (!(event.detail == DND.DROP_NONE)) {
			event.detail = DND.DROP_COPY;
		}
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		if ((event.operations == DND.DROP_DEFAULT)) {
			event.detail = DND.DROP_NONE;
		}
	}

	@Override
	public void dragOver(DropTargetEvent event) {
	}

	@Override
	public void drop(DropTargetEvent event) {
		if (NodeTransfer.getInstance().isSupportedType(event.dataTypes[0])) {
			INode node = (INode) event.data;
			if (node instanceof RootNode) {
				return;
			}
			if (node instanceof ItemNode) {
				node = Controller.getInstance().findNode(node.getPath(), ItemNode.class);
			} else {
				node = Controller.getInstance().findNode(node.getPath(), GroupNode.class);
			}
			String editorId = getEditorId(node);
			try {
				AbstractEditorPart editor = (AbstractEditorPart) window.getActivePage()
						.openEditor(new NodeEditorInput(node.getPath() + editorId), editorId);
				editor.addSelectedNode(node);
				editor.fillFields();
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	private String getEditorId(INode node) {
		if (node instanceof ItemNode) {
			return ItemEditor.ID;
		} else if (node instanceof GroupNode) {
			return GroupEditor.ID;
		}
		return null;
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

}
