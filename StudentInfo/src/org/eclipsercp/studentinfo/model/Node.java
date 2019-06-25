package org.eclipsercp.studentinfo.model;

public abstract class Node implements INode, Cloneable {

	protected GroupNode parent;
	protected String name;

	public Node(String name) {
		this.name = name;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public GroupNode getParent() {
		return parent;
	}

	@Override
	public void setParent(GroupNode parent) {
		this.parent = parent;

	}

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
			node = (INode) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Node other = (Node) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		if (parent == null) {
//			if (other.parent != null)
//				return false;
//		} else if (!parent.equals(other.parent))
//			return false;
//		return true;
//	}


	
	

}
