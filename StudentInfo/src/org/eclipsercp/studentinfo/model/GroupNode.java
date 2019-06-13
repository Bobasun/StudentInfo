package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.List;

public class GroupNode implements Node {

	private List<Node> users;
	private Node parent;
	private String name;

	public GroupNode(Node parent, String name) {
		this.name = name;
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	public void addUser(Node user) {
		if (users == null) {
			users = new ArrayList<Node>();
		}
		users.add(user);
	}

	public void removeUser(ItemNode user) {
		if (users != null) {
			users.remove(user);
			if (users.isEmpty()) {
				users = null;
			}
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasChildren() {
		if (users == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Node> getChildren() {
		return users;
	}

	@Override
	public void addNode(Node node) {
		if (users == null) {
			users = new ArrayList<Node>();
		}
		users.add(node);		
	}

	@Override
	public void removeNode(Node node) {
		if (users != null) {
			users.remove(node);
			if (users.isEmpty()) {
				users = null;
			}
		}		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	
	
}
