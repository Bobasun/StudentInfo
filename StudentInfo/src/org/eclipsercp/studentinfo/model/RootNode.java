package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class RootNode implements Node {

	private List<Node> groups;
//	private GroupNode rootGroup;
	private Node root;
	private String name;

	public RootNode(String name) {
		this.name = name;
	}

//	public GroupNode getRoot() {
//		if (rootGroup == null) {
//			rootGroup = new GroupNode(null, "RootGroup");
//		}
//		return rootGroup;
//	}

	public void addGroup(Node group) {
		if (groups == null) {
			groups = new ArrayList<Node>();
		}
		groups.add(group);
	}

	public void removeGroup(Node group) {
		if (groups != null) {
			groups.remove(group);
			if (groups.isEmpty()) {
				groups = null;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Node getParent() {
		return root;
	}

	public boolean hasChildren() {
		if (groups != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Node> getChildren() {
		return groups;
	}

	@Override
	public void addNode(Node node) {
		if (groups == null) {
			groups = new ArrayList<Node>();
		}
		groups.add(node);		
	}

	@Override
	public void removeNode(Node node) {
		if (groups != null) {
			groups.remove(node);
			if (groups.isEmpty()) {
				groups = null;
			}
		}		
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
