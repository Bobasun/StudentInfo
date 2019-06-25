package org.eclipsercp.studentinfo.model;

import java.util.List;

public class NodeService implements INodeService {

	private GroupNode root;
	private static NodeService nodeService;

	public static NodeService getInstance() {
		if (nodeService == null) {
			nodeService = new NodeService(new RootNode("folder"));
		}
		return nodeService;
	}

	private NodeService(GroupNode root) {
		this.root = root;

	}

	@Override
	public void removeNode(GroupNode parent, INode node) {
		if (parent.getChildren().contains(node)) {
			parent.getChildren().remove(node);
		}
	}

	@Override
	public void addNode(GroupNode parent, INode node) {
		parent.getChildren().add(node);
		node.setParent(parent);
	}

	@Override
	public void updateNode(INode oldNode, INode newNode) {
		newNode.setParent(oldNode.getParent());
		if(oldNode.hasChildren()) {
			((GroupNode)newNode).addChildren(((GroupNode)oldNode).getChildren());
			for (int i = 0; i < ((GroupNode)newNode).getChildren().size(); i++) {
				((GroupNode)newNode).getChildren().get(i).setParent((GroupNode)newNode);
			}
		}
		int index = oldNode.getParent().getChildren().indexOf(oldNode);
		oldNode.getParent().getChildren().set(index, newNode);
		
//		if (newNode.getParent().getChildren().contains(oldNode)) {
//			oldNode.setName(newNode.getName());
//			if(oldNode instanceof ItemNode) {
//				ItemNode selectedItem = (ItemNode) oldNode;
//				ItemNode localItem = (ItemNode) newNode;
//				selectedItem.setAddress(localItem.getAddress());
//				selectedItem.setCity(localItem.getCity());
//				selectedItem.setResult(localItem.getResult());
//			}
//			int index = newNode.getParent().getChildren().indexOf(oldNode);
//			oldNode.getParent().getChildren().set(index, newNode);
			
//		}
	}

	@Override
	public GroupNode getRoot() {
		return root;
	}

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
			if (localNode != null  ) {
				if(localNode.hasChildren()) {
					children = ((GroupNode)localNode).getChildren();
				}
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

//	@Override
//	public void updateNode(INode selectedNode, String name) {
//		selectedNode.setName(name);
//	}

}
