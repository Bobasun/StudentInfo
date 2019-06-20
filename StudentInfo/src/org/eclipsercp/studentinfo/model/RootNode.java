package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class RootNode extends Node {

	public RootNode(String name) {
		super(name);
		nodes = new ArrayList<>();
	}

	@Override
	public INode getParent() {
		return null;
	}
	
//	@Override
//	public String getPath() {
//		return ".";
//	}
}
