package org.eclipsercp.studentinfo.model;

public class ItemNodeFactory extends INodeFactory {

	@Override
	public INode createINode(GroupNode parent) {
		return new ItemNode(parent);
	}

}
