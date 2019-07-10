package org.eclipsercp.studentinfo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Node implements INode, Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3675962890779052672L;
	@JsonIgnore
	protected GroupNode parent;
	
	@JsonProperty
	protected String name;

	public Node() {
	}

	public Node(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getParentPath() {
		return parent.getPath();
	}


	@Override
	public void setParent(GroupNode parent) {
		this.parent = parent;
	}

	@JsonIgnore
	public GroupNode getRoot() {
		if (parent == null) {
			return (GroupNode) this;
		}
		return parent.getRoot();
	}

	public String toString() {
		return name;
	}

	
	@Override
	public GroupNode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@JsonIgnore
	@Override
	public String getPath() {
		if (parent != null) {
			return parent.getPath() + "/" + getName();
		} else {
			return "/" + getName();
		}
	}

	public INode clone() {
		INode node = null;
		try {
			node = (INode) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return node;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

//	public String () {
//		return name;
//		
//	}

}
