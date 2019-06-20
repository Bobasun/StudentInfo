package org.eclipsercp.studentinfo.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeService implements INodeService {

	private INode root;
	private static NodeService nodeService;

	public static NodeService getInstance() {
		if (nodeService == null) {
			nodeService = new NodeService(new RootNode("folder"));
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

//	public INode findParentByName(String name) {
//		if (root.getName().equals(name)) {
//			return root;
//		}
//		return findParent(name, root.getChildren());
//	}
//	
//	public INode findParents(String name, List<INode> children) {
//
//		for(INode node : children) {
//			if (node.getName().equals(name)) {
//				System.err.println("yep");
//				return node;
//			} else {
//				System.err.println("nope");
//				return findParent(name, node.getChildren());
//			}
//		}
//	
//			
//
//		return null;
//
//	}

	public INode[] getAllNodes() {
		INode[] nodes = new INode[1];
		nodes[0] = root;
		return nodes;
	}

	@Override
	public INode find(String path) {

		String[] groups = path.substring(1, path.length()).split("/");
		if (root.getName().equals(groups[0])) {
			return find(groups, root.getChildren());
		} else {
			throw new UnsupportedOperationException();
		}

	}

	private INode find(String[] groups, List<INode> children) {

		INode localNode = root;
		for (int i = 1; i < groups.length; i++) {
			localNode = find(groups[i], children);
			if (localNode != null) {
				children = localNode.getChildren();
			} else {
				return null;
			}
		}
		return localNode;

	}

	private INode find(String name, List<INode> nodes) {
		INode local = null;
		for (INode node : nodes) {
			if (node.getName().equals(name)) {
				local = node;
				break;
			}
		}
		return local;

	}

}
