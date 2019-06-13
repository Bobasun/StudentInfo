package org.eclipsercp.studentinfo;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.Node;
import org.eclipsercp.studentinfo.model.ItemNode;

public class UsersTreeViewerContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((Node[]) inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((Node) parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		return ((Node) element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((Node) element).hasChildren();
	}

}
