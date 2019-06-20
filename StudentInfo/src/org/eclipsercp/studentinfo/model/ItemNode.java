package org.eclipsercp.studentinfo.model;

import java.util.List;

public class ItemNode extends Node {

	private String address;
	private String city;
	private int result;

	public ItemNode(String name, String address, String city, int result) {
		super(name);
		this.name = name;
		this.address = address;
		this.city = city;
		this.result = result;
	}

	public ItemNode() {
		super("");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return parent.getName();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public boolean hasChildren() {
		return false;
	}

	@Override
	public List<INode> getChildren() {
		return null;
	}

	@Override
	public String toString() {
		return name + " " + address;
	}

}
