package org.eclipsercp.studentinfo.dnd;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TransferData;
import org.eclipsercp.studentinfo.controller.Controller;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.view.UsersView;


public class MyDropListener extends ViewerDropAdapter {

	private UsersView view;
	private final Viewer viewer;
	private INode target;
	private int operation;

	protected MyDropListener(Viewer viewer, UsersView view ) {
		super(viewer);
		this.viewer = viewer;
		this.view = view;
	}

	@Override
	public void drop(DropTargetEvent event) {
		operation = this.determineLocation(event);
//		INode dropedNode = this.view.getRootNode()[0];
		this.target = (INode) determineTarget(event);
		System.err.println(target.getPath() + "]]]]]]]]]]]]]]");
//		String target = (String) determineTarget(event);
//		String translatedLocation = "";
//		switch (operation) {
//		case 1:
//			translatedLocation = "Dropped before the target ";
//			break;
//		case 2:
//			translatedLocation = "Dropped after the target ";
//			break;
//		case 3:
//			translatedLocation = "Dropped on the target ";
//			break;
//		case 4:
//			translatedLocation = "Dropped into nothing ";
//			break;
//		}
//		System.out.println(translatedLocation);
//		System.out.println("The drop was done on the element: " + target);
		super.drop(event);
	}

	@Override
	public boolean performDrop(Object data) {
		INode datax = (INode) data;
		System.err.println("drop");
		Controller.getInstance().save(datax, datax);
		return false;
	}

	@Override
	public boolean validateDrop(Object target, int operation, TransferData transferType) {
		System.err.println("validate drop");
		if(target instanceof GroupNode) {
			return true;
		}
		return false;
	}

}
