package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Node implements INode, Cloneable{

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
		if (nodes == null) {
			return false;
		}
		return true;
	}

	@Override
	public void setParent(INode parent) {
		this.parent = parent;

	}

	public INode getRoot() {
		if (parent == null) {
			return this;
		}
		return parent.getRoot();
	}

	public String toString() {
		return name;
	}

	@Override
	public String getPath() {
		if (parent != null) {
			return parent.getPath() + "/" + getName();
		} else {
			return "/" + getName();
		}
//		if (parent == null) {
//			path += this.getName();
//			return path;
//		} else {
//			path+=this.getName() +"/";
//		return parent.getPathToRoot(path);
//		}
	}

	public INode clone() {
		INode node = null;
		try {
			node =  (INode) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}
	
}
