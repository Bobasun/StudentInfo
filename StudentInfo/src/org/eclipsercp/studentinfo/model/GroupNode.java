package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class GroupNode extends Node {

//	public GroupNode(INode parent, String name) {
//		super(parent,name);
//		nodes = new ArrayList<>();
//	}

	public GroupNode(String name) {
		super(name);
		nodes = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return name;
	}

}
