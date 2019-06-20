package org.eclipsercp.studentinfo.model;

import java.util.List;

public interface INodeService {

	void addNode(INode parent, INode node);
	void removeNode(INode node);
	void updateNode(INode oldNode, INode newNode);
	INode getRoot();
	INode[] getAllNodes();

	INode find(String path);
}
