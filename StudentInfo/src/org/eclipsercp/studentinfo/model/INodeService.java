package org.eclipsercp.studentinfo.model;

public interface INodeService {

	void addNode(INode parent, INode node);
	void removeNode(INode node);
	void updateNode(INode oldNode, INode newNode);
	INode getRoot();
	INode[] getAllNodes();
	
}
