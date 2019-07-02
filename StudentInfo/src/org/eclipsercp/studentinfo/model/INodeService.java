package org.eclipsercp.studentinfo.model;

import java.util.List;

public interface INodeService {

	void addNode(GroupNode parent, INode node);

	void updateNode(INode oldNode, INode newNode);

	GroupNode getRoot();

	INode[] getAllNodes();

	INode find(String path, Class<? extends INode> class_);

	INode find(String path);

	void removeNode(GroupNode parent, INode node);

}
