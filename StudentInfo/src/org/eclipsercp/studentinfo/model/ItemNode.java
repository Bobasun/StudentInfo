package org.eclipsercp.studentinfo.model;

public class ItemNode extends Node {

	private String address;
	private String city;
	private int result;

	public ItemNode(String name, String address, String city, int result) {
		super(name);
		this.address = address;
		this.city = city;
		this.result = result;
	}

	public ItemNode() {
		super("");
	}

	public ItemNode(GroupNode parent) {
		this("","","", 0);
		this.parent = parent;
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
	public String toString() {
		return name + " " + address;
	}

	@Override
	public int hashCode() {
		super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + this.result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemNode other = (ItemNode) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (result != other.result)
			return false;
		return true;
	}

	
	
}
