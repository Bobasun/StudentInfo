package org.eclipsercp.studentinfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupNode extends Node{


	/**
	 * 
	 */
	private static final long serialVersionUID = 597830079000970594L;
	protected List<INode> nodes;

	public GroupNode() {
		super();
	}
	
	public GroupNode(String name) {
		super(name);
		nodes = new ArrayList<>();
	}

	public GroupNode(GroupNode parent) {
		this("");
		this.parent = parent;

	}

	public List<INode> getChildren() {
		return nodes;
	}

	@Override
	public boolean hasChildren() {
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addChildren(List<INode> children) {
		nodes = children;
	}

}
