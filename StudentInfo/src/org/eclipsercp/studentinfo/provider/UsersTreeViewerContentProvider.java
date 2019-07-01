package org.eclipsercp.studentinfo.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipsercp.studentinfo.model.GroupNode;
import org.eclipsercp.studentinfo.model.INode;

public class UsersTreeViewerContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((INode[]) inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof GroupNode) {
		return ((GroupNode) parentElement).getChildren().toArray();
		}else {
			return null;
		}
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
