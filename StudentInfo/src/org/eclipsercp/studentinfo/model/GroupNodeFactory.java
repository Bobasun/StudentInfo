package org.eclipsercp.studentinfo.model;

public class GroupNodeFactory extends INodeFactory {

	@Override
	public INode createINode(GroupNode parent) {
		return new GroupNode(parent);
	}

}
