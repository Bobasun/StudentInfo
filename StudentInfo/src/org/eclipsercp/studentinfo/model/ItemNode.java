package org.eclipsercp.studentinfo.model;

import java.util.List;

public class ItemNode implements Node{

	private String name;
	private String group;
	private String address;
	private String City;
	private int result;
	private Node parent;

	public ItemNode(String name, String address, String city, int result, Node group1) {
		this.name = name;
		this.group = group;
		this.address = address;
		City = city;
		this.result = result;
		this.parent = group1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setGroupNode(GroupNode groupNode) {
		this.parent = groupNode;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	public boolean hasChildren() {
		return false;
	}

	@Override
	public List<Node> getChildren() {
		return null;
	}

	@Override
	public void addNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNode(Node node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return name;
	}

	

	
	
}
