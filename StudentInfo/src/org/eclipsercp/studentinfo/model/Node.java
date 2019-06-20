package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Node implements INode{

	protected List<INode> nodes;
	protected INode parent;
	protected String name;
	
	public Node(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public INode getParent() {
		return parent;
	}

	@Override
	public List<INode> getChildren() {
		return nodes;
	}

	@Override
	public boolean hasChildren() {
		if(nodes == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public void setParent(INode parent) {
		this.parent = parent;
		
	}

	public INode getRoot() {
		if(parent == null) {
			return this;
		}
		return parent.getRoot();
	}
	
	public String toString() {
		return name;
	}
	
	
}
