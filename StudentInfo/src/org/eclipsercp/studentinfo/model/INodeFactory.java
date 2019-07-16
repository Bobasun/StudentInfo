package org.eclipsercp.studentinfo.model;

import org.eclipsercp.studentinfo.editor.GroupEditor;
import org.eclipsercp.studentinfo.editor.ItemEditor;

public abstract class INodeFactory {

	public static INodeFactory createNodeFactoty(String id) {

		switch (id) {
		case ItemEditor.ID:
			return new ItemNodeFactory();
		case GroupEditor.ID:
			return new GroupNodeFactory();
		default:
			System.err.println("incorrect id");
			break;
		}
		return null;
	}

	public abstract INode createINode(GroupNode parent);

}
