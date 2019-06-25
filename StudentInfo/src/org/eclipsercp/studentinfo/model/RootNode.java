package org.eclipsercp.studentinfo.model;

public class RootNode extends GroupNode {

	public RootNode(String name) {
		super(name);
		//nodes = new ArrayList<>();
	}

	@Override
	public GroupNode getParent() {
		return null;
	}
	
//	@Override
//	public String getPath() {
//		return ".";
//	}
}
