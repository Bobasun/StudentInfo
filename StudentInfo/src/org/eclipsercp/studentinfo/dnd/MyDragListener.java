package org.eclipsercp.studentinfo.dnd;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.RootNode;

public class MyDragListener implements DragSourceListener {

	private final TreeViewer viewer;

	public MyDragListener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		if (viewer.getStructuredSelection().getFirstElement() instanceof RootNode) {
			event.doit = false;
		}

		System.out.println("Start Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		IStructuredSelection selection = viewer.getStructuredSelection();
		INode selectedNode = (INode) selection.getFirstElement();

		if (NodeTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = selectedNode;
		}

	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("Finshed Drag");
	}

}
