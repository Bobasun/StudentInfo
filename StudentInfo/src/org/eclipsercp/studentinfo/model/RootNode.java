package org.eclipsercp.studentinfo.model;

public class RootNode extends GroupNode {

	public RootNode(String name) {
		super(name);
	}

	@Override
	public GroupNode getParent() {
		return null;
	}
	

}
