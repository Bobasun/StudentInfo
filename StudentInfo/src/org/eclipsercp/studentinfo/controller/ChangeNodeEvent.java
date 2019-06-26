package org.eclipsercp.studentinfo.controller;

import org.eclipsercp.studentinfo.model.INode;

public class ChangeNodeEvent  {

	private EnumAction action;
	private INode newNode;
	private INode oldNode;
	public ChangeNodeEvent(EnumAction action, INode oldNode, INode newNode) {
		this.oldNode = oldNode;
		this.action = action;
		this.newNode = newNode;
	}
	public EnumAction getAction() {
		return action;
	}

	public INode getNewNode() {
		return newNode;
	}

	public INode getOldNode() {
		return oldNode;
	}
	
	
	
	
}
