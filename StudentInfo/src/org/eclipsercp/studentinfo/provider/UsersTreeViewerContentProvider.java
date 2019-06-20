package org.eclipsercp.studentinfo.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipsercp.studentinfo.model.RootNode;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;
import org.eclipsercp.studentinfo.model.ItemNode;

public class UsersTreeViewerContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((INode[]) inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((INode) parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		return ((INode) element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((INode) element).hasChildren();
	}

}
