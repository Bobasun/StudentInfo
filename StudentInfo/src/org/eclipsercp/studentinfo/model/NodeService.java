package org.eclipsercp.studentinfo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeService implements INodeService {

	private INode root;
	private static NodeService nodeService;
	
	
	public static NodeService getInstance() {
		if(nodeService==null) {
			nodeService=new NodeService( new RootNode("folder"));
		}
		return nodeService;
	}
	
	private NodeService(INode root) {
		this.root = root;

	}

	@Override
	public void removeNode(INode node) {
		if (node.getParent().getChildren().contains(node)) {
			node.getParent().getChildren().remove(node);
		}
	}

	@Override
	public void addNode(INode parent, INode node) {
		parent.getChildren().add(node);
		node.setParent(parent);
	}

	@Override
	public void updateNode(INode oldNode, INode newNode) {
		if (oldNode.getParent().getChildren().contains(oldNode)) {
			int index = oldNode.getParent().getChildren().indexOf(oldNode);
			oldNode.getParent().getChildren().set(index, newNode);
			newNode.setParent(oldNode.getParent());
		}
	}
	@Override
	public INode getRoot() {
		return root;
	}
	
	public INode[] getAllNodes() {
		INode[] nodes = new INode[1];
		nodes[0] = root;
		return nodes;
	}

	

}
