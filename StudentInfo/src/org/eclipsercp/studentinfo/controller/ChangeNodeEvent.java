package org.eclipsercp.studentinfo.controller;

import org.eclipsercp.studentinfo.model.INode;

public class ChangeNodeEvent  {

	private EnumAction action;
	private INode node;
	public ChangeNodeEvent(EnumAction action, INode node) {
	
		this.action = action;
		this.node = node;
	}
	public EnumAction getAction() {
		return action;
	}
	public void setAction(EnumAction action) {
		this.action = action;
	}
	public INode getNode() {
		return node;
	}
	public void setNode(INode node) {
		this.node = node;
	}
	
	
	
	
}
