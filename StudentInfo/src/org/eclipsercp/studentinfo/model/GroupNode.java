package org.eclipsercp.studentinfo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupNode extends Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 597830079000970594L;
	@JsonProperty
	public List<INode> nodes;

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

	@JsonIgnore
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

	public void replaceChildren(List<INode> children) {
		nodes = children;
	}

	public void addChildren(INode child) {
		nodes.add(child);
		child.setParent(this);
	}

	public String groupToString() {
		String result;
		result = "{ " + nameToString() + ", " + patentToString() + ", " + childrenToString();

		return result;

	}

	private String childrenToString() {
		return "\"" + "nodes" + "\" : " + "\"" + nodes + "\"";
	}

	private String patentToString() {
		return "\"" + "parent" + "\" : " + "\"" + parent + "\"";
	}

	private String nameToString() {
		return "\"" + "name" + "\" : " + "\"" + name + "\"";
	}

}
